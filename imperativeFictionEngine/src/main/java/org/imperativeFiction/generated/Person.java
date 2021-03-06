//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 07:21:03 PM CEST 
//


package org.imperativeFiction.generated;

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
 * <p>Java class for person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="person"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}objectType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="personStatuses" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="hunger" type="{}hunger"/&gt;
 *         &lt;element name="armour" type="{}armourType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "person", propOrder = {
    "personStatuses",
    "hunger",
    "armour"
})
public class Person
    extends ObjectType
    implements Equals, ToString
{

    @XmlElement(required = true)
    protected String personStatuses;
    @XmlElement(required = true)
    protected Hunger hunger;
    @XmlElement(required = true)
    protected ArmourType armour;

    /**
     * Gets the value of the personStatuses property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonStatuses() {
        return personStatuses;
    }

    /**
     * Sets the value of the personStatuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonStatuses(String value) {
        this.personStatuses = value;
    }

    /**
     * Gets the value of the hunger property.
     * 
     * @return
     *     possible object is
     *     {@link Hunger }
     *     
     */
    public Hunger getHunger() {
        return hunger;
    }

    /**
     * Sets the value of the hunger property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hunger }
     *     
     */
    public void setHunger(Hunger value) {
        this.hunger = value;
    }

    /**
     * Gets the value of the armour property.
     * 
     * @return
     *     possible object is
     *     {@link ArmourType }
     *     
     */
    public ArmourType getArmour() {
        return armour;
    }

    /**
     * Sets the value of the armour property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArmourType }
     *     
     */
    public void setArmour(ArmourType value) {
        this.armour = value;
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
            String thePersonStatuses;
            thePersonStatuses = this.getPersonStatuses();
            strategy.appendField(locator, this, "personStatuses", buffer, thePersonStatuses);
        }
        {
            Hunger theHunger;
            theHunger = this.getHunger();
            strategy.appendField(locator, this, "hunger", buffer, theHunger);
        }
        {
            ArmourType theArmour;
            theArmour = this.getArmour();
            strategy.appendField(locator, this, "armour", buffer, theArmour);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Person)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Person that = ((Person) object);
        {
            String lhsPersonStatuses;
            lhsPersonStatuses = this.getPersonStatuses();
            String rhsPersonStatuses;
            rhsPersonStatuses = that.getPersonStatuses();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "personStatuses", lhsPersonStatuses), LocatorUtils.property(thatLocator, "personStatuses", rhsPersonStatuses), lhsPersonStatuses, rhsPersonStatuses)) {
                return false;
            }
        }
        {
            Hunger lhsHunger;
            lhsHunger = this.getHunger();
            Hunger rhsHunger;
            rhsHunger = that.getHunger();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hunger", lhsHunger), LocatorUtils.property(thatLocator, "hunger", rhsHunger), lhsHunger, rhsHunger)) {
                return false;
            }
        }
        {
            ArmourType lhsArmour;
            lhsArmour = this.getArmour();
            ArmourType rhsArmour;
            rhsArmour = that.getArmour();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "armour", lhsArmour), LocatorUtils.property(thatLocator, "armour", rhsArmour), lhsArmour, rhsArmour)) {
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
