
package org.uengine.visualmashup.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FieldTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="String"/>
 *     &lt;enumeration value="Date"/>
 *     &lt;enumeration value="Int"/>
 *     &lt;enumeration value="Long"/>
 *     &lt;enumeration value="Float"/>
 *     &lt;enumeration value="Boolean"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FieldTypeEnum")
@XmlEnum
public enum FieldTypeEnum {

    @XmlEnumValue("String")
    STRING("String"),
    @XmlEnumValue("Date")
    DATE("Date"),
    @XmlEnumValue("Int")
    INT("Int"),
    @XmlEnumValue("Long")
    LONG("Long"),
    @XmlEnumValue("Float")
    FLOAT("Float"),
    @XmlEnumValue("Boolean")
    BOOLEAN("Boolean");
    private final String value;

    FieldTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FieldTypeEnum fromValue(String v) {
        for (FieldTypeEnum c: FieldTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
