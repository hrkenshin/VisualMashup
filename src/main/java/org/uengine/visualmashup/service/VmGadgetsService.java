package org.uengine.visualmashup.service;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.spi.resource.Singleton;
import org.apache.commons.lang.StringEscapeUtils;
import org.opensocial.gadget.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.uengine.visualmashup.common.util.KeyGenerator;
import org.uengine.visualmashup.dao.VmGadgetsMapper;
import org.uengine.visualmashup.model.DeleteRequest;
import org.uengine.visualmashup.model.DeleteResponse;
import org.uengine.visualmashup.model.Entity;
import org.uengine.visualmashup.model.VmGadgets;
import org.uengine.visualmashup.service.provider.GadgetModuleJAXB;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DataBindingException;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 웹UI 레이어와의 외부인터페이스로 오픈소셜가젯 RESTFul 웹서비스 서비스로직 구현클래스.(vm_gadgets 테이블)
 * 웹어플리케이션에서 GET 또는 POST 방식으로 정의된 경로의 리소스URL로 요청이 보내지게 되며,
 * JSON 또는 XML 형식으로 Contents 를 받거나 Parameter 를 받아 다음의 주요 Function 들을 처리한다.
 * <pre>
 *  - get    : 키로 데이터의 조회
 *  - create : 데이터의 생성
 *  - update : 데이터의 수정
 *  - delete : 데이터의 삭제
 *  - search : 조건절로 데이터의 조회
 *
 * </pre>
 *
 * @author 이승백
 */
@Singleton
@Path("/vm_gadgets")
@Service("vmGadgetsService")
public class VmGadgetsService extends VMService {

    @Context
    private ServletContext servletContext;

    public static final QName QNAME = new QName("", "vm_gadgets");

    public VmGadgetsService() {
    }

    @Autowired
    public VmGadgetsService(VmGadgetsMapper daoMapper) {
        super(daoMapper, QNAME);
        GadgetModuleJAXB.initSchema();
    }

    /**
     * 가젯 정의 XML 파일을 업로드하여 임포트한다.
     * <pre>
     *  - 가젯 정의 XML 파일을 업로드하고 XML을 파싱하여 가젯정보를 DB로 Import 한다.
     * </pre>
     *
     * @param byteArray       <code>byte[]</code>
     * @param fileDisposition <code>FormDataContentDisposition</code>
     * @param fileName        파일명
     * @param title           제목
     * @param description     설명
     * @return JSON String ex) {success:true}
     */
    @POST
    @Path("/uploadGadget")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_HTML)
    public String uploadGadget(@FormDataParam("file") byte[] byteArray,
                               @FormDataParam("file") FormDataContentDisposition fileDisposition,
                               @FormDataParam("fileName") String fileName,
                               @FormDataParam("title") String title,
                               @FormDataParam("description") String description) {
        File xmlFile = null;
        try {
            // 0. 업로드
            String path = messageAccessor.getMessage("vm.gadget.basePath") + File.separator +
                    KeyGenerator.getKeyByDateFormat();
            File baseDir = new File(servletContext.getRealPath(path));
            if (!baseDir.exists()) baseDir.mkdirs();

            fileName = StringUtils.hasText(fileName) ?
                    StringUtils.getFilename(StringUtils.cleanPath(fileName)) : fileDisposition.getFileName();

            xmlFile = new File(baseDir.getAbsolutePath() + File.separator + fileName);
            FileCopyUtils.copy(byteArray, xmlFile);

            // logging
            logger.info("Gadget XML Uploaded : " + xmlFile.getAbsolutePath());

            // 1. 가젯 XML 정보 파싱
            Module module = GadgetModuleJAXB.unmarshal(xmlFile, org.opensocial.gadget.Module.class);
            String xmlContents = new String(byteArray, "UTF-8");

            // 2. 가젯 정보 설정
            VmGadgets vmGadgets = new VmGadgets();
            vmGadgets.setTitle(StringUtils.hasText(title) ? title : module.getModulePrefs().getTitle());
            vmGadgets.setTitleUrl(module.getModulePrefs().getTitleUrl());
            vmGadgets.setDescription(StringUtils.hasText(description) ? description : module.getModulePrefs().getDescription());
            vmGadgets.setAuthor(module.getModulePrefs().getAuthor());
            vmGadgets.setAuthorEmail(module.getModulePrefs().getAuthorEmail());
            vmGadgets.setScreenshot(module.getModulePrefs().getScreenshot());
            vmGadgets.setThumbnail(module.getModulePrefs().getThumbnail());
            vmGadgets.setType("0");
            vmGadgets.setPath(StringUtils.cleanPath(path + File.separator + fileName));
            vmGadgets.setXmlContents(xmlContents);

            // 3. 가젯 정보 DB 입력
            daoMapper.insert(vmGadgets);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            cleanXMLFile(xmlFile);
            if (e instanceof DataBindingException) {
                return "{success:false, msg:\"" + StringEscapeUtils.escapeJavaScript(
                        messageAccessor.getMessage("error.XMLNotValid", new String[]{e.getMessage()})) + "\"}";
            } else {
                return "{success:false, msg:\"" + StringEscapeUtils.escapeJavaScript(
                        messageAccessor.getMessage("error.UploadGadgetFailed", new String[]{e.getMessage()})) + "\"}";
            }
        }

        return "{success:true}";
    }

    /**
     * 데이터를 삭제한다.(가젯 XML 파일 삭제 처리)
     *
     * @param request DeleteRequest 요청값
     * @return DeleteResponse 삭제요청에 대한 응답
     */
    @Override
    @DELETE
    @Path("/delete")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public DeleteResponse delete(DeleteRequest request) {
        DeleteResponse response = null;

        List<Integer> keyList = request.getId();

        try {
            // 1. 요청 처리 실행
            Entity record = null;
            VmGadgets vmGadgets = null;
            File xmlFile = null;
            for (Integer key : keyList) {
                record = daoMapper.selectByPrimaryKey(key);
                if (record != null) {
                    vmGadgets = (VmGadgets) record;
                    if (StringUtils.hasText(vmGadgets.getPath())) {
                        xmlFile = new File(servletContext.getRealPath(vmGadgets.getPath()));
                        cleanXMLFile(xmlFile);
                    }
                    daoMapper.deleteByPrimaryKey(key);
                }
            }

            // 2. 응답 메시지 처리
            response = new DeleteResponse();
            response.setIsSuccess(true);
        } catch (Exception e) {
            throw new VMServiceException(messageAccessor.getMessage("error.DeleteDataFailed",
                    new String[]{e.getMessage()}), e);
        }

        return response;
    }

    /**
     * Gadget XML 파일을 부모 디렉토리와 함께 삭제한다.
     *
     * @param xmlFile Gadget XML File
     */
    private void cleanXMLFile(File xmlFile) {
        if (xmlFile.exists()) {
            try {
                org.apache.commons.io.FileUtils.deleteDirectory(xmlFile.getParentFile());
                // logging
                logger.info("Gadget XML Deleted : " + xmlFile.getAbsolutePath());
            } catch (IOException ie) {
                logger.warn("Gadget XML Cleaning Fail : " + ie.getMessage(), ie);
            }
        }
    }
}