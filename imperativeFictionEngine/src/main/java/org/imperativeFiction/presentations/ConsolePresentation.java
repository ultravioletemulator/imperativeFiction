package org.imperativeFiction.presentations;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.imperativeFiction.engine.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/22/15.
 */
public class ConsolePresentation implements Presentation {

	private static Logger logger = LoggerFactory.getLogger(ConsolePresentation.class);

	public void presentText(String text) {
		logger.info(text);
		//System.out.println(text);
	}

	public void presentLocation() {
	}

	public void presentAction() {
	}

	public void showImage(Image image) {
		logger.debug("Not implemented");
	}

	public String readCommand() throws GameException {
		String command = null;
		Scanner console = new Scanner(System.in);
		if (console.hasNextLine()) {
			command = console.nextLine();
		}
		//		System.out.println("Command read: " + command);
		return command;
	}
}
