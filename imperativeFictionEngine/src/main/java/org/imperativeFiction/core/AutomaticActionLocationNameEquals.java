package org.imperativeFiction.core;

import java.util.Iterator;

import org.imperativeFiction.generated.AutomaticAction;
import org.imperativeFiction.generated.Execution;

/**
 * Created by developer on 7/27/15.
 */
public class AutomaticActionLocationNameEquals
		implements BeanNameEquals<AutomaticAction> {

	public boolean equals(AutomaticAction o1, String name) {
		Iterator<Execution> exIt = o1.getExecution().iterator();
		boolean found = false;
		while (!found && exIt.hasNext()) {
			Execution ex = exIt.next();
			if (ex != null && ex.getOnEnterLocation() != null) {
				boolean foundloc = false;
				Iterator<String> locIt = ex.getOnEnterLocation().iterator();
				while (locIt.hasNext() && !foundloc) {
					String locName = locIt.next();
					if (locName != null && locName.equalsIgnoreCase(name)) {
						found = true;
					}
				}
			}
		}
		return found;
	}
}
