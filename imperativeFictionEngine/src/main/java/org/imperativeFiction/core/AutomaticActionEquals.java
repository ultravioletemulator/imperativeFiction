package org.imperativeFiction.core;

import org.imperativeFiction.generated.AutomaticAction;

/**
 * Created by developer on 7/27/15.
 */
public class AutomaticActionEquals implements BeanEquals<AutomaticAction> {

	public boolean equals(AutomaticAction o1, AutomaticAction o2) {
		boolean res = false;
		if (o1 != null && o1 == null || o1 == null && o2 != null)
			res = (o1.getName() != null && !"".equals(o2.getName()) || (o2.getName() != null && !"".equals(o1.getName())));
		return res;
	}
}
