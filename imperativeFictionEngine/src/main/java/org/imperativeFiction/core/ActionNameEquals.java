package org.imperativeFiction.core;

import org.imperativeFiction.generated.Action;
import org.imperativeFiction.generated.Goal;

/**
 * Created by developer on 7/27/15.
 */
public class ActionNameEquals implements BeanNameEquals<Action> {

	public boolean equals(Action o1, String name) {
		//		System.out.println("Goal:" + o1 + " finding Name:" + name);
		boolean res = false;
		res = (o1 != null && o1.getName() != null && o1.getName().equalsIgnoreCase(name));
		//		System.out.println("found:" + res);
		return res;
	}
}
