//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 07:21:03 PM CEST 
//


package org.imperativeFiction.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for objectStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="objectStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="hidden"/&gt;
 *     &lt;enumeration value="visible"/&gt;
 *     &lt;enumeration value="broken"/&gt;
 *     &lt;enumeration value="open"/&gt;
 *     &lt;enumeration value="closed"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "objectStatus")
@XmlEnum
public enum ObjectStatus {

    @XmlEnumValue("hidden")
    HIDDEN("hidden"),
    @XmlEnumValue("visible")
    VISIBLE("visible"),
    @XmlEnumValue("broken")
    BROKEN("broken"),
    @XmlEnumValue("open")
    OPEN("open"),
    @XmlEnumValue("closed")
    CLOSED("closed");
    private final String value;

    ObjectStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectStatus fromValue(String v) {
        for (ObjectStatus c: ObjectStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
