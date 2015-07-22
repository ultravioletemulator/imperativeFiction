package org.imperativeFiction.presentations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/22/15.
 */
public class ConsolePersentation implements Presentation {

	private Logger logger = LoggerFactory.getLogger(ConsolePersentation.class);

	public void presentText(String text) {
		logger.debug(text);
		System.out.println(text);
	}

	public void presentLocation() {
	}

	public void presentAction() {
	}
}
