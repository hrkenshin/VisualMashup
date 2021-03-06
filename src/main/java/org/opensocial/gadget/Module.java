
package org.opensocial.gadget;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModulePrefs" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded" minOccurs="0">
 *                   &lt;element name="Require" type="{}GadgetFeatureType"/>
 *                   &lt;element name="Optional" type="{}GadgetFeatureType"/>
 *                   &lt;element name="Preload">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="authz" default="none">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;enumeration value="none"/>
 *                                 &lt;enumeration value="signed"/>
 *                                 &lt;enumeration value="oauth"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="sign_owner" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *                           &lt;attribute name="sign_viewer" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *                           &lt;attribute name="views" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="oauth_service_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="oauth_token_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="oauth_request_token" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="oauth_request_token_secret" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Icon">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="mode">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;enumeration value="base64"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Locale">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="msg" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                     &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="country" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="messages" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="language_direction">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;enumeration value="ltr"/>
 *                                 &lt;enumeration value="rtl"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="Link">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="rel" use="required">
 *                             &lt;simpleType>
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                                 &lt;enumeration value="gadgets.help"/>
 *                                 &lt;enumeration value="gadgets.support"/>
 *                                 &lt;enumeration value="icon"/>
 *                               &lt;/restriction>
 *                             &lt;/simpleType>
 *                           &lt;/attribute>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="OAuth" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Service" maxOccurs="unbounded">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Request" type="{}OAuthResourceType" minOccurs="0"/>
 *                                       &lt;element name="Access" type="{}OAuthResourceType" minOccurs="0"/>
 *                                       &lt;element name="Authorization" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;attribute name="url" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="Rating">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;attribute name="scheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="" />
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/choice>
 *                 &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="title_url" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="author" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="author_email" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="screenshot" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="thumbnail" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="UserPref" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EnumValue" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="display_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="display_name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="default_value" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="datatype" default="string">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="string"/>
 *                       &lt;enumeration value="hidden"/>
 *                       &lt;enumeration value="bool"/>
 *                       &lt;enumeration value="enum"/>
 *                       &lt;enumeration value="list"/>
 *                       &lt;enumeration value="number"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Content" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="type" default="html">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                       &lt;enumeration value="html"/>
 *                       &lt;enumeration value="url"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *                 &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="view" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="preferred_height" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *                 &lt;attribute name="preferred_width" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "modulePrefs",
    "userPref",
    "content"
})
@XmlRootElement(name = "Module")
public class Module {

    @XmlElement(name = "ModulePrefs")
    protected Module.ModulePrefs modulePrefs;
    @XmlElement(name = "UserPref")
    protected List<Module.UserPref> userPref;
    @XmlElement(name = "Content", required = true)
    protected List<Module.Content> content;

    /**
     * Gets the value of the modulePrefs property.
     * 
     * @return
     *     possible object is
     *     {@link Module.ModulePrefs }
     *     
     */
    public Module.ModulePrefs getModulePrefs() {
        return modulePrefs;
    }

    /**
     * Sets the value of the modulePrefs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Module.ModulePrefs }
     *     
     */
    public void setModulePrefs(Module.ModulePrefs value) {
        this.modulePrefs = value;
    }

    /**
     * Gets the value of the userPref property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userPref property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserPref().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Module.UserPref }
     * 
     * 
     */
    public List<Module.UserPref> getUserPref() {
        if (userPref == null) {
            userPref = new ArrayList<Module.UserPref>();
        }
        return this.userPref;
    }

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Module.Content }
     * 
     * 
     */
    public List<Module.Content> getContent() {
        if (content == null) {
            content = new ArrayList<Module.Content>();
        }
        return this.content;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="type" default="html">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="html"/>
     *             &lt;enumeration value="url"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *       &lt;attribute name="href" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="view" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="preferred_height" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *       &lt;attribute name="preferred_width" type="{http://www.w3.org/2001/XMLSchema}integer" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Content {

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String type;
        @XmlAttribute
        protected String href;
        @XmlAttribute
        protected String view;
        @XmlAttribute(name = "preferred_height")
        protected BigInteger preferredHeight;
        @XmlAttribute(name = "preferred_width")
        protected BigInteger preferredWidth;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            if (type == null) {
                return "html";
            } else {
                return type;
            }
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the href property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHref() {
            return href;
        }

        /**
         * Sets the value of the href property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHref(String value) {
            this.href = value;
        }

        /**
         * Gets the value of the view property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getView() {
            return view;
        }

        /**
         * Sets the value of the view property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setView(String value) {
            this.view = value;
        }

        /**
         * Gets the value of the preferredHeight property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getPreferredHeight() {
            return preferredHeight;
        }

        /**
         * Sets the value of the preferredHeight property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setPreferredHeight(BigInteger value) {
            this.preferredHeight = value;
        }

        /**
         * Gets the value of the preferredWidth property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getPreferredWidth() {
            return preferredWidth;
        }

        /**
         * Sets the value of the preferredWidth property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setPreferredWidth(BigInteger value) {
            this.preferredWidth = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded" minOccurs="0">
     *         &lt;element name="Require" type="{}GadgetFeatureType"/>
     *         &lt;element name="Optional" type="{}GadgetFeatureType"/>
     *         &lt;element name="Preload">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="authz" default="none">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="none"/>
     *                       &lt;enumeration value="signed"/>
     *                       &lt;enumeration value="oauth"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="sign_owner" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
     *                 &lt;attribute name="sign_viewer" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
     *                 &lt;attribute name="views" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="oauth_service_name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="oauth_token_name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="oauth_request_token" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="oauth_request_token_secret" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Icon">
     *           &lt;complexType>
     *             &lt;simpleContent>
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                 &lt;attribute name="mode">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="base64"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/simpleContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Locale">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="msg" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                           &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="country" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="messages" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="language_direction">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="ltr"/>
     *                       &lt;enumeration value="rtl"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="Link">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="rel" use="required">
     *                   &lt;simpleType>
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *                       &lt;enumeration value="gadgets.help"/>
     *                       &lt;enumeration value="gadgets.support"/>
     *                       &lt;enumeration value="icon"/>
     *                     &lt;/restriction>
     *                   &lt;/simpleType>
     *                 &lt;/attribute>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="OAuth" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="Service" maxOccurs="unbounded">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="Request" type="{}OAuthResourceType" minOccurs="0"/>
     *                             &lt;element name="Access" type="{}OAuthResourceType" minOccurs="0"/>
     *                             &lt;element name="Authorization" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;attribute name="url" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="Rating">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;attribute name="scheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="" />
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/choice>
     *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="title_url" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="author" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="author_email" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="screenshot" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="thumbnail" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "requireOrOptionalOrPreload"
    })
    public static class ModulePrefs {

        @XmlElementRefs({
            @XmlElementRef(name = "Icon", type = JAXBElement.class),
            @XmlElementRef(name = "Link", type = JAXBElement.class),
            @XmlElementRef(name = "Optional", type = JAXBElement.class),
            @XmlElementRef(name = "Locale", type = JAXBElement.class),
            @XmlElementRef(name = "Require", type = JAXBElement.class),
            @XmlElementRef(name = "Preload", type = JAXBElement.class),
            @XmlElementRef(name = "OAuth", type = JAXBElement.class)
        })
        protected List<JAXBElement<?>> requireOrOptionalOrPreload;
        @XmlAttribute
        protected String title;
        @XmlAttribute(name = "title_url")
        protected String titleUrl;
        @XmlAttribute
        protected String description;
        @XmlAttribute
        protected String author;
        @XmlAttribute(name = "author_email")
        protected String authorEmail;
        @XmlAttribute
        protected String screenshot;
        @XmlAttribute
        protected String thumbnail;

        /**
         * Gets the value of the requireOrOptionalOrPreload property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the requireOrOptionalOrPreload property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRequireOrOptionalOrPreload().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Icon }{@code >}
         * {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Link }{@code >}
         * {@link JAXBElement }{@code <}{@link GadgetFeatureType }{@code >}
         * {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Locale }{@code >}
         * {@link JAXBElement }{@code <}{@link GadgetFeatureType }{@code >}
         * {@link JAXBElement }{@code <}{@link Module.ModulePrefs.Preload }{@code >}
         * {@link JAXBElement }{@code <}{@link Module.ModulePrefs.OAuth }{@code >}
         * 
         * 
         */
        public List<JAXBElement<?>> getRequireOrOptionalOrPreload() {
            if (requireOrOptionalOrPreload == null) {
                requireOrOptionalOrPreload = new ArrayList<JAXBElement<?>>();
            }
            return this.requireOrOptionalOrPreload;
        }

        /**
         * Gets the value of the title property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitle() {
            return title;
        }

        /**
         * Sets the value of the title property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitle(String value) {
            this.title = value;
        }

        /**
         * Gets the value of the titleUrl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTitleUrl() {
            return titleUrl;
        }

        /**
         * Sets the value of the titleUrl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTitleUrl(String value) {
            this.titleUrl = value;
        }

        /**
         * Gets the value of the description property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescription() {
            return description;
        }

        /**
         * Sets the value of the description property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescription(String value) {
            this.description = value;
        }

        /**
         * Gets the value of the author property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthor() {
            return author;
        }

        /**
         * Sets the value of the author property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthor(String value) {
            this.author = value;
        }

        /**
         * Gets the value of the authorEmail property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthorEmail() {
            return authorEmail;
        }

        /**
         * Sets the value of the authorEmail property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthorEmail(String value) {
            this.authorEmail = value;
        }

        /**
         * Gets the value of the screenshot property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getScreenshot() {
            return screenshot;
        }

        /**
         * Sets the value of the screenshot property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setScreenshot(String value) {
            this.screenshot = value;
        }

        /**
         * Gets the value of the thumbnail property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getThumbnail() {
            return thumbnail;
        }

        /**
         * Sets the value of the thumbnail property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setThumbnail(String value) {
            this.thumbnail = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;simpleContent>
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *       &lt;attribute name="mode">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="base64"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/simpleContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class Icon {

            @XmlValue
            protected String value;
            @XmlAttribute
            protected String mode;
            @XmlAttribute
            protected String type;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the mode property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMode() {
                return mode;
            }

            /**
             * Sets the value of the mode property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMode(String value) {
                this.mode = value;
            }

            /**
             * Gets the value of the type property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getType() {
                return type;
            }

            /**
             * Sets the value of the type property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setType(String value) {
                this.type = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="rel" use="required">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="gadgets.help"/>
         *             &lt;enumeration value="gadgets.support"/>
         *             &lt;enumeration value="icon"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Link {

            @XmlAttribute(required = true)
            protected String href;
            @XmlAttribute(required = true)
            protected String rel;

            /**
             * Gets the value of the href property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHref() {
                return href;
            }

            /**
             * Sets the value of the href property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHref(String value) {
                this.href = value;
            }

            /**
             * Gets the value of the rel property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getRel() {
                return rel;
            }

            /**
             * Sets the value of the rel property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setRel(String value) {
                this.rel = value;
            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="msg" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="lang" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="country" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="messages" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="language_direction">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="ltr"/>
         *             &lt;enumeration value="rtl"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "msg"
        })
        public static class Locale {

            protected List<Module.ModulePrefs.Locale.Msg> msg;
            @XmlAttribute
            protected String lang;
            @XmlAttribute
            protected String country;
            @XmlAttribute
            protected String messages;
            @XmlAttribute(name = "language_direction")
            protected String languageDirection;

            /**
             * Gets the value of the msg property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the msg property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getMsg().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Module.ModulePrefs.Locale.Msg }
             * 
             * 
             */
            public List<Module.ModulePrefs.Locale.Msg> getMsg() {
                if (msg == null) {
                    msg = new ArrayList<Module.ModulePrefs.Locale.Msg>();
                }
                return this.msg;
            }

            /**
             * Gets the value of the lang property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLang() {
                return lang;
            }

            /**
             * Sets the value of the lang property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLang(String value) {
                this.lang = value;
            }

            /**
             * Gets the value of the country property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCountry() {
                return country;
            }

            /**
             * Sets the value of the country property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCountry(String value) {
                this.country = value;
            }

            /**
             * Gets the value of the messages property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMessages() {
                return messages;
            }

            /**
             * Sets the value of the messages property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMessages(String value) {
                this.messages = value;
            }

            /**
             * Gets the value of the languageDirection property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLanguageDirection() {
                return languageDirection;
            }

            /**
             * Sets the value of the languageDirection property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLanguageDirection(String value) {
                this.languageDirection = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class Msg {

                @XmlValue
                protected String value;
                @XmlAttribute(required = true)
                protected String name;
                @XmlAttribute
                protected String desc;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the name property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getName() {
                    return name;
                }

                /**
                 * Sets the value of the name property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setName(String value) {
                    this.name = value;
                }

                /**
                 * Gets the value of the desc property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDesc() {
                    return desc;
                }

                /**
                 * Sets the value of the desc property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDesc(String value) {
                    this.desc = value;
                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="Service" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="Request" type="{}OAuthResourceType" minOccurs="0"/>
         *                   &lt;element name="Access" type="{}OAuthResourceType" minOccurs="0"/>
         *                   &lt;element name="Authorization" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="url" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="Rating">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;attribute name="scheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "service",
            "rating"
        })
        public static class OAuth {

            @XmlElement(name = "Service", required = true)
            protected List<Module.ModulePrefs.OAuth.Service> service;
            @XmlElement(name = "Rating", required = true)
            protected Module.ModulePrefs.OAuth.Rating rating;
            @XmlAttribute
            protected String name;

            /**
             * Gets the value of the service property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the service property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getService().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Module.ModulePrefs.OAuth.Service }
             * 
             * 
             */
            public List<Module.ModulePrefs.OAuth.Service> getService() {
                if (service == null) {
                    service = new ArrayList<Module.ModulePrefs.OAuth.Service>();
                }
                return this.service;
            }

            /**
             * Gets the value of the rating property.
             * 
             * @return
             *     possible object is
             *     {@link Module.ModulePrefs.OAuth.Rating }
             *     
             */
            public Module.ModulePrefs.OAuth.Rating getRating() {
                return rating;
            }

            /**
             * Sets the value of the rating property.
             * 
             * @param value
             *     allowed object is
             *     {@link Module.ModulePrefs.OAuth.Rating }
             *     
             */
            public void setRating(Module.ModulePrefs.OAuth.Rating value) {
                this.rating = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;attribute name="scheme" type="{http://www.w3.org/2001/XMLSchema}anyURI" default="" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "")
            public static class Rating {

                @XmlAttribute
                @XmlSchemaType(name = "anyURI")
                protected String scheme;

                /**
                 * Gets the value of the scheme property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getScheme() {
                    if (scheme == null) {
                        return "";
                    } else {
                        return scheme;
                    }
                }

                /**
                 * Sets the value of the scheme property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setScheme(String value) {
                    this.scheme = value;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="Request" type="{}OAuthResourceType" minOccurs="0"/>
             *         &lt;element name="Access" type="{}OAuthResourceType" minOccurs="0"/>
             *         &lt;element name="Authorization" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="url" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "request",
                "access",
                "authorization"
            })
            public static class Service {

                @XmlElement(name = "Request")
                protected OAuthResourceType request;
                @XmlElement(name = "Access")
                protected OAuthResourceType access;
                @XmlElement(name = "Authorization")
                protected Module.ModulePrefs.OAuth.Service.Authorization authorization;

                /**
                 * Gets the value of the request property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link OAuthResourceType }
                 *     
                 */
                public OAuthResourceType getRequest() {
                    return request;
                }

                /**
                 * Sets the value of the request property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link OAuthResourceType }
                 *     
                 */
                public void setRequest(OAuthResourceType value) {
                    this.request = value;
                }

                /**
                 * Gets the value of the access property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link OAuthResourceType }
                 *     
                 */
                public OAuthResourceType getAccess() {
                    return access;
                }

                /**
                 * Sets the value of the access property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link OAuthResourceType }
                 *     
                 */
                public void setAccess(OAuthResourceType value) {
                    this.access = value;
                }

                /**
                 * Gets the value of the authorization property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Module.ModulePrefs.OAuth.Service.Authorization }
                 *     
                 */
                public Module.ModulePrefs.OAuth.Service.Authorization getAuthorization() {
                    return authorization;
                }

                /**
                 * Sets the value of the authorization property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Module.ModulePrefs.OAuth.Service.Authorization }
                 *     
                 */
                public void setAuthorization(Module.ModulePrefs.OAuth.Service.Authorization value) {
                    this.authorization = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="url" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Authorization {

                    @XmlAttribute(required = true)
                    protected String url;

                    /**
                     * Gets the value of the url property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getUrl() {
                        return url;
                    }

                    /**
                     * Sets the value of the url property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setUrl(String value) {
                        this.url = value;
                    }

                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="href" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="authz" default="none">
         *         &lt;simpleType>
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
         *             &lt;enumeration value="none"/>
         *             &lt;enumeration value="signed"/>
         *             &lt;enumeration value="oauth"/>
         *           &lt;/restriction>
         *         &lt;/simpleType>
         *       &lt;/attribute>
         *       &lt;attribute name="sign_owner" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
         *       &lt;attribute name="sign_viewer" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
         *       &lt;attribute name="views" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="oauth_service_name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="oauth_token_name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="oauth_request_token" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="oauth_request_token_secret" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Preload {

            @XmlAttribute(required = true)
            protected String href;
            @XmlAttribute
            protected String authz;
            @XmlAttribute(name = "sign_owner")
            protected Boolean signOwner;
            @XmlAttribute(name = "sign_viewer")
            protected Boolean signViewer;
            @XmlAttribute
            protected String views;
            @XmlAttribute(name = "oauth_service_name")
            protected String oauthServiceName;
            @XmlAttribute(name = "oauth_token_name")
            protected String oauthTokenName;
            @XmlAttribute(name = "oauth_request_token")
            protected String oauthRequestToken;
            @XmlAttribute(name = "oauth_request_token_secret")
            protected String oauthRequestTokenSecret;

            /**
             * Gets the value of the href property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getHref() {
                return href;
            }

            /**
             * Sets the value of the href property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setHref(String value) {
                this.href = value;
            }

            /**
             * Gets the value of the authz property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAuthz() {
                if (authz == null) {
                    return "none";
                } else {
                    return authz;
                }
            }

            /**
             * Sets the value of the authz property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAuthz(String value) {
                this.authz = value;
            }

            /**
             * Gets the value of the signOwner property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public boolean isSignOwner() {
                if (signOwner == null) {
                    return true;
                } else {
                    return signOwner;
                }
            }

            /**
             * Sets the value of the signOwner property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSignOwner(Boolean value) {
                this.signOwner = value;
            }

            /**
             * Gets the value of the signViewer property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public boolean isSignViewer() {
                if (signViewer == null) {
                    return true;
                } else {
                    return signViewer;
                }
            }

            /**
             * Sets the value of the signViewer property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSignViewer(Boolean value) {
                this.signViewer = value;
            }

            /**
             * Gets the value of the views property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getViews() {
                return views;
            }

            /**
             * Sets the value of the views property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setViews(String value) {
                this.views = value;
            }

            /**
             * Gets the value of the oauthServiceName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOauthServiceName() {
                return oauthServiceName;
            }

            /**
             * Sets the value of the oauthServiceName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOauthServiceName(String value) {
                this.oauthServiceName = value;
            }

            /**
             * Gets the value of the oauthTokenName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOauthTokenName() {
                return oauthTokenName;
            }

            /**
             * Sets the value of the oauthTokenName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOauthTokenName(String value) {
                this.oauthTokenName = value;
            }

            /**
             * Gets the value of the oauthRequestToken property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOauthRequestToken() {
                return oauthRequestToken;
            }

            /**
             * Sets the value of the oauthRequestToken property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOauthRequestToken(String value) {
                this.oauthRequestToken = value;
            }

            /**
             * Gets the value of the oauthRequestTokenSecret property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getOauthRequestTokenSecret() {
                return oauthRequestTokenSecret;
            }

            /**
             * Sets the value of the oauthRequestTokenSecret property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setOauthRequestTokenSecret(String value) {
                this.oauthRequestTokenSecret = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="EnumValue" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="display_value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="display_name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="default_value" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="datatype" default="string">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *             &lt;enumeration value="string"/>
     *             &lt;enumeration value="hidden"/>
     *             &lt;enumeration value="bool"/>
     *             &lt;enumeration value="enum"/>
     *             &lt;enumeration value="list"/>
     *             &lt;enumeration value="number"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "enumValue"
    })
    public static class UserPref {

        @XmlElement(name = "EnumValue")
        protected List<Module.UserPref.EnumValue> enumValue;
        @XmlAttribute(required = true)
        protected String name;
        @XmlAttribute(name = "display_name")
        protected String displayName;
        @XmlAttribute(name = "default_value")
        protected String defaultValue;
        @XmlAttribute
        protected String required;
        @XmlAttribute
        protected String datatype;

        /**
         * Gets the value of the enumValue property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the enumValue property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEnumValue().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Module.UserPref.EnumValue }
         * 
         * 
         */
        public List<Module.UserPref.EnumValue> getEnumValue() {
            if (enumValue == null) {
                enumValue = new ArrayList<Module.UserPref.EnumValue>();
            }
            return this.enumValue;
        }

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setName(String value) {
            this.name = value;
        }

        /**
         * Gets the value of the displayName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDisplayName() {
            return displayName;
        }

        /**
         * Sets the value of the displayName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDisplayName(String value) {
            this.displayName = value;
        }

        /**
         * Gets the value of the defaultValue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDefaultValue() {
            return defaultValue;
        }

        /**
         * Sets the value of the defaultValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDefaultValue(String value) {
            this.defaultValue = value;
        }

        /**
         * Gets the value of the required property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRequired() {
            return required;
        }

        /**
         * Sets the value of the required property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRequired(String value) {
            this.required = value;
        }

        /**
         * Gets the value of the datatype property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDatatype() {
            if (datatype == null) {
                return "string";
            } else {
                return datatype;
            }
        }

        /**
         * Sets the value of the datatype property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDatatype(String value) {
            this.datatype = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="value" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="display_value" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class EnumValue {

            @XmlAttribute(required = true)
            protected String value;
            @XmlAttribute(name = "display_value")
            protected String displayValue;

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Gets the value of the displayValue property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDisplayValue() {
                return displayValue;
            }

            /**
             * Sets the value of the displayValue property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDisplayValue(String value) {
                this.displayValue = value;
            }

        }

    }

}
