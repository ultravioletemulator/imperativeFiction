//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.27 at 07:29:42 AM CEST 
//


package org.imperativeFiction.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 * <p>Java class for location complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="location"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{}image" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="north" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="south" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="east" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="west" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="northeast" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="northwest" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="southeast" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="southwest" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="description" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "location", propOrder = {
    "image",
    "north",
    "south",
    "east",
    "west",
    "northeast",
    "northwest",
    "southeast",
    "southwest"
})
public class Location
    implements Equals, ToString
{

    protected List<Image> image;
    @XmlElement(required = true)
    protected String north;
    @XmlElement(required = true)
    protected String south;
    @XmlElement(required = true)
    protected String east;
    @XmlElement(required = true)
    protected String west;
    @XmlElement(required = true)
    protected String northeast;
    @XmlElement(required = true)
    protected String northwest;
    @XmlElement(required = true)
    protected String southeast;
    @XmlElement(required = true)
    protected String southwest;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "description", required = true)
    protected String description;

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Image }
     * 
     * 
     */
    public List<Image> getImage() {
        if (image == null) {
            image = new ArrayList<Image>();
        }
        return this.image;
    }

    /**
     * Gets the value of the north property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNorth() {
        return north;
    }

    /**
     * Sets the value of the north property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNorth(String value) {
        this.north = value;
    }

    /**
     * Gets the value of the south property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSouth() {
        return south;
    }

    /**
     * Sets the value of the south property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSouth(String value) {
        this.south = value;
    }

    /**
     * Gets the value of the east property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEast() {
        return east;
    }

    /**
     * Sets the value of the east property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEast(String value) {
        this.east = value;
    }

    /**
     * Gets the value of the west property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWest() {
        return west;
    }

    /**
     * Sets the value of the west property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWest(String value) {
        this.west = value;
    }

    /**
     * Gets the value of the northeast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNortheast() {
        return northeast;
    }

    /**
     * Sets the value of the northeast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNortheast(String value) {
        this.northeast = value;
    }

    /**
     * Gets the value of the northwest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNorthwest() {
        return northwest;
    }

    /**
     * Sets the value of the northwest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNorthwest(String value) {
        this.northwest = value;
    }

    /**
     * Gets the value of the southeast property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoutheast() {
        return southeast;
    }

    /**
     * Sets the value of the southeast property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoutheast(String value) {
        this.southeast = value;
    }

    /**
     * Gets the value of the southwest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSouthwest() {
        return southwest;
    }

    /**
     * Sets the value of the southwest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSouthwest(String value) {
        this.southwest = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
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
            List<Image> theImage;
            theImage = (((this.image!= null)&&(!this.image.isEmpty()))?this.getImage():null);
            strategy.appendField(locator, this, "image", buffer, theImage);
        }
        {
            String theNorth;
            theNorth = this.getNorth();
            strategy.appendField(locator, this, "north", buffer, theNorth);
        }
        {
            String theSouth;
            theSouth = this.getSouth();
            strategy.appendField(locator, this, "south", buffer, theSouth);
        }
        {
            String theEast;
            theEast = this.getEast();
            strategy.appendField(locator, this, "east", buffer, theEast);
        }
        {
            String theWest;
            theWest = this.getWest();
            strategy.appendField(locator, this, "west", buffer, theWest);
        }
        {
            String theNortheast;
            theNortheast = this.getNortheast();
            strategy.appendField(locator, this, "northeast", buffer, theNortheast);
        }
        {
            String theNorthwest;
            theNorthwest = this.getNorthwest();
            strategy.appendField(locator, this, "northwest", buffer, theNorthwest);
        }
        {
            String theSoutheast;
            theSoutheast = this.getSoutheast();
            strategy.appendField(locator, this, "southeast", buffer, theSoutheast);
        }
        {
            String theSouthwest;
            theSouthwest = this.getSouthwest();
            strategy.appendField(locator, this, "southwest", buffer, theSouthwest);
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Location)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Location that = ((Location) object);
        {
            List<Image> lhsImage;
            lhsImage = (((this.image!= null)&&(!this.image.isEmpty()))?this.getImage():null);
            List<Image> rhsImage;
            rhsImage = (((that.image!= null)&&(!that.image.isEmpty()))?that.getImage():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "image", lhsImage), LocatorUtils.property(thatLocator, "image", rhsImage), lhsImage, rhsImage)) {
                return false;
            }
        }
        {
            String lhsNorth;
            lhsNorth = this.getNorth();
            String rhsNorth;
            rhsNorth = that.getNorth();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "north", lhsNorth), LocatorUtils.property(thatLocator, "north", rhsNorth), lhsNorth, rhsNorth)) {
                return false;
            }
        }
        {
            String lhsSouth;
            lhsSouth = this.getSouth();
            String rhsSouth;
            rhsSouth = that.getSouth();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "south", lhsSouth), LocatorUtils.property(thatLocator, "south", rhsSouth), lhsSouth, rhsSouth)) {
                return false;
            }
        }
        {
            String lhsEast;
            lhsEast = this.getEast();
            String rhsEast;
            rhsEast = that.getEast();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "east", lhsEast), LocatorUtils.property(thatLocator, "east", rhsEast), lhsEast, rhsEast)) {
                return false;
            }
        }
        {
            String lhsWest;
            lhsWest = this.getWest();
            String rhsWest;
            rhsWest = that.getWest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "west", lhsWest), LocatorUtils.property(thatLocator, "west", rhsWest), lhsWest, rhsWest)) {
                return false;
            }
        }
        {
            String lhsNortheast;
            lhsNortheast = this.getNortheast();
            String rhsNortheast;
            rhsNortheast = that.getNortheast();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "northeast", lhsNortheast), LocatorUtils.property(thatLocator, "northeast", rhsNortheast), lhsNortheast, rhsNortheast)) {
                return false;
            }
        }
        {
            String lhsNorthwest;
            lhsNorthwest = this.getNorthwest();
            String rhsNorthwest;
            rhsNorthwest = that.getNorthwest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "northwest", lhsNorthwest), LocatorUtils.property(thatLocator, "northwest", rhsNorthwest), lhsNorthwest, rhsNorthwest)) {
                return false;
            }
        }
        {
            String lhsSoutheast;
            lhsSoutheast = this.getSoutheast();
            String rhsSoutheast;
            rhsSoutheast = that.getSoutheast();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "southeast", lhsSoutheast), LocatorUtils.property(thatLocator, "southeast", rhsSoutheast), lhsSoutheast, rhsSoutheast)) {
                return false;
            }
        }
        {
            String lhsSouthwest;
            lhsSouthwest = this.getSouthwest();
            String rhsSouthwest;
            rhsSouthwest = that.getSouthwest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "southwest", lhsSouthwest), LocatorUtils.property(thatLocator, "southwest", rhsSouthwest), lhsSouthwest, rhsSouthwest)) {
                return false;
            }
        }
        {
            String lhsName;
            lhsName = this.getName();
            String rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsDescription;
            lhsDescription = this.getDescription();
            String rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setImage(List<Image> value) {
        this.image = null;
        List<Image> draftl = this.getImage();
        draftl.addAll(value);
    }

}
