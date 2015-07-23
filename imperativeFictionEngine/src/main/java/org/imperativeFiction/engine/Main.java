package org.imperativeFiction.engine;

/**
 * Created by developer on 7/22/15.
 */
public class Main {

	public static void main(String[] args) throws GameException {
		System.out.println("Size: " + args.length + " Arguments:" + args);
		String gameFile = args[0];
		GameEngine gameEngine = new GameEngine();
		gameEngine.runGame(gameFile);
	}
}
