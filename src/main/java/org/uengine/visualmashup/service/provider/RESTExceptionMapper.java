package org.uengine.visualmashup.service.provider;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uengine.visualmashup.common.util.JAXBUtil;
import org.uengine.visualmashup.model.ExceptionReport;
import org.uengine.visualmashup.service.VMServiceException;
import org.xml.sax.SAXParseException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

/**
 * VMService 웹서비스 예외 처리 클래스.
 * REST 웹서비스에서 발생하는 모든 예외에 대해 메시지를 XML 형태로 변환하여 반환하는 방식으로 예외를 처리한다.
 *
 * @author 이승백
 */
@Provider
public class RESTExceptionMapper implements ExceptionMapper<Exception> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Response toResponse(Exception e) {
        ExceptionReport exceptionReport = createExceptionReport(e);

        try {
            int status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();

            if (e instanceof UnmarshalException || e instanceof SAXParseException
                    || e instanceof JAXBException) {
                status = Response.Status.BAD_REQUEST.getStatusCode();
            } else if (e instanceof WebApplicationException) {
                WebApplicationException we = (WebApplicationException) e;
                status = we.getResponse().getStatus();
            } else if (e instanceof VMServiceException) {
                VMServiceException ae = (VMServiceException) e;
                exceptionReport.setCode(ae.getCode());
                exceptionReport.setLocator(ae.getLocator());
            }

            String xml = JAXBUtil.marshal(exceptionReport);

            return Response.status(status).entity(xml).type("text/xml; charset=UTF-8").build();
        } catch (Exception dbe) {
            logger.warn("ExceptionReport를 Marshalling할 수 없습니다.", dbe);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("ExceptionReport Marshalling Error")
                    .type("text/plain").build();
        }
    }

    /**
     * REST 서비스 예외가 발생한 경우 ExceptionReport JAXB Object를 생성한다.
     *
     * @param rse 예외 클래스
     * @return ExceptionReport JAXB Object
     */
    private ExceptionReport createExceptionReport(Exception rse) {
        String message = rse.getMessage();
        ExceptionReport exceptionReport = new ExceptionReport();
        exceptionReport.setMessage(message == null || message.equals("") ? rse.getClass().getName() : message);
        exceptionReport.setStackTrace(ExceptionUtils.getFullStackTrace(rse));
        return exceptionReport;
    }
}