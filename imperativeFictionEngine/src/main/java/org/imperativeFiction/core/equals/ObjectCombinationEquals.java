package org.imperativeFiction.core.equals;

import org.imperativeFiction.generated.ObjectCombination;
import org.imperativeFiction.generated.ObjectType;

/**
 * Created by developer on 7/28/15.
 */
public class ObjectCombinationEquals implements PlainEquals {

	private ObjectCombination combination;
	private ObjectType o1;
	private ObjectType o2;

	public ObjectCombinationEquals() {
	}

	public ObjectCombinationEquals(ObjectCombination combination, ObjectType o1, ObjectType o2) {
		this.combination = combination;
		this.o1 = o1;
		this.o2 = o2;
	}

	public ObjectCombination getCombination() {
		return combination;
	}

	public void setCombination(ObjectCombination combination) {
		this.combination = combination;
	}

	public ObjectType getO1() {
		return o1;
	}

	public void setO1(ObjectType o1) {
		this.o1 = o1;
	}

	public ObjectType getO2() {
		return o2;
	}

	public void setO2(ObjectType o2) {
		this.o2 = o2;
	}

	public boolean equals() {
		boolean res = false;
		if (combination != null) {
			if (o1 == null && o2 == null)
				res = true;
			else if (o1 != null && o2 != null && combination.getObject1Name() != null && combination.getObject2Name() != null && ((combination.getObject1Name().equalsIgnoreCase(o1.getName()) && combination.getObject1Name().equalsIgnoreCase(o2.getName())) || (combination.getObject2Name().equalsIgnoreCase(o1.getName()) && combination.getObject2Name().equalsIgnoreCase(o2.getName()))))
				res = true;
		} else
			res = false;
		return res;
	}
}
