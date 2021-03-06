
package org.uengine.visualmashup.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element ref="{}_Entity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="isSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="totalCnt" type="{http://www.w3.org/2001/XMLSchema}int" default="0" />
 *       &lt;attribute name="message" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
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
@XmlRootElement(name = "SearchResponse")
public class SearchResponse {

    @XmlElementRef(name = "_Entity", type = JAXBElement.class)
    protected List<JAXBElement<? extends Entity>> entity;
    @XmlAttribute
    protected Boolean isSuccess;
    @XmlAttribute
    protected Integer totalCnt;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String message;

    /**
     * Gets the value of the entity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link VmGadgets }{@code >}
     * {@link JAXBElement }{@code <}{@link Entity }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Entity>> getEntity() {
        if (entity == null) {
            entity = new ArrayList<JAXBElement<? extends Entity>>();
        }
        return this.entity;
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
     * Gets the value of the totalCnt property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public int getTotalCnt() {
        if (totalCnt == null) {
            return  0;
        } else {
            return totalCnt;
        }
    }

    /**
     * Sets the value of the totalCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalCnt(Integer value) {
        this.totalCnt = value;
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
