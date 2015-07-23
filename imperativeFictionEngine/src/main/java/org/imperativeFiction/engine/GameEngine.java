package org.imperativeFiction.engine;

import org.imperativeFiction.generated.Game;
import org.imperativeFiction.presentations.Presentation;
import org.imperativeFiction.presentations.SwingPresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by developer on 7/22/15.
 */
public class GameEngine {

	private Logger logger = LoggerFactory.getLogger(GameEngine.class);
	GameExecutor gameExecutor = new GameExecutor();

	public void runGame(String fileName) throws GameException {
		logger.debug("Running game..." + fileName);
		Game game = loadGame(fileName);
		executeGame(game);
	}

	public Game loadGame(String fileName) throws GameException {
		logger.debug("Loading game...");
		Game game = null;
		try {
			if (fileName == null || (fileName != null && fileName.equals(""))) {
				throw new GameException("Game not found" + fileName);
			} else {
				File gameFile = new File(fileName);
				JAXBContext jc = JAXBContext.newInstance("org.imperativeFiction.generated");
				Unmarshaller um = jc.createUnmarshaller();
				game = (Game) um.unmarshal(gameFile);
				if (game == null) {
					throw new GameException("Game not found:" + fileName);
				}
			}
		} catch (JAXBException e) {
			throw new GameException(e);
		}
		return game;
	}

	public void executeGame(Game game) throws GameException {
		if (game == null || (game != null && game.equals(""))) {
			System.out.println("Could not load game:" + game);
		} else {
			System.out.println("******************************************************");
			System.out.println("Executing game " + game.getName() + " version:" + game.getVersion() + "...");
			System.out.println("Author:" + game.getAuthor());
			System.out.println("License: " + game.getLicensing());
			System.out.println("******************************************************");
			System.out.println(game.getName());
			System.out.println(game.getDescription());
			System.out.println("******************************************************");
			gameExecutor.executeGame(game);
		}
	}
}
