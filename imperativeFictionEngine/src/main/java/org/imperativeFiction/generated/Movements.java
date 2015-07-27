//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 06:57:31 PM CEST 
//


package org.imperativeFiction.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for movements.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="movements"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="north"/&gt;
 *     &lt;enumeration value="south"/&gt;
 *     &lt;enumeration value="east"/&gt;
 *     &lt;enumeration value="west"/&gt;
 *     &lt;enumeration value="northeast"/&gt;
 *     &lt;enumeration value="northwest"/&gt;
 *     &lt;enumeration value="southeast"/&gt;
 *     &lt;enumeration value="southwest"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "movements")
@XmlEnum
public enum Movements {

    @XmlEnumValue("north")
    NORTH("north"),
    @XmlEnumValue("south")
    SOUTH("south"),
    @XmlEnumValue("east")
    EAST("east"),
    @XmlEnumValue("west")
    WEST("west"),
    @XmlEnumValue("northeast")
    NORTHEAST("northeast"),
    @XmlEnumValue("northwest")
    NORTHWEST("northwest"),
    @XmlEnumValue("southeast")
    SOUTHEAST("southeast"),
    @XmlEnumValue("southwest")
    SOUTHWEST("southwest");
    private final String value;

    Movements(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Movements fromValue(String v) {
        for (Movements c: Movements.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
