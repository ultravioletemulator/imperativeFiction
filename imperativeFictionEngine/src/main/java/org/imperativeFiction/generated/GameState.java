//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 06:57:31 PM CEST 
//


package org.imperativeFiction.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="location" type="{}location"/&gt;
 *         &lt;element name="inventory" type="{}inventory"/&gt;
 *         &lt;element name="characterState" type="{}characterState"/&gt;
 *         &lt;element name="gainedGoals" type="{}gainedGoals"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "location",
    "inventory",
    "characterState",
    "gainedGoals"
})
@XmlRootElement(name = "gameState")
public class GameState
    implements Equals, ToString
{

    @XmlElement(required = true)
    protected Location location;
    @XmlElement(required = true)
    protected Inventory inventory;
    @XmlElement(required = true)
    protected CharacterState characterState;
    @XmlElement(required = true)
    protected GainedGoals gainedGoals;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
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
     * Gets the value of the characterState property.
     * 
     * @return
     *     possible object is
     *     {@link CharacterState }
     *     
     */
    public CharacterState getCharacterState() {
        return characterState;
    }

    /**
     * Sets the value of the characterState property.
     * 
     * @param value
     *     allowed object is
     *     {@link CharacterState }
     *     
     */
    public void setCharacterState(CharacterState value) {
        this.characterState = value;
    }

    /**
     * Gets the value of the gainedGoals property.
     * 
     * @return
     *     possible object is
     *     {@link GainedGoals }
     *     
     */
    public GainedGoals getGainedGoals() {
        return gainedGoals;
    }

    /**
     * Sets the value of the gainedGoals property.
     * 
     * @param value
     *     allowed object is
     *     {@link GainedGoals }
     *     
     */
    public void setGainedGoals(GainedGoals value) {
        this.gainedGoals = value;
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
            Location theLocation;
            theLocation = this.getLocation();
            strategy.appendField(locator, this, "location", buffer, theLocation);
        }
        {
            Inventory theInventory;
            theInventory = this.getInventory();
            strategy.appendField(locator, this, "inventory", buffer, theInventory);
        }
        {
            CharacterState theCharacterState;
            theCharacterState = this.getCharacterState();
            strategy.appendField(locator, this, "characterState", buffer, theCharacterState);
        }
        {
            GainedGoals theGainedGoals;
            theGainedGoals = this.getGainedGoals();
            strategy.appendField(locator, this, "gainedGoals", buffer, theGainedGoals);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GameState)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GameState that = ((GameState) object);
        {
            Location lhsLocation;
            lhsLocation = this.getLocation();
            Location rhsLocation;
            rhsLocation = that.getLocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "location", lhsLocation), LocatorUtils.property(thatLocator, "location", rhsLocation), lhsLocation, rhsLocation)) {
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
            CharacterState lhsCharacterState;
            lhsCharacterState = this.getCharacterState();
            CharacterState rhsCharacterState;
            rhsCharacterState = that.getCharacterState();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "characterState", lhsCharacterState), LocatorUtils.property(thatLocator, "characterState", rhsCharacterState), lhsCharacterState, rhsCharacterState)) {
                return false;
            }
        }
        {
            GainedGoals lhsGainedGoals;
            lhsGainedGoals = this.getGainedGoals();
            GainedGoals rhsGainedGoals;
            rhsGainedGoals = that.getGainedGoals();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gainedGoals", lhsGainedGoals), LocatorUtils.property(thatLocator, "gainedGoals", rhsGainedGoals), lhsGainedGoals, rhsGainedGoals)) {
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
