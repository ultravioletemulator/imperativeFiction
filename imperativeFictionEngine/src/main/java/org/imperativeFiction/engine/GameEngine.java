package org.imperativeFiction.engine;

import org.imperativeFiction.core.OsCheck;
import org.imperativeFiction.generated.ExecutionConfig;
import org.imperativeFiction.generated.Game;
import org.imperativeFiction.presentations.ConsolePresentation;
import org.imperativeFiction.presentations.Presentation;
import org.imperativeFiction.presentations.SwingPresentation;
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
public class GameEngine extends Thread {

	private Logger logger = LoggerFactory.getLogger(GameEngine.class);
	GameExecutor gameExecutor = new GameExecutor();
	static ExecutionConfig exConfig = new ExecutionConfig();
	static Presentation presentation = new ConsolePresentation();
	//	static Presentation presentation = new SwingPresentation();
	public static String JAXB_PACKAGE = "org.imperativeFiction.generated";

	public GameEngine() {
		// initial Config
		GameEngine.getExConfig().setMusic(new Boolean(true));
	}

	public static Presentation getPresentation() {
		return presentation;
	}

	public static String gameFile;

	public static String getGameFile() {
		return gameFile;
	}

	public static void setGameFile(String gameFile) {
		GameEngine.gameFile = gameFile;
	}

	public static void setPresentation(Presentation presentation) {
		GameEngine.presentation = presentation;
	}

	public static ExecutionConfig getExConfig() {
		return exConfig;
	}

	public void runGame(String fileName) throws GameException {
		logger.debug("Testing log Console appender...");
		logger.debug("Game engine Running game..." + fileName);
		Game game = loadGame(fileName);
		executeGame(game);
	}

	public Game loadGame(String fileName) throws GameException {
		logger.debug("Game engine Loading game...");
		logger.debug("Current folder:" + FileUtils.getCurrentDir());
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
			presentation.presentText("Could not load game:" + game);
		} else {
			presentation.presentText("Clear screen");
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
				presentation.presentText("******************************************************");
				presentation.presentText("Os: " + ostype);
				presentation.presentText("Executing game " + game.getName() + " version:" + game.getVersion() + "...");
				presentation.presentText("Author:" + game.getAuthor());
				presentation.presentText("License: " + game.getLicensing());
				presentation.presentText("******************************************************");
				presentation.presentText(game.getName());
				presentation.presentText(game.getDescription());
				presentation.presentText("******************************************************");
			} catch (IOException e) {
			}
			gameExecutor.executeGame(game);
		}
	}

	@Override
	public void run() {
		try {
			this.runGame(GameEngine.getGameFile());
		} catch (GameException e) {
			e.printStackTrace();
		}
	}
}
