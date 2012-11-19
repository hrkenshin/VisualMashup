
package org.uengine.visualmashup.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * Wire Terminal Type
 * 
 * <p>Java class for TerminalType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TerminalType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="gadgetId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="topicName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TerminalType")
public class TerminalType {

    @XmlAttribute(required = true)
    protected String gadgetId;
    @XmlAttribute(required = true)
    protected String topicName;

    /**
     * Gets the value of the gadgetId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGadgetId() {
        return gadgetId;
    }

    /**
     * Sets the value of the gadgetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGadgetId(String value) {
        this.gadgetId = value;
    }

    /**
     * Gets the value of the topicName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * Sets the value of the topicName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopicName(String value) {
        this.topicName = value;
    }

}
