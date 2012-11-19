package org.uengine.visualmashup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.uengine.visualmashup.dao.DAOMapper;
import org.uengine.visualmashup.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.List;

/**
 * 웹UI 레이어와의 외부인터페이스로 RESTFul 웹서비스 공통기능 정의 추상 클래스.
 * 웹어플리케이션에서 GET 또는 POST 방식으로 정의된 경로의 리소스URL로 요청이 보내지게 되며,
 * JSON 또는 XML 형식으로 Contents 를 받거나 Parameter 를 받아 다음의 주요 Function 들을 처리한다.
 * <pre>
 *  - view   : 키로 데이터의 조회
 *  - create : 데이터의 생성
 *  - update : 데이터의 수정
 *  - delete : 데이터의 삭제
 *  - search : 조건절로 데이터의 조회
 * </pre>
 *
 * @author 이승백
 */
@Service
public abstract class VMService {

    protected DAOMapper daoMapper = null;
    protected QName QNAME = null;

    @Autowired
    protected MessageSourceAccessor messageAccessor;

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public VMService() {
    }

    public VMService(DAOMapper daoMapper, QName QNAME) {
        this.daoMapper = daoMapper;
        this.QNAME = QNAME;
    }

    /**
     * 조건절로 데이터를 조회한다.
     *
     * @param request SearchRequest 요청값
     * @return SearchResponse 조회요청에 대한 응답
     */
    @POST
    @Path("/search")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public SearchResponse search(SearchRequest request) {
        SearchResponse response = null;

        int offset = request.getOffset();
        int size = request.getSize();
        String orderBy = request.getOrderBy();
        boolean isTotalCntOnly = request.isIsTotalCntOnly();
        List<Condition> conditionList = request.getCondition();

        try {
            // 0. 요청 데이타 획득 : 조건절
            WhereClause whereClause = WhereClause.conditionToWhereClause(conditionList);
            whereClause.setOffset(offset);
            whereClause.setSize(size);

            if (StringUtils.hasText(orderBy)) {
                whereClause.setOrderByClause(orderBy);
            }

            // 1. 요청 처리 실행
            int totalCnt = daoMapper.countByWhereClause(whereClause);
            if (isTotalCntOnly) {
                response = new SearchResponse();
                response.setIsSuccess(true);
                response.setTotalCnt(totalCnt);
                return response;
            }
            List<? extends Entity> recordList = daoMapper.selectByWhereClause(whereClause);

            // 2. 응답 메시지 처리
            response = new SearchResponse();
            response.setTotalCnt(totalCnt);
            response.setIsSuccess(true);

            List<JAXBElement<? extends Entity>> baseList = response.getEntity();

            JAXBElement<? extends Entity> entityElement = null;
            for (Entity record : recordList) {
                entityElement = new JAXBElement<Entity>(QNAME, Entity.class, null, record);
                baseList.add(entityElement);
            }
        } catch (Exception e) {
            throw new VMServiceException(messageAccessor.getMessage("error.SearchDataFailed",
                    new String[]{e.getMessage()}), e);
        }

        return response;
    }

    /**
     * 키로 데이터를 조회한다.
     *
     * @param id 요청값
     * @return ViewResponse 조회요청에 대한 응답
     */
    @GET
    @Path("/view")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ViewResponse view(@QueryParam("id") int id) {
        ViewResponse response = null;

        try {
            // 1. 요청 처리 실행
            Entity record = daoMapper.selectByPrimaryKey(id);

            // 2. 응답 메시지 처리
            response = new ViewResponse();
            response.setIsSuccess(true);

            if (record != null) {
                JAXBElement<? extends Entity> entityElement = new JAXBElement<Entity>(QNAME, Entity.class, null, record);
                response.setEntity(entityElement);
            }
        } catch (Exception e) {
            throw new VMServiceException(messageAccessor.getMessage("error.ViewDataFailed",
                    new String[]{e.getMessage()}), e);
        }

        return response;
    }

    /**
     * 데이터를 생성한다.
     *
     * @param request CreateRequest 요청값
     * @return CreateResponse 요청에대한 응답
     */
    @POST
    @Path("/create")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public CreateResponse create(CreateRequest request) {
        CreateResponse response = null;

        List<JAXBElement<? extends Entity>> baseList = request.getEntity();

        try {
            Entity record = null;

            // 1. 요청 처리 실행
            for (JAXBElement<?> jaxbElement : baseList) {
                record = (Entity) jaxbElement.getValue();
                daoMapper.insert(record);
            }

            // 2. 응답 메시지 처리
            response = new CreateResponse();
            response.setIsSuccess(true);
        } catch (Exception e) {
            throw new VMServiceException(messageAccessor.getMessage("error.CreateDataFailed",
                    new String[]{e.getMessage()}), e);
        }

        return response;
    }

    /**
     * 데이터를 수정한다.
     *
     * @param request UpdateRequest 요청값
     * @return UpdateResponse 요청에대한 응답
     */
    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Transactional
    public UpdateResponse update(UpdateRequest request) {
        UpdateResponse response = null;

        List<JAXBElement<? extends Entity>> baseList = request.getEntity();

        try {
            Entity record = null;

            // 1. 요청 처리 실행
            for (JAXBElement<?> jaxbElement : baseList) {
                record = (Entity) jaxbElement.getValue();
                daoMapper.updateByPrimaryKeySelective(record);
            }

            // 2. 응답 메시지 처리
            response = new UpdateResponse();
            response.setIsSuccess(true);
        } catch (Exception e) {
            throw new VMServiceException(messageAccessor.getMessage("error.UpdateDataFailed",
                    new String[]{e.getMessage()}), e);
        }

        return response;
    }

    /**
     * 데이터를 삭제한다.
     *
     * @param request DeleteRequest 요청값
     * @return DeleteResponse 삭제요청에 대한 응답
     */
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
            for (Integer key : keyList) {
                daoMapper.deleteByPrimaryKey(key);
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
     * 키로 데이터를 조회한다.
     *
     * @param key 키
     * @return 데이터
     */
    public Entity get(int key) {
        return daoMapper.selectByPrimaryKey(key);
    }

    /**
     * 데이터를 입력한다.
     *
     * @param record 데이터
     * @return 입력건수
     */
    public int add(Entity record) {
        return daoMapper.insert(record);
    }

    /**
     * 데이터를 삭제한다.
     *
     * @param key 키
     * @return 삭제건수
     */
    public int remove(int key) {
        return daoMapper.deleteByPrimaryKey(key);
    }

    /**
     * 데이터를 변경한다.
     *
     * @param record 데이터
     * @return 변경건수
     */
    public int modify(Entity record) {
        return daoMapper.updateByPrimaryKey(record);
    }

    /**
     * 조건절로 데이터를 조회한다.
     *
     * @param whereClause 조건절
     * @return 데이터 목록
     */
    public List<? extends Entity> list(WhereClause whereClause) {
        return daoMapper.selectByWhereClause(whereClause);
    }
}