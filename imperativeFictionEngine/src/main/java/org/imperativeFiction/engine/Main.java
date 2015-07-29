package org.imperativeFiction.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/22/15.
 */
public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws GameException {
		logger.debug("Size: " + args.length + " Arguments:" + args);
		String gameFile = args[0];
		GameEngine gameEngine = new GameEngine();
		gameEngine.runGame(gameFile);
	}
}
