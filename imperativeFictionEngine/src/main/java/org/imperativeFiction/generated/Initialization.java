//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 06:57:31 PM CEST 
//


package org.imperativeFiction.generated;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for initialization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="initialization"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="life" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/&gt;
 *         &lt;element name="inventory" type="{}inventory"/&gt;
 *         &lt;element name="initialLocationName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "initialization", propOrder = {
    "life",
    "inventory",
    "initialLocationName"
})
public class Initialization
    implements Equals, ToString
{

    protected BigInteger life;
    @XmlElement(required = true)
    protected Inventory inventory;
    @XmlElement(required = true)
    protected String initialLocationName;

    /**
     * Gets the value of the life property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLife() {
        return life;
    }

    /**
     * Sets the value of the life property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLife(BigInteger value) {
        this.life = value;
    }

    /**
     * Gets the value of the inventory property.
     * 
     * @return
     *     possible object is
     *     {@link Inventory }
     *     
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Sets the value of the inventory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Inventory }
     *     
     */
    public void setInventory(Inventory value) {
        this.inventory = value;
    }

    /**
     * Gets the value of the initialLocationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInitialLocationName() {
        return initialLocationName;
    }

    /**
     * Sets the value of the initialLocationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInitialLocationName(String value) {
        this.initialLocationName = value;
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
        {
            BigInteger theLife;
            theLife = this.getLife();
            strategy.appendField(locator, this, "life", buffer, theLife);
        }
        {
            Inventory theInventory;
            theInventory = this.getInventory();
            strategy.appendField(locator, this, "inventory", buffer, theInventory);
        }
        {
            String theInitialLocationName;
            theInitialLocationName = this.getInitialLocationName();
            strategy.appendField(locator, this, "initialLocationName", buffer, theInitialLocationName);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Initialization)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Initialization that = ((Initialization) object);
        {
            BigInteger lhsLife;
            lhsLife = this.getLife();
            BigInteger rhsLife;
            rhsLife = that.getLife();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "life", lhsLife), LocatorUtils.property(thatLocator, "life", rhsLife), lhsLife, rhsLife)) {
                return false;
            }
        }
        {
            Inventory lhsInventory;
            lhsInventory = this.getInventory();
            Inventory rhsInventory;
            rhsInventory = that.getInventory();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inventory", lhsInventory), LocatorUtils.property(thatLocator, "inventory", rhsInventory), lhsInventory, rhsInventory)) {
                return false;
            }
        }
        {
            String lhsInitialLocationName;
            lhsInitialLocationName = this.getInitialLocationName();
            String rhsInitialLocationName;
            rhsInitialLocationName = that.getInitialLocationName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "initialLocationName", lhsInitialLocationName), LocatorUtils.property(thatLocator, "initialLocationName", rhsInitialLocationName), lhsInitialLocationName, rhsInitialLocationName)) {
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
