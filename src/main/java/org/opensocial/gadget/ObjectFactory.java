
package org.opensocial.gadget;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.opensocial.gadget package. 
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

    private final static QName _ModuleModulePrefsLink_QNAME = new QName("", "Link");
    private final static QName _ModuleModulePrefsRequire_QNAME = new QName("", "Require");
    private final static QName _ModuleModulePrefsOAuth_QNAME = new QName("", "OAuth");
    private final static QName _ModuleModulePrefsLocale_QNAME = new QName("", "Locale");
    private final static QName _ModuleModulePrefsIcon_QNAME = new QName("", "Icon");
    private final static QName _ModuleModulePrefsOptional_QNAME = new QName("", "Optional");
    private final static QName _ModuleModulePrefsPreload_QNAME = new QName("", "Preload");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.opensocial.gadget
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Module.UserPref.EnumValue }
     * 
     */
    public Module.UserPref.EnumValue createModuleUserPrefEnumValue() {
        return new Module.UserPref.EnumValue();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.Link }
     * 
     */
    public Module.ModulePrefs.Link createModuleModulePrefsLink() {
        return new Module.ModulePrefs.Link();
    }

    /**
     * Create an instance of {@link OAuthResourceType }
     * 
     */
    public OAuthResourceType createOAuthResourceType() {
        return new OAuthResourceType();
    }

    /**
     * Create an instance of {@link GadgetFeatureType.Param }
     * 
     */
    public GadgetFeatureType.Param createGadgetFeatureTypeParam() {
        return new GadgetFeatureType.Param();
    }

    /**
     * Create an instance of {@link Module.UserPref }
     * 
     */
    public Module.UserPref createModuleUserPref() {
        return new Module.UserPref();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.OAuth.Service.Authorization }
     * 
     */
    public Module.ModulePrefs.OAuth.Service.Authorization createModuleModulePrefsOAuthServiceAuthorization() {
        return new Module.ModulePrefs.OAuth.Service.Authorization();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs }
     * 
     */
    public Module.ModulePrefs createModuleModulePrefs() {
        return new Module.ModulePrefs();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.Locale.Msg }
     * 
     */
    public Module.ModulePrefs.Locale.Msg createModuleModulePrefsLocaleMsg() {
        return new Module.ModulePrefs.Locale.Msg();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.OAuth }
     * 
     */
    public Module.ModulePrefs.OAuth createModuleModulePrefsOAuth() {
        return new Module.ModulePrefs.OAuth();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.OAuth.Service }
     * 
     */
    public Module.ModulePrefs.OAuth.Service createModuleModulePrefsOAuthService() {
        return new Module.ModulePrefs.OAuth.Service();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.Icon }
     * 
     */
    public Module.ModulePrefs.Icon createModuleModulePrefsIcon() {
        return new Module.ModulePrefs.Icon();
    }

    /**
     * Create an instance of {@link Module }
     * 
     */
    public Module createModule() {
        return new Module();
    }

    /**
     * Create an instance of {@link GadgetFeatureType }
     * 
     */
    public GadgetFeatureType createGadgetFeatureType() {
        return new GadgetFeatureType();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.OAuth.Rating }
     * 
     */
    public Module.ModulePrefs.OAuth.Rating createModuleModulePrefsOAuthRating() {
        return new Module.ModulePrefs.OAuth.Rating();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.Preload }
     * 
     */
    public Module.ModulePrefs.Preload createModuleModulePrefsPreload() {
        return new Module.ModulePrefs.Preload();
    }

    /**
     * Create an instance of {@link Module.Content }
     * 
     */
    public Module.Content createModuleContent() {
        return new Module.Content();
    }

    /**
     * Create an instance of {@link Module.ModulePrefs.Locale }
     * 
     */
    public Module.ModulePrefs.Locale createModuleModulePrefsLocale() {
        return new Module.ModulePrefs.Locale();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Link }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Link", scope = Module.ModulePrefs.class)
    public JAXBElement<Module.ModulePrefs.Link> createModuleModulePrefsLink(Module.ModulePrefs.Link value) {
        return new JAXBElement<Module.ModulePrefs.Link>(_ModuleModulePrefsLink_QNAME, Module.ModulePrefs.Link.class, Module.ModulePrefs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GadgetFeatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Require", scope = Module.ModulePrefs.class)
    public JAXBElement<GadgetFeatureType> createModuleModulePrefsRequire(GadgetFeatureType value) {
        return new JAXBElement<GadgetFeatureType>(_ModuleModulePrefsRequire_QNAME, GadgetFeatureType.class, Module.ModulePrefs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Module.ModulePrefs.OAuth }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "OAuth", scope = Module.ModulePrefs.class)
    public JAXBElement<Module.ModulePrefs.OAuth> createModuleModulePrefsOAuth(Module.ModulePrefs.OAuth value) {
        return new JAXBElement<Module.ModulePrefs.OAuth>(_ModuleModulePrefsOAuth_QNAME, Module.ModulePrefs.OAuth.class, Module.ModulePrefs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Locale }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Locale", scope = Module.ModulePrefs.class)
    public JAXBElement<Module.ModulePrefs.Locale> createModuleModulePrefsLocale(Module.ModulePrefs.Locale value) {
        return new JAXBElement<Module.ModulePrefs.Locale>(_ModuleModulePrefsLocale_QNAME, Module.ModulePrefs.Locale.class, Module.ModulePrefs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Icon }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Icon", scope = Module.ModulePrefs.class)
    public JAXBElement<Module.ModulePrefs.Icon> createModuleModulePrefsIcon(Module.ModulePrefs.Icon value) {
        return new JAXBElement<Module.ModulePrefs.Icon>(_ModuleModulePrefsIcon_QNAME, Module.ModulePrefs.Icon.class, Module.ModulePrefs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GadgetFeatureType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Optional", scope = Module.ModulePrefs.class)
    public JAXBElement<GadgetFeatureType> createModuleModulePrefsOptional(GadgetFeatureType value) {
        return new JAXBElement<GadgetFeatureType>(_ModuleModulePrefsOptional_QNAME, GadgetFeatureType.class, Module.ModulePrefs.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Preload }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Preload", scope = Module.ModulePrefs.class)
    public JAXBElement<Module.ModulePrefs.Preload> createModuleModulePrefsPreload(Module.ModulePrefs.Preload value) {
        return new JAXBElement<Module.ModulePrefs.Preload>(_ModuleModulePrefsPreload_QNAME, Module.ModulePrefs.Preload.class, Module.ModulePrefs.class, value);
    }

}
