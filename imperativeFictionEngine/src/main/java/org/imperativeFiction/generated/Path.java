//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 09:38:31 AM CEST 
//


package org.imperativeFiction.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for path complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="path"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}boundary"&gt;
 *       &lt;attribute name="toLocation" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="fromLocation" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "path")
@XmlSeeAlso({
    Door.class
})
public class Path
    extends Boundary
    implements Equals, ToString
{

    @XmlAttribute(name = "toLocation", required = true)
    protected String toLocation;
    @XmlAttribute(name = "fromLocation", required = true)
    protected String fromLocation;

    /**
     * Gets the value of the toLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToLocation() {
        return toLocation;
    }

    /**
     * Sets the value of the toLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToLocation(String value) {
        this.toLocation = value;
    }

    /**
     * Gets the value of the fromLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromLocation() {
        return fromLocation;
    }

    /**
     * Sets the value of the fromLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromLocation(String value) {
        this.fromLocation = value;
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
            String theToLocation;
            theToLocation = this.getToLocation();
            strategy.appendField(locator, this, "toLocation", buffer, theToLocation);
        }
        {
            String theFromLocation;
            theFromLocation = this.getFromLocation();
            strategy.appendField(locator, this, "fromLocation", buffer, theFromLocation);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Path)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Path that = ((Path) object);
        {
            String lhsToLocation;
            lhsToLocation = this.getToLocation();
            String rhsToLocation;
            rhsToLocation = that.getToLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "toLocation", lhsToLocation), LocatorUtils.property(thatLocator, "toLocation", rhsToLocation), lhsToLocation, rhsToLocation)) {
                return false;
            }
        }
        {
            String lhsFromLocation;
            lhsFromLocation = this.getFromLocation();
            String rhsFromLocation;
            rhsFromLocation = that.getFromLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fromLocation", lhsFromLocation), LocatorUtils.property(thatLocator, "fromLocation", rhsFromLocation), lhsFromLocation, rhsFromLocation)) {
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
