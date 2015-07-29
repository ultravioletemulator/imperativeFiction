//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 05:48:08 PM CEST 
//


package org.imperativeFiction.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for door complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="door"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}path"&gt;
 *       &lt;attribute name="doorStatus" use="required" type="{}objectStatus" /&gt;
 *       &lt;attribute name="openWithObject" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "door")
public class Door
    extends Path
    implements Equals, ToString
{

    @XmlAttribute(name = "doorStatus", required = true)
    protected ObjectStatus doorStatus;
    @XmlAttribute(name = "openWithObject")
    protected String openWithObject;

    /**
     * Gets the value of the doorStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectStatus }
     *     
     */
    public ObjectStatus getDoorStatus() {
        return doorStatus;
    }

    /**
     * Sets the value of the doorStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectStatus }
     *     
     */
    public void setDoorStatus(ObjectStatus value) {
        this.doorStatus = value;
    }

    /**
     * Gets the value of the openWithObject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenWithObject() {
        return openWithObject;
    }

    /**
     * Sets the value of the openWithObject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenWithObject(String value) {
        this.openWithObject = value;
    }

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        super.appendFields(locator, buffer, strategy);
        {
            ObjectStatus theDoorStatus;
            theDoorStatus = this.getDoorStatus();
            strategy.appendField(locator, this, "doorStatus", buffer, theDoorStatus);
        }
        {
            String theOpenWithObject;
            theOpenWithObject = this.getOpenWithObject();
            strategy.appendField(locator, this, "openWithObject", buffer, theOpenWithObject);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Door)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Door that = ((Door) object);
        {
            ObjectStatus lhsDoorStatus;
            lhsDoorStatus = this.getDoorStatus();
            ObjectStatus rhsDoorStatus;
            rhsDoorStatus = that.getDoorStatus();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "doorStatus", lhsDoorStatus), LocatorUtils.property(thatLocator, "doorStatus", rhsDoorStatus), lhsDoorStatus, rhsDoorStatus)) {
                return false;
            }
        }
        {
            String lhsOpenWithObject;
            lhsOpenWithObject = this.getOpenWithObject();
            String rhsOpenWithObject;
            rhsOpenWithObject = that.getOpenWithObject();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "openWithObject", lhsOpenWithObject), LocatorUtils.property(thatLocator, "openWithObject", rhsOpenWithObject), lhsOpenWithObject, rhsOpenWithObject)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
