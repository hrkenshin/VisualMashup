
package org.uengine.visualmashup.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;choice>
 *           &lt;element name="initConfig" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           &lt;element name="html" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="userPref" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="position" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;attribute name="x" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *                 &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xtype" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="portlet"/>
 *             &lt;enumeration value="gadget"/>
 *             &lt;enumeration value="transformer"/>
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
    "initConfig",
    "url",
    "html",
    "userPref",
    "position"
})
@XmlRootElement(name = "portlet")
public class Portlet {

    protected String initConfig;
    @XmlSchemaType(name = "anyURI")
    protected String url;
    protected String html;
    protected List<Portlet.UserPref> userPref;
    protected Portlet.Position position;
    @XmlAttribute(required = true)
    protected String id;
    @XmlAttribute(required = true)
    protected String xtype;

    /**
     * Gets the value of the initConfig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitConfig() {
        return initConfig;
    }

    /**
     * Sets the value of the initConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitConfig(String value) {
        this.initConfig = value;
    }

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

    /**
     * Gets the value of the html property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtml() {
        return html;
    }

    /**
     * Sets the value of the html property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtml(String value) {
        this.html = value;
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
     * {@link Portlet.UserPref }
     * 
     * 
     */
    public List<Portlet.UserPref> getUserPref() {
        if (userPref == null) {
            userPref = new ArrayList<Portlet.UserPref>();
        }
        return this.userPref;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link Portlet.Position }
     *     
     */
    public Portlet.Position getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link Portlet.Position }
     *     
     */
    public void setPosition(Portlet.Position value) {
        this.position = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the xtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXtype() {
        return xtype;
    }

    /**
     * Sets the value of the xtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXtype(String value) {
        this.xtype = value;
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
     *       &lt;attribute name="x" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *       &lt;attribute name="y" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Position {

        @XmlAttribute(required = true)
        protected int x;
        @XmlAttribute(required = true)
        protected int y;

        /**
         * Gets the value of the x property.
         * 
         */
        public int getX() {
            return x;
        }

        /**
         * Sets the value of the x property.
         * 
         */
        public void setX(int value) {
            this.x = value;
        }

        /**
         * Gets the value of the y property.
         * 
         */
        public int getY() {
            return y;
        }

        /**
         * Sets the value of the y property.
         * 
         */
        public void setY(int value) {
            this.y = value;
        }

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
    public static class UserPref {

        @XmlValue
        protected String value;
        @XmlAttribute(required = true)
        protected String name;

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

    }

}
