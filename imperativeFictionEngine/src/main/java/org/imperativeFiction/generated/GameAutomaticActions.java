//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 12:45:53 PM CEST 
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
 * <p>Java class for gameAutomaticActions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gameAutomaticActions"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="automaticAction" type="{}automaticAction" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gameAutomaticActions", propOrder = {
    "automaticAction"
})
public class GameAutomaticActions
    implements Equals, ToString
{

    protected List<AutomaticAction> automaticAction;

    /**
     * Gets the value of the automaticAction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the automaticAction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAutomaticAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AutomaticAction }
     * 
     * 
     */
    public List<AutomaticAction> getAutomaticAction() {
        if (automaticAction == null) {
            automaticAction = new ArrayList<AutomaticAction>();
        }
        return this.automaticAction;
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
            List<AutomaticAction> theAutomaticAction;
            theAutomaticAction = (((this.automaticAction!= null)&&(!this.automaticAction.isEmpty()))?this.getAutomaticAction():null);
            strategy.appendField(locator, this, "automaticAction", buffer, theAutomaticAction);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GameAutomaticActions)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GameAutomaticActions that = ((GameAutomaticActions) object);
        {
            List<AutomaticAction> lhsAutomaticAction;
            lhsAutomaticAction = (((this.automaticAction!= null)&&(!this.automaticAction.isEmpty()))?this.getAutomaticAction():null);
            List<AutomaticAction> rhsAutomaticAction;
            rhsAutomaticAction = (((that.automaticAction!= null)&&(!that.automaticAction.isEmpty()))?that.getAutomaticAction():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "automaticAction", lhsAutomaticAction), LocatorUtils.property(thatLocator, "automaticAction", rhsAutomaticAction), lhsAutomaticAction, rhsAutomaticAction)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setAutomaticAction(List<AutomaticAction> value) {
        this.automaticAction = null;
        List<AutomaticAction> draftl = this.getAutomaticAction();
        draftl.addAll(value);
    }

}
