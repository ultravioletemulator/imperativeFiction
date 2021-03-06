package org.imperativeFiction.core.equals;

import org.imperativeFiction.generated.AutomaticAction;

/**
 * Created by developer on 7/27/15.
 */
public class AutomaticActionNameEquals
		implements BeanNameEquals<AutomaticAction> {

	public boolean equals(AutomaticAction o1, String name) {
		boolean res = false;
		if (o1 != null && o1 == null || o1 == null && name != null)
			res = (o1.getName() != null && !"".equals(name) || (name != null && !"".equals(o1.getName())));
		return res;
	}
}
