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
 * <p>Java class for execution complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="execution"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="onEnterLocation" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="onGetObject" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="onInteractWithCharacter" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "execution", propOrder = {
    "onEnterLocation",
    "onGetObject",
    "onInteractWithCharacter"
})
public class Execution
    implements Equals, ToString
{

    protected List<String> onEnterLocation;
    protected List<String> onGetObject;
    protected List<String> onInteractWithCharacter;

    /**
     * Gets the value of the onEnterLocation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onEnterLocation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnEnterLocation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOnEnterLocation() {
        if (onEnterLocation == null) {
            onEnterLocation = new ArrayList<String>();
        }
        return this.onEnterLocation;
    }

    /**
     * Gets the value of the onGetObject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onGetObject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnGetObject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOnGetObject() {
        if (onGetObject == null) {
            onGetObject = new ArrayList<String>();
        }
        return this.onGetObject;
    }

    /**
     * Gets the value of the onInteractWithCharacter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onInteractWithCharacter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnInteractWithCharacter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOnInteractWithCharacter() {
        if (onInteractWithCharacter == null) {
            onInteractWithCharacter = new ArrayList<String>();
        }
        return this.onInteractWithCharacter;
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
            List<String> theOnEnterLocation;
            theOnEnterLocation = (((this.onEnterLocation!= null)&&(!this.onEnterLocation.isEmpty()))?this.getOnEnterLocation():null);
            strategy.appendField(locator, this, "onEnterLocation", buffer, theOnEnterLocation);
        }
        {
            List<String> theOnGetObject;
            theOnGetObject = (((this.onGetObject!= null)&&(!this.onGetObject.isEmpty()))?this.getOnGetObject():null);
            strategy.appendField(locator, this, "onGetObject", buffer, theOnGetObject);
        }
        {
            List<String> theOnInteractWithCharacter;
            theOnInteractWithCharacter = (((this.onInteractWithCharacter!= null)&&(!this.onInteractWithCharacter.isEmpty()))?this.getOnInteractWithCharacter():null);
            strategy.appendField(locator, this, "onInteractWithCharacter", buffer, theOnInteractWithCharacter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Execution)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Execution that = ((Execution) object);
        {
            List<String> lhsOnEnterLocation;
            lhsOnEnterLocation = (((this.onEnterLocation!= null)&&(!this.onEnterLocation.isEmpty()))?this.getOnEnterLocation():null);
            List<String> rhsOnEnterLocation;
            rhsOnEnterLocation = (((that.onEnterLocation!= null)&&(!that.onEnterLocation.isEmpty()))?that.getOnEnterLocation():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "onEnterLocation", lhsOnEnterLocation), LocatorUtils.property(thatLocator, "onEnterLocation", rhsOnEnterLocation), lhsOnEnterLocation, rhsOnEnterLocation)) {
                return false;
            }
        }
        {
            List<String> lhsOnGetObject;
            lhsOnGetObject = (((this.onGetObject!= null)&&(!this.onGetObject.isEmpty()))?this.getOnGetObject():null);
            List<String> rhsOnGetObject;
            rhsOnGetObject = (((that.onGetObject!= null)&&(!that.onGetObject.isEmpty()))?that.getOnGetObject():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "onGetObject", lhsOnGetObject), LocatorUtils.property(thatLocator, "onGetObject", rhsOnGetObject), lhsOnGetObject, rhsOnGetObject)) {
                return false;
            }
        }
        {
            List<String> lhsOnInteractWithCharacter;
            lhsOnInteractWithCharacter = (((this.onInteractWithCharacter!= null)&&(!this.onInteractWithCharacter.isEmpty()))?this.getOnInteractWithCharacter():null);
            List<String> rhsOnInteractWithCharacter;
            rhsOnInteractWithCharacter = (((that.onInteractWithCharacter!= null)&&(!that.onInteractWithCharacter.isEmpty()))?that.getOnInteractWithCharacter():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "onInteractWithCharacter", lhsOnInteractWithCharacter), LocatorUtils.property(thatLocator, "onInteractWithCharacter", rhsOnInteractWithCharacter), lhsOnInteractWithCharacter, rhsOnInteractWithCharacter)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setOnEnterLocation(List<String> value) {
        this.onEnterLocation = null;
        List<String> draftl = this.getOnEnterLocation();
        draftl.addAll(value);
    }

    public void setOnGetObject(List<String> value) {
        this.onGetObject = null;
        List<String> draftl = this.getOnGetObject();
        draftl.addAll(value);
    }

    public void setOnInteractWithCharacter(List<String> value) {
        this.onInteractWithCharacter = null;
        List<String> draftl = this.getOnInteractWithCharacter();
        draftl.addAll(value);
    }

}
