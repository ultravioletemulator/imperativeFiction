//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.29 at 11:38:15 AM CEST 
//


package org.imperativeFiction.generated;

import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for automaticAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="automaticAction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accomplishGoal" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="die" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="execution" type="{}execution" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "automaticAction", propOrder = {
    "accomplishGoal",
    "die",
    "execution"
})
public class AutomaticAction
    implements Equals, ToString
{

    protected List<String> accomplishGoal;
    protected Boolean die;
    protected List<Execution> execution;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the accomplishGoal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accomplishGoal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccomplishGoal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAccomplishGoal() {
        if (accomplishGoal == null) {
            accomplishGoal = new ArrayList<String>();
        }
        return this.accomplishGoal;
    }

    /**
     * Gets the value of the die property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDie() {
        return die;
    }

    /**
     * Sets the value of the die property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDie(Boolean value) {
        this.die = value;
    }

    /**
     * Gets the value of the execution property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the execution property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExecution().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Execution }
     * 
     * 
     */
    public List<Execution> getExecution() {
        if (execution == null) {
            execution = new ArrayList<Execution>();
        }
        return this.execution;
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
            List<String> theAccomplishGoal;
            theAccomplishGoal = (((this.accomplishGoal!= null)&&(!this.accomplishGoal.isEmpty()))?this.getAccomplishGoal():null);
            strategy.appendField(locator, this, "accomplishGoal", buffer, theAccomplishGoal);
        }
        {
            Boolean theDie;
            theDie = this.isDie();
            strategy.appendField(locator, this, "die", buffer, theDie);
        }
        {
            List<Execution> theExecution;
            theExecution = (((this.execution!= null)&&(!this.execution.isEmpty()))?this.getExecution():null);
            strategy.appendField(locator, this, "execution", buffer, theExecution);
        }
        {
            String theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AutomaticAction)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AutomaticAction that = ((AutomaticAction) object);
        {
            List<String> lhsAccomplishGoal;
            lhsAccomplishGoal = (((this.accomplishGoal!= null)&&(!this.accomplishGoal.isEmpty()))?this.getAccomplishGoal():null);
            List<String> rhsAccomplishGoal;
            rhsAccomplishGoal = (((that.accomplishGoal!= null)&&(!that.accomplishGoal.isEmpty()))?that.getAccomplishGoal():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accomplishGoal", lhsAccomplishGoal), LocatorUtils.property(thatLocator, "accomplishGoal", rhsAccomplishGoal), lhsAccomplishGoal, rhsAccomplishGoal)) {
                return false;
            }
        }
        {
            Boolean lhsDie;
            lhsDie = this.isDie();
            Boolean rhsDie;
            rhsDie = that.isDie();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "die", lhsDie), LocatorUtils.property(thatLocator, "die", rhsDie), lhsDie, rhsDie)) {
                return false;
            }
        }
        {
            List<Execution> lhsExecution;
            lhsExecution = (((this.execution!= null)&&(!this.execution.isEmpty()))?this.getExecution():null);
            List<Execution> rhsExecution;
            rhsExecution = (((that.execution!= null)&&(!that.execution.isEmpty()))?that.getExecution():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "execution", lhsExecution), LocatorUtils.property(thatLocator, "execution", rhsExecution), lhsExecution, rhsExecution)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

    public void setAccomplishGoal(List<String> value) {
        this.accomplishGoal = null;
        List<String> draftl = this.getAccomplishGoal();
        draftl.addAll(value);
    }

    public void setExecution(List<Execution> value) {
        this.execution = null;
        List<Execution> draftl = this.getExecution();
        draftl.addAll(value);
    }

}
