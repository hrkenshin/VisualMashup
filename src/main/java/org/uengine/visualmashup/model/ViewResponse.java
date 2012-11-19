
package org.uengine.visualmashup.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element ref="{}_Entity" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "entity"
})
@XmlRootElement(name = "ViewResponse")
public class ViewResponse {

    @XmlElementRef(name = "_Entity", type = JAXBElement.class)
    protected JAXBElement<? extends Entity> entity;
    @XmlAttribute
    protected Boolean isSuccess;
    @XmlAttribute
    protected String message;

    /**
     * Gets the value of the entity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link VmGadgets }{@code >}
     *     {@link JAXBElement }{@code <}{@link Entity }{@code >}
     *     
     */
    public JAXBElement<? extends Entity> getEntity() {
        return entity;
    }

    /**
     * Sets the value of the entity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link VmGadgets }{@code >}
     *     {@link JAXBElement }{@code <}{@link Entity }{@code >}
     *     
     */
    public void setEntity(JAXBElement<? extends Entity> value) {
        this.entity = ((JAXBElement<? extends Entity> ) value);
    }

    /**
     * Gets the value of the isSuccess property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIsSuccess() {
        if (isSuccess == null) {
            return true;
        } else {
            return isSuccess;
        }
    }

    /**
     * Sets the value of the isSuccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSuccess(Boolean value) {
        this.isSuccess = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

}
