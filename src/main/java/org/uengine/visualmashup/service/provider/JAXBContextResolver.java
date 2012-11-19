package org.uengine.visualmashup.service.provider;

import org.uengine.visualmashup.service.VMServiceException;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * Unmarshaller ContextResolver 클래스로 XML Schema 인스턴스를 등록하여
 * REST웹서비스의 XML 요청 구문의 유효성 체크를 Schema 레벨로 가능하도록 한다.
 *
 * @author 이승백
 */
@Provider
public class JAXBContextResolver implements ContextResolver<Unmarshaller> {

    private static final String INSTANCE_PACKAGE = "org.uengine.visualmashup.model";
    private static final String SCHEMA_LOCATION = "/schema/mashup/visual-mashup.xsd";

    public Unmarshaller getContext(Class<?> type) {
        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = JAXBContext.newInstance(INSTANCE_PACKAGE).createUnmarshaller();

            SchemaFactory sf = SchemaFactory
                    .newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(JAXBContextResolver.class.getResource(SCHEMA_LOCATION));

            unmarshaller.setSchema(schema);
        } catch (Exception e) {
            throw new VMServiceException("XML Schema 인스턴스 초기화 중 예외 발생!", e);
        }

        return unmarshaller;
    }
}