package org.imperativeFiction.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/22/15.
 */
public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	private static GameEngine gameEngine = null;

	public static void main(String[] args) throws GameException {
		//		logger.debug("Size: " + args.length + " Arguments:" + args);
		gameEngine = new GameEngine();
		parseParams(args);
		gameEngine.run();
	}

	private static void parseParams(String[] args) {
		// parse parameters
		int i = 0;
		while (i < args.length) {
			String par = args[i];
			if (i == 0) {
				gameEngine.setGameFile(par);
			} else {
				if (par != null && par.startsWith("-noMusic")) {
					gameEngine.getExConfig().setMusic(new Boolean(false));
				}
			}
			//			logger.debug("par" + par);
			i++;
		}
	}
}
