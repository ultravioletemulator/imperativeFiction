//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 09:18:54 AM CEST 
//


package org.imperativeFiction.generated;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}genericActions"/&gt;
 *         &lt;element ref="{}doors"/&gt;
 *         &lt;element ref="{}locations"/&gt;
 *         &lt;element ref="{}gameGoals"/&gt;
 *         &lt;element ref="{}gameObjects"/&gt;
 *         &lt;element ref="{}gameObjectCombinations"/&gt;
 *         &lt;element name="gameWeapons" type="{}gameWeapons" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{}gameObjectPlacements"/&gt;
 *         &lt;element name="gameAutomaticActions" type="{}gameAutomaticActions"/&gt;
 *         &lt;element name="gameMessages" type="{}gameMessages" minOccurs="0"/&gt;
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
    "genericActions",
    "doors",
    "locations",
    "gameGoals",
    "gameObjects",
    "gameObjectCombinations",
    "gameWeapons",
    "gameObjectPlacements",
    "gameAutomaticActions",
    "gameMessages"
})
@XmlRootElement(name = "definition")
public class Definition
    implements Equals, ToString
{

    @XmlElement(required = true)
    protected GenericActions genericActions;
    @XmlElement(required = true)
    protected Doors doors;
    @XmlElement(required = true)
    protected Locations locations;
    @XmlElement(required = true)
    protected GameGoals gameGoals;
    @XmlElement(required = true)
    protected GameObjects gameObjects;
    @XmlElement(required = true)
    protected GameObjectCombinations gameObjectCombinations;
    @XmlElement(required = true)
    protected List<GameWeapons> gameWeapons;
    @XmlElement(required = true)
    protected GameObjectPlacements gameObjectPlacements;
    @XmlElement(required = true)
    protected GameAutomaticActions gameAutomaticActions;
    protected GameMessages gameMessages;

    /**
     * Gets the value of the genericActions property.
     * 
     * @return
     *     possible object is
     *     {@link GenericActions }
     *     
     */
    public GenericActions getGenericActions() {
        return genericActions;
    }

    /**
     * Sets the value of the genericActions property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericActions }
     *     
     */
    public void setGenericActions(GenericActions value) {
        this.genericActions = value;
    }

    /**
     * Gets the value of the doors property.
     * 
     * @return
     *     possible object is
     *     {@link Doors }
     *     
     */
    public Doors getDoors() {
        return doors;
    }

    /**
     * Sets the value of the doors property.
     * 
     * @param value
     *     allowed object is
     *     {@link Doors }
     *     
     */
    public void setDoors(Doors value) {
        this.doors = value;
    }

    /**
     * Gets the value of the locations property.
     * 
     * @return
     *     possible object is
     *     {@link Locations }
     *     
     */
    public Locations getLocations() {
        return locations;
    }

    /**
     * Sets the value of the locations property.
     * 
     * @param value
     *     allowed object is
     *     {@link Locations }
     *     
     */
    public void setLocations(Locations value) {
        this.locations = value;
    }

    /**
     * Gets the value of the gameGoals property.
     * 
     * @return
     *     possible object is
     *     {@link GameGoals }
     *     
     */
    public GameGoals getGameGoals() {
        return gameGoals;
    }

    /**
     * Sets the value of the gameGoals property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameGoals }
     *     
     */
    public void setGameGoals(GameGoals value) {
        this.gameGoals = value;
    }

    /**
     * Gets the value of the gameObjects property.
     * 
     * @return
     *     possible object is
     *     {@link GameObjects }
     *     
     */
    public GameObjects getGameObjects() {
        return gameObjects;
    }

    /**
     * Sets the value of the gameObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameObjects }
     *     
     */
    public void setGameObjects(GameObjects value) {
        this.gameObjects = value;
    }

    /**
     * Gets the value of the gameObjectCombinations property.
     * 
     * @return
     *     possible object is
     *     {@link GameObjectCombinations }
     *     
     */
    public GameObjectCombinations getGameObjectCombinations() {
        return gameObjectCombinations;
    }

    /**
     * Sets the value of the gameObjectCombinations property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameObjectCombinations }
     *     
     */
    public void setGameObjectCombinations(GameObjectCombinations value) {
        this.gameObjectCombinations = value;
    }

    /**
     * Gets the value of the gameWeapons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gameWeapons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGameWeapons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GameWeapons }
     * 
     * 
     */
    public List<GameWeapons> getGameWeapons() {
        if (gameWeapons == null) {
            gameWeapons = new ArrayList<GameWeapons>();
        }
        return this.gameWeapons;
    }

    /**
     * Gets the value of the gameObjectPlacements property.
     * 
     * @return
     *     possible object is
     *     {@link GameObjectPlacements }
     *     
     */
    public GameObjectPlacements getGameObjectPlacements() {
        return gameObjectPlacements;
    }

    /**
     * Sets the value of the gameObjectPlacements property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameObjectPlacements }
     *     
     */
    public void setGameObjectPlacements(GameObjectPlacements value) {
        this.gameObjectPlacements = value;
    }

    /**
     * Gets the value of the gameAutomaticActions property.
     * 
     * @return
     *     possible object is
     *     {@link GameAutomaticActions }
     *     
     */
    public GameAutomaticActions getGameAutomaticActions() {
        return gameAutomaticActions;
    }

    /**
     * Sets the value of the gameAutomaticActions property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameAutomaticActions }
     *     
     */
    public void setGameAutomaticActions(GameAutomaticActions value) {
        this.gameAutomaticActions = value;
    }

    /**
     * Gets the value of the gameMessages property.
     * 
     * @return
     *     possible object is
     *     {@link GameMessages }
     *     
     */
    public GameMessages getGameMessages() {
        return gameMessages;
    }

    /**
     * Sets the value of the gameMessages property.
     * 
     * @param value
     *     allowed object is
     *     {@link GameMessages }
     *     
     */
    public void setGameMessages(GameMessages value) {
        this.gameMessages = value;
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
            GenericActions theGenericActions;
            theGenericActions = this.getGenericActions();
            strategy.appendField(locator, this, "genericActions", buffer, theGenericActions);
        }
        {
            Doors theDoors;
            theDoors = this.getDoors();
            strategy.appendField(locator, this, "doors", buffer, theDoors);
        }
        {
            Locations theLocations;
            theLocations = this.getLocations();
            strategy.appendField(locator, this, "locations", buffer, theLocations);
        }
        {
            GameGoals theGameGoals;
            theGameGoals = this.getGameGoals();
            strategy.appendField(locator, this, "gameGoals", buffer, theGameGoals);
        }
        {
            GameObjects theGameObjects;
            theGameObjects = this.getGameObjects();
            strategy.appendField(locator, this, "gameObjects", buffer, theGameObjects);
        }
        {
            GameObjectCombinations theGameObjectCombinations;
            theGameObjectCombinations = this.getGameObjectCombinations();
            strategy.appendField(locator, this, "gameObjectCombinations", buffer, theGameObjectCombinations);
        }
        {
            List<GameWeapons> theGameWeapons;
            theGameWeapons = (((this.gameWeapons!= null)&&(!this.gameWeapons.isEmpty()))?this.getGameWeapons():null);
            strategy.appendField(locator, this, "gameWeapons", buffer, theGameWeapons);
        }
        {
            GameObjectPlacements theGameObjectPlacements;
            theGameObjectPlacements = this.getGameObjectPlacements();
            strategy.appendField(locator, this, "gameObjectPlacements", buffer, theGameObjectPlacements);
        }
        {
            GameAutomaticActions theGameAutomaticActions;
            theGameAutomaticActions = this.getGameAutomaticActions();
            strategy.appendField(locator, this, "gameAutomaticActions", buffer, theGameAutomaticActions);
        }
        {
            GameMessages theGameMessages;
            theGameMessages = this.getGameMessages();
            strategy.appendField(locator, this, "gameMessages", buffer, theGameMessages);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Definition)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Definition that = ((Definition) object);
        {
            GenericActions lhsGenericActions;
            lhsGenericActions = this.getGenericActions();
            GenericActions rhsGenericActions;
            rhsGenericActions = that.getGenericActions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "genericActions", lhsGenericActions), LocatorUtils.property(thatLocator, "genericActions", rhsGenericActions), lhsGenericActions, rhsGenericActions)) {
                return false;
            }
        }
        {
            Doors lhsDoors;
            lhsDoors = this.getDoors();
            Doors rhsDoors;
            rhsDoors = that.getDoors();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "doors", lhsDoors), LocatorUtils.property(thatLocator, "doors", rhsDoors), lhsDoors, rhsDoors)) {
                return false;
            }
        }
        {
            Locations lhsLocations;
            lhsLocations = this.getLocations();
            Locations rhsLocations;
            rhsLocations = that.getLocations();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locations", lhsLocations), LocatorUtils.property(thatLocator, "locations", rhsLocations), lhsLocations, rhsLocations)) {
                return false;
            }
        }
        {
            GameGoals lhsGameGoals;
            lhsGameGoals = this.getGameGoals();
            GameGoals rhsGameGoals;
            rhsGameGoals = that.getGameGoals();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameGoals", lhsGameGoals), LocatorUtils.property(thatLocator, "gameGoals", rhsGameGoals), lhsGameGoals, rhsGameGoals)) {
                return false;
            }
        }
        {
            GameObjects lhsGameObjects;
            lhsGameObjects = this.getGameObjects();
            GameObjects rhsGameObjects;
            rhsGameObjects = that.getGameObjects();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameObjects", lhsGameObjects), LocatorUtils.property(thatLocator, "gameObjects", rhsGameObjects), lhsGameObjects, rhsGameObjects)) {
                return false;
            }
        }
        {
            GameObjectCombinations lhsGameObjectCombinations;
            lhsGameObjectCombinations = this.getGameObjectCombinations();
            GameObjectCombinations rhsGameObjectCombinations;
            rhsGameObjectCombinations = that.getGameObjectCombinations();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameObjectCombinations", lhsGameObjectCombinations), LocatorUtils.property(thatLocator, "gameObjectCombinations", rhsGameObjectCombinations), lhsGameObjectCombinations, rhsGameObjectCombinations)) {
                return false;
            }
        }
        {
            List<GameWeapons> lhsGameWeapons;
            lhsGameWeapons = (((this.gameWeapons!= null)&&(!this.gameWeapons.isEmpty()))?this.getGameWeapons():null);
            List<GameWeapons> rhsGameWeapons;
            rhsGameWeapons = (((that.gameWeapons!= null)&&(!that.gameWeapons.isEmpty()))?that.getGameWeapons():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameWeapons", lhsGameWeapons), LocatorUtils.property(thatLocator, "gameWeapons", rhsGameWeapons), lhsGameWeapons, rhsGameWeapons)) {
                return false;
            }
        }
        {
            GameObjectPlacements lhsGameObjectPlacements;
            lhsGameObjectPlacements = this.getGameObjectPlacements();
            GameObjectPlacements rhsGameObjectPlacements;
            rhsGameObjectPlacements = that.getGameObjectPlacements();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameObjectPlacements", lhsGameObjectPlacements), LocatorUtils.property(thatLocator, "gameObjectPlacements", rhsGameObjectPlacements), lhsGameObjectPlacements, rhsGameObjectPlacements)) {
                return false;
            }
        }
        {
            GameAutomaticActions lhsGameAutomaticActions;
            lhsGameAutomaticActions = this.getGameAutomaticActions();
            GameAutomaticActions rhsGameAutomaticActions;
            rhsGameAutomaticActions = that.getGameAutomaticActions();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameAutomaticActions", lhsGameAutomaticActions), LocatorUtils.property(thatLocator, "gameAutomaticActions", rhsGameAutomaticActions), lhsGameAutomaticActions, rhsGameAutomaticActions)) {
                return false;
            }
        }
        {
            GameMessages lhsGameMessages;
            lhsGameMessages = this.getGameMessages();
            GameMessages rhsGameMessages;
            rhsGameMessages = that.getGameMessages();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gameMessages", lhsGameMessages), LocatorUtils.property(thatLocator, "gameMessages", rhsGameMessages), lhsGameMessages, rhsGameMessages)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setGameWeapons(List<GameWeapons> value) {
        this.gameWeapons = null;
        List<GameWeapons> draftl = this.getGameWeapons();
        draftl.addAll(value);
    }

}
