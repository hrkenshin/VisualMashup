
package org.uengine.visualmashup.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OperatorEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OperatorEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Between"/>
 *     &lt;enumeration value="Equal"/>
 *     &lt;enumeration value="GreaterThan"/>
 *     &lt;enumeration value="GreaterThanOrEqual"/>
 *     &lt;enumeration value="LessThan"/>
 *     &lt;enumeration value="LessThanOrEqual"/>
 *     &lt;enumeration value="Like"/>
 *     &lt;enumeration value="NotEqual"/>
 *     &lt;enumeration value="IsNull"/>
 *     &lt;enumeration value="IsNotNull"/>
 *     &lt;enumeration value="In"/>
 *     &lt;enumeration value="Custom"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OperatorEnum")
@XmlEnum
public enum OperatorEnum {

    @XmlEnumValue("Between")
    BETWEEN("Between"),
    @XmlEnumValue("Equal")
    EQUAL("Equal"),
    @XmlEnumValue("GreaterThan")
    GREATER_THAN("GreaterThan"),
    @XmlEnumValue("GreaterThanOrEqual")
    GREATER_THAN_OR_EQUAL("GreaterThanOrEqual"),
    @XmlEnumValue("LessThan")
    LESS_THAN("LessThan"),
    @XmlEnumValue("LessThanOrEqual")
    LESS_THAN_OR_EQUAL("LessThanOrEqual"),
    @XmlEnumValue("Like")
    LIKE("Like"),
    @XmlEnumValue("NotEqual")
    NOT_EQUAL("NotEqual"),
    @XmlEnumValue("IsNull")
    IS_NULL("IsNull"),
    @XmlEnumValue("IsNotNull")
    IS_NOT_NULL("IsNotNull"),
    @XmlEnumValue("In")
    IN("In"),
    @XmlEnumValue("Custom")
    CUSTOM("Custom");
    private final String value;

    OperatorEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OperatorEnum fromValue(String v) {
        for (OperatorEnum c: OperatorEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
