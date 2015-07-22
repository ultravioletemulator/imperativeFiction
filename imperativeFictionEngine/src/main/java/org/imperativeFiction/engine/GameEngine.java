package org.imperativeFiction.engine;

import org.imperativeFiction.generated.Game;
import org.imperativeFiction.presentations.ConsolePersentation;
import org.imperativeFiction.presentations.Presentation;
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
	Presentation presentation = new ConsolePersentation();

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
			presentation.presentText("Could not load game:" + game);
		} else {
			presentation.presentText("******************************************************");
			presentation.presentText("Executing game " + game.getName() + " version:" + game.getVersion() + "...");
			presentation.presentText("Author:" + game.getAuthor());
			presentation.presentText("License: " + game.getLicensing());
			presentation.presentText("******************************************************");
			presentation.presentText(game.getName());
			presentation.presentText(game.getDescription());
			presentation.presentText("******************************************************");
			gameExecutor.executeGame(game);
		}
	}
}
