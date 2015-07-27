package org.imperativeFiction.core;

import org.imperativeFiction.generated.GameMessages;

/**
 * Created by developer on 7/27/15.
 */
public class MessageNameEquals implements BeanNameEquals<GameMessages.Message> {

	public boolean equals(GameMessages.Message o1, String name) {
		boolean res = false;
		if (o1 != null && o1 == null || o1 == null && name != null)
			res = (o1.getName() != null && !"".equals(name) || (name != null && !"".equals(o1.getName())));
		return res;
	}
}
