//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 12:32:17 PM CEST 
//


package org.imperativeFiction.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for gainedGoals complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gainedGoals"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gainedGoal" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gainedGoals", propOrder = {
    "gainedGoal"
})
public class GainedGoals
    implements Equals, ToString
{

    protected List<String> gainedGoal;

    /**
     * Gets the value of the gainedGoal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the gainedGoal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGainedGoal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGainedGoal() {
        if (gainedGoal == null) {
            gainedGoal = new ArrayList<String>();
        }
        return this.gainedGoal;
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
            List<String> theGainedGoal;
            theGainedGoal = (((this.gainedGoal!= null)&&(!this.gainedGoal.isEmpty()))?this.getGainedGoal():null);
            strategy.appendField(locator, this, "gainedGoal", buffer, theGainedGoal);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GainedGoals)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GainedGoals that = ((GainedGoals) object);
        {
            List<String> lhsGainedGoal;
            lhsGainedGoal = (((this.gainedGoal!= null)&&(!this.gainedGoal.isEmpty()))?this.getGainedGoal():null);
            List<String> rhsGainedGoal;
            rhsGainedGoal = (((that.gainedGoal!= null)&&(!that.gainedGoal.isEmpty()))?that.getGainedGoal():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gainedGoal", lhsGainedGoal), LocatorUtils.property(thatLocator, "gainedGoal", rhsGainedGoal), lhsGainedGoal, rhsGainedGoal)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setGainedGoal(List<String> value) {
        this.gainedGoal = null;
        List<String> draftl = this.getGainedGoal();
        draftl.addAll(value);
    }

}
