package org.imperativeFiction.core.equals;

import org.imperativeFiction.generated.ObjectType;

/**
 * Created by developer on 7/29/15.
 */
public class StringEquals implements BeanNameEquals<String> {

	public boolean equals(String o1, String name) {
		//		System.out.println("Goal:" + o1 + " finding Name:" + name);
		boolean res = false;
		res = (o1 != null && o1.equalsIgnoreCase(name));
		//		System.out.println("found:" + res);
		return res;
	}
}
