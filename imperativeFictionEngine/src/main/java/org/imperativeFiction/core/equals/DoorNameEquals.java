package org.imperativeFiction.core.equals;

import org.imperativeFiction.generated.Door;

/**
 * Created by developer on 7/27/15.
 */
public class DoorNameEquals implements BeanNameEquals<Door> {

	public boolean equals(Door o1, String name) {
		//		System.out.println("Goal:" + o1 + " finding Name:" + name);
		boolean res = false;
		res = (o1 != null && o1.getName() != null && o1.getName().equalsIgnoreCase(name));
		//		System.out.println("found:" + res);
		return res;
	}
}
