package org.imperativeFiction.engine;

import org.imperativeFiction.core.OsCheck;
import org.imperativeFiction.generated.Game;
import org.imperativeFiction.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

/**
 * Created by developer on 7/22/15.
 */
public class GameEngine {

	private Logger logger = LoggerFactory.getLogger(GameEngine.class);
	GameExecutor gameExecutor = new GameExecutor();
	public static String JAXB_PACKAGE = "org.imperativeFiction.generated";

	public void runGame(String fileName) throws GameException {
		logger.debug("Testing log Console appender...");
		logger.debug("Game engine Running game..." + fileName);
		Game game = loadGame(fileName);
		executeGame(game);
	}

	public Game loadGame(String fileName) throws GameException {
		logger.debug("Game engine Loading game...");
		logger.debug("Current folder:" + FileUtils.getCurrentDir());
		System.out.println("Current folder:" + FileUtils.getCurrentDir());
		Game game = null;
		try {
			File gameFile = null;
			gameFile = FileUtils.getGameFile(fileName);
			JAXBContext jc = JAXBContext.newInstance(JAXB_PACKAGE);
			Unmarshaller um = jc.createUnmarshaller();
			game = (Game) um.unmarshal(gameFile);
			if (game == null) {
				throw new GameException("Game not found:" + fileName);
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
			System.out.println("Clear screen");
			try {
				OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
				switch (ostype) {
				case Windows:
					Runtime.getRuntime().exec("cls");
					break;
				case MacOS:
					Runtime.getRuntime().exec("clear");
					break;
				case Linux:
					Runtime.getRuntime().exec("clear");
					break;
				case Other:
					Runtime.getRuntime().exec("clear");
					break;
				}
				System.out.println("******************************************************");
				System.out.println("Os: " + ostype);
				System.out.println("Executing game " + game.getName() + " version:" + game.getVersion() + "...");
				System.out.println("Author:" + game.getAuthor());
				System.out.println("License: " + game.getLicensing());
				System.out.println("******************************************************");
				System.out.println(game.getName());
				System.out.println(game.getDescription());
				System.out.println("******************************************************");
			} catch (IOException e) {
			}
			gameExecutor.executeGame(game);
		}
	}
}
