
package org.uengine.visualmashup.model;

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
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="fieldName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fieldType" type="{}FieldTypeEnum" default="String" />
 *       &lt;attribute name="operator" type="{}OperatorEnum" default="Equal" />
 *       &lt;attribute name="delimiter" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" default="," />
 *       &lt;attribute name="dateFormat" type="{http://www.w3.org/2001/XMLSchema}string" default="yyyy-MM-dd HH:mm:ss" />
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
@XmlRootElement(name = "condition")
public class Condition {

    @XmlValue
    protected String value;
    @XmlAttribute(required = true)
    protected String fieldName;
    @XmlAttribute
    protected FieldTypeEnum fieldType;
    @XmlAttribute
    protected OperatorEnum operator;
    @XmlAttribute
    @XmlSchemaType(name = "anySimpleType")
    protected String delimiter;
    @XmlAttribute
    protected String dateFormat;

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
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the fieldType property.
     * 
     * @return
     *     possible object is
     *     {@link FieldTypeEnum }
     *     
     */
    public FieldTypeEnum getFieldType() {
        if (fieldType == null) {
            return FieldTypeEnum.STRING;
        } else {
            return fieldType;
        }
    }

    /**
     * Sets the value of the fieldType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldTypeEnum }
     *     
     */
    public void setFieldType(FieldTypeEnum value) {
        this.fieldType = value;
    }

    /**
     * Gets the value of the operator property.
     * 
     * @return
     *     possible object is
     *     {@link OperatorEnum }
     *     
     */
    public OperatorEnum getOperator() {
        if (operator == null) {
            return OperatorEnum.EQUAL;
        } else {
            return operator;
        }
    }

    /**
     * Sets the value of the operator property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperatorEnum }
     *     
     */
    public void setOperator(OperatorEnum value) {
        this.operator = value;
    }

    /**
     * Gets the value of the delimiter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelimiter() {
        if (delimiter == null) {
            return ",";
        } else {
            return delimiter;
        }
    }

    /**
     * Sets the value of the delimiter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelimiter(String value) {
        this.delimiter = value;
    }

    /**
     * Gets the value of the dateFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateFormat() {
        if (dateFormat == null) {
            return "yyyy-MM-dd HH:mm:ss";
        } else {
            return dateFormat;
        }
    }

    /**
     * Sets the value of the dateFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateFormat(String value) {
        this.dateFormat = value;
    }

}
