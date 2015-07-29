//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 07:21:03 PM CEST 
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
 *         &lt;element name="precondition" type="{}precondition" maxOccurs="unbounded"/&gt;
 *         &lt;element ref="{}action" maxOccurs="unbounded"/&gt;
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
    "precondition",
    "action"
})
@XmlRootElement(name = "automatedAction")
public class AutomatedAction
    implements Equals, ToString
{

    @XmlElement(required = true)
    protected List<Precondition> precondition;
    @XmlElement(required = true)
    protected List<Action> action;

    /**
     * Gets the value of the precondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the precondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrecondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Precondition }
     * 
     * 
     */
    public List<Precondition> getPrecondition() {
        if (precondition == null) {
            precondition = new ArrayList<Precondition>();
        }
        return this.precondition;
    }

    /**
     * Gets the value of the action property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the action property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Action }
     * 
     * 
     */
    public List<Action> getAction() {
        if (action == null) {
            action = new ArrayList<Action>();
        }
        return this.action;
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
            List<Precondition> thePrecondition;
            thePrecondition = (((this.precondition!= null)&&(!this.precondition.isEmpty()))?this.getPrecondition():null);
            strategy.appendField(locator, this, "precondition", buffer, thePrecondition);
        }
        {
            List<Action> theAction;
            theAction = (((this.action!= null)&&(!this.action.isEmpty()))?this.getAction():null);
            strategy.appendField(locator, this, "action", buffer, theAction);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AutomatedAction)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AutomatedAction that = ((AutomatedAction) object);
        {
            List<Precondition> lhsPrecondition;
            lhsPrecondition = (((this.precondition!= null)&&(!this.precondition.isEmpty()))?this.getPrecondition():null);
            List<Precondition> rhsPrecondition;
            rhsPrecondition = (((that.precondition!= null)&&(!that.precondition.isEmpty()))?that.getPrecondition():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "precondition", lhsPrecondition), LocatorUtils.property(thatLocator, "precondition", rhsPrecondition), lhsPrecondition, rhsPrecondition)) {
                return false;
            }
        }
        {
            List<Action> lhsAction;
            lhsAction = (((this.action!= null)&&(!this.action.isEmpty()))?this.getAction():null);
            List<Action> rhsAction;
            rhsAction = (((that.action!= null)&&(!that.action.isEmpty()))?that.getAction():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "action", lhsAction), LocatorUtils.property(thatLocator, "action", rhsAction), lhsAction, rhsAction)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setPrecondition(List<Precondition> value) {
        this.precondition = null;
        List<Precondition> draftl = this.getPrecondition();
        draftl.addAll(value);
    }

    public void setAction(List<Action> value) {
        this.action = null;
        List<Action> draftl = this.getAction();
        draftl.addAll(value);
    }

}
