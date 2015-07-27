package org.imperativeFiction.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import org.imperativeFiction.core.*;
import org.imperativeFiction.generated.*;
import org.imperativeFiction.presentations.ConsolePresentation;
import org.imperativeFiction.presentations.Presentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/22/15.
 */
public class GameExecutor {

	private Logger logger = LoggerFactory.getLogger(GameExecutor.class);
	static Presentation presentation = new ConsolePresentation();
	static Game runningGame = null;
	static ObjectFactory factory = new ObjectFactory();
	static Inventory inventory = null;
	static GameState gameState = null;
	static CommandParser parser = new CommandParser();
	CharacterState characterState = factory.createCharacterState();

	public static ObjectFactory getFactory() {
		return factory;
	}

	public static GameState getGameState() {
		return gameState;
	}

	public static Inventory getInventory() {
		return inventory;
	}

	public static Presentation getPresentation() {
		return presentation;
	}

	public static Game getRunningGame() {
		return runningGame;
	}

	public void executeGame(Game game) throws GameException {
		runningGame = game;
		if (game != null && game.getArtwork() != null && game.getArtwork().getImage() != null && game.getArtwork().getImage().size() > 0 && game.getArtwork().getImage().get(0) != null && game.getArtwork().getImage().get(0).getFile() != null && game.getArtwork().getImage().get(0).getFile().getPath() != null) {
			GameUtils.showArtwork(game.getArtwork().getImage().get(0).getFile().getPath());
		}
		try {
			GameUtils.playMusic(new File(game.getMusic().getFile().get(0).getPath()));
		} catch (FileNotFoundException e) {
			throw new GameException(e);
		} catch (JavaLayerException e) {
			throw new GameException(e);
		}
		runGame();
	}

	private void runGame() throws GameException {
		logger.debug("Running game: " + runningGame.getName());
		System.out.println("Running game: " + runningGame.getName());
		boolean finished = false;
		gameState = initGame();
		while (!finished) {
			String command = presentation.readCommand();
			try {
				GameAction gAction = parser.parseCommand(runningGame.getDefinition().getGenericActions(), command);
				ActionResponse response = executeAction(gAction);
				System.out.println("ActionResponse:" + response);
				presentation.presentText(response.getResponse());
				//				presentation.presentText(gameState.getLocation().getDescription());
				//				System.out.println("Objects in location :" + InteractionUtils.getObjectsInLocation(runningGame.getDefinition().getGameObjectPlacements(), gameState.getLocation()).getResponse());
				//				presentation.presentText(InteractionUtils.getObjectsInLocation(runningGame.getDefinition().getGameObjectPlacements(), gameState.getLocation()).getResponse());
				if (response != null && response.isQuit() != null && response.isQuit())
					System.exit(0);
				presentation.presentText(getFullLocationDescription(gameState.getLocation()));
			} catch (UnknownCommandException e) {
				presentation.presentText(e.getLocalizedMessage());
			}
		}
	}

	private GameState initGame() {
		GameState gameState = factory.createGameState();
		characterState.setLife(runningGame.getInitialization().getLife());
		inventory = runningGame.getInitialization().getInventory();
		Location location = GameUtils.getLocation(runningGame.getInitialization().getInitialLocationName());
		gameState.setLocation(location);
		presentation.presentText("InitialState :" + characterState);
		presentation.presentText("Inventory :" + inventory);
		presentation.presentText(getFullLocationDescription(location));
		return gameState;
	}

	private ActionResponse executeAction(GameAction gAction) throws GameException {
		ActionResponse response = null;
		System.out.println("Action =" + gAction.getAction().getName());
		if (gAction != null) {
			ActionTypes actionType = ActionTypes.valueOf(gAction.getAction().getBasicAction());
			if (actionType != null) {
				switch (actionType) {
				case open:
					response = InteractionUtils.openObjects(gAction);
					break;
				case close:
					response = InteractionUtils.closeObjects(gAction);
					break;
				case examine:
					response = InteractionUtils.examineObject(gAction);
					break;
				case get:
					response = GameUtils.getObject(gAction);
					break;
				case go:
					response = MovementUtils.move(gameState.getLocation(), gAction);
					break;
				case talk:
					throw new GameException(new NotImplementedException());
				case pull:
					throw new GameException(new NotImplementedException());
				case push:
					throw new GameException(new NotImplementedException());
				case use:
					throw new GameException(new NotImplementedException());
				case quit:
					throw new GameException("Game Finished");
				default:
					break;
				}
			}
		}
		return response;
	}

	private String getFullLocationDescription(Location location) {
		StringBuilder sb = new StringBuilder();
		//		System.out.println("Location:" + location);
		if (location != null)
			sb.append(location.getDescription());
		sb.append("\n");
		//		System.out.println("Placements:" + runningGame.getDefinition().getGameObjectPlacements());
		sb.append(InteractionUtils.getObjectsInLocation(runningGame.getDefinition().getGameObjectPlacements(), location).getResponse());
		sb.append("\n");
		List<DirectionBoundary> paths = GameUtils.getPaths(location);
		if (paths != null) {
			sb.append("You can go ");
			int i = 0;
			for (DirectionBoundary dirBoundary : paths) {
				if (!GameUtils.isFirst(i))
					sb.append(" , ");
				if (dirBoundary != null && dirBoundary.getMovementTypes() != null)
					sb.append(dirBoundary.getMovementTypes().name());
				i++;
			}
		}
		return sb.toString();
	}
}
