//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 11:38:15 AM CEST 
//


package org.imperativeFiction.generated;

import java.math.BigInteger;
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
 * <p>Java class for weaponType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="weaponType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{}objectType"&gt;
 *       &lt;attribute name="damage" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "weaponType")
public class WeaponType
    extends ObjectType
    implements Equals, ToString
{

    @XmlAttribute(name = "damage", required = true)
    protected BigInteger damage;

    /**
     * Gets the value of the damage property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDamage() {
        return damage;
    }

    /**
     * Sets the value of the damage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDamage(BigInteger value) {
        this.damage = value;
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
            BigInteger theDamage;
            theDamage = this.getDamage();
            strategy.appendField(locator, this, "damage", buffer, theDamage);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WeaponType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WeaponType that = ((WeaponType) object);
        {
            BigInteger lhsDamage;
            lhsDamage = this.getDamage();
            BigInteger rhsDamage;
            rhsDamage = that.getDamage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "damage", lhsDamage), LocatorUtils.property(thatLocator, "damage", rhsDamage), lhsDamage, rhsDamage)) {
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
