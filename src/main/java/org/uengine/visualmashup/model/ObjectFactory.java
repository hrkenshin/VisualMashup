
package org.uengine.visualmashup.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.uengine.visualmashup.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Entity_QNAME = new QName("", "_Entity");
    private final static QName _VmGadgets_QNAME = new QName("", "vm_gadgets");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.uengine.visualmashup.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UIPage }
     * 
     */
    public UIPage createUIPage() {
        return new UIPage();
    }

    /**
     * Create an instance of {@link DeleteRequest }
     * 
     */
    public DeleteRequest createDeleteRequest() {
        return new DeleteRequest();
    }

    /**
     * Create an instance of {@link ViewResponse }
     * 
     */
    public ViewResponse createViewResponse() {
        return new ViewResponse();
    }

    /**
     * Create an instance of {@link VmGadgets }
     * 
     */
    public VmGadgets createVmGadgets() {
        return new VmGadgets();
    }

    /**
     * Create an instance of {@link CreateRequest }
     * 
     */
    public CreateRequest createCreateRequest() {
        return new CreateRequest();
    }

    /**
     * Create an instance of {@link DeleteResponse }
     * 
     */
    public DeleteResponse createDeleteResponse() {
        return new DeleteResponse();
    }

    /**
     * Create an instance of {@link ExceptionReport }
     * 
     */
    public ExceptionReport createExceptionReport() {
        return new ExceptionReport();
    }

    /**
     * Create an instance of {@link Portlet }
     * 
     */
    public Portlet createPortlet() {
        return new Portlet();
    }

    /**
     * Create an instance of {@link Layout }
     * 
     */
    public Layout createLayout() {
        return new Layout();
    }

    /**
     * Create an instance of {@link Pipeline }
     * 
     */
    public Pipeline createPipeline() {
        return new Pipeline();
    }

    /**
     * Create an instance of {@link UpdateRequest }
     * 
     */
    public UpdateRequest createUpdateRequest() {
        return new UpdateRequest();
    }

    /**
     * Create an instance of {@link Condition }
     * 
     */
    public Condition createCondition() {
        return new Condition();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link SearchRequest }
     * 
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest();
    }

    /**
     * Create an instance of {@link Wire.Transformer.Param }
     * 
     */
    public Wire.Transformer.Param createWireTransformerParam() {
        return new Wire.Transformer.Param();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Wire }
     * 
     */
    public Wire createWire() {
        return new Wire();
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link ViewRequest }
     * 
     */
    public ViewRequest createViewRequest() {
        return new ViewRequest();
    }

    /**
     * Create an instance of {@link Wire.Transformer }
     * 
     */
    public Wire.Transformer createWireTransformer() {
        return new Wire.Transformer();
    }

    /**
     * Create an instance of {@link TerminalType }
     * 
     */
    public TerminalType createTerminalType() {
        return new TerminalType();
    }

    /**
     * Create an instance of {@link Portlet.Position }
     * 
     */
    public Portlet.Position createPortletPosition() {
        return new Portlet.Position();
    }

    /**
     * Create an instance of {@link Portlet.UserPref }
     * 
     */
    public Portlet.UserPref createPortletUserPref() {
        return new Portlet.UserPref();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Entity }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "_Entity")
    public JAXBElement<Entity> createEntity(Entity value) {
        return new JAXBElement<Entity>(_Entity_QNAME, Entity.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VmGadgets }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "vm_gadgets", substitutionHeadNamespace = "", substitutionHeadName = "_Entity")
    public JAXBElement<VmGadgets> createVmGadgets(VmGadgets value) {
        return new JAXBElement<VmGadgets>(_VmGadgets_QNAME, VmGadgets.class, null, value);
    }

}
