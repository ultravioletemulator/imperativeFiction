package org.imperativeFiction.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import org.imperativeFiction.core.*;
import org.imperativeFiction.generated.*;
import org.imperativeFiction.presentations.ConsolePresentation;
import org.imperativeFiction.presentations.Presentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Created by developer on 7/22/15.
 */
public class GameExecutor {

	private Logger logger = LoggerFactory.getLogger(GameExecutor.class);
	static Presentation presentation = new ConsolePresentation();
	static Game runningGame = null;
	static ObjectFactory factory = new ObjectFactory();
	//	static Inventory inventory = new Inventory();
	static GameState gameState = null;
	static CommandParser parser = new CommandParser();
	CharacterState characterState = factory.createCharacterState();

	public static ObjectFactory getFactory() {
		return factory;
	}

	public static GameState getGameState() {
		return gameState;
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
		//Present initial text
		presentation.presentText(getFullLocationDescription(gameState.getLocation()));
		while (!finished) {
			String command = presentation.readCommand();
			try {
				GameAction gAction = parser.parseCommand(runningGame.getDefinition().getGenericActions(), command);
				ActionResponse response = executeAction(gAction);
				System.out.println("ActionResponse:" + response);
				if (response == null) {
					response = new ActionResponse();
					response.setResponse("Could not understand " + command);
				}
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
		// Set inventory
		gameState.setInventory(runningGame.getInitialization().getInventory());
		if (gameState.getInventory() == null)
			gameState.setInventory(new Inventory());
		if (gameState.getInventory().getObjectName() == null)
			gameState.getInventory().setObjectName(new ArrayList<String>());
		// setObjectPlacements
		GameObjectPlacements placements = runningGame.getDefinition().getGameObjectPlacements();
		System.out.println("Loading Game Object placements...");
		gameState.setGameObjectPlacements(placements);
		System.out.println("Game Object placements loaded. " + gameState.getGameObjectPlacements());
		//Locations
		Location location = GameUtils.getLocation(runningGame.getInitialization().getInitialLocationName());
		gameState.setLocation(location);
		presentation.presentText("InitialState :" + characterState);
		presentation.presentText("Inventory :" + gameState.getInventory());
		return gameState;
	}

	private ActionResponse executeAction(GameAction gAction) throws GameException {
		ActionResponse response = null;
		System.out.println("Action =" + gAction);
		if (gAction != null && gAction.getAction() != null && gAction.getAction().getBasicAction() != null) {
			ActionTypes actionType = ActionTypes.valueOf(gAction.getAction().getBasicAction());
			System.out.println("Action type:" + actionType);
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
					ActionResponse rAct = InteractionUtils.getObject(gAction, GameExecutor.getGameState().getLocation());
					ActionResponse rInt = InteractionUtils.showInventory();
					response = GameUtils.mergeActionResponses(rAct, rInt);
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
				case inventory:
					response = InteractionUtils.showInventory();
					break;
				case quit:
					//throw new GameException("Game Finished");
					response = new ActionResponse();
					response.setResponse("Exit game.");
					response.setQuit(true);
					break;
				case save:
					if (gAction.getParameters() != null && gAction.getParameters().size() > 0) {
						saveGameState(gAction.getParameters().get(0));
					}
					break;
				case load:
					if (gAction.getParameters() != null && gAction.getParameters().size() > 0) {
						loadGameState(gAction.getParameters().get(0));
					}
					break;
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
//		System.out.println("GameState: " + gameState);
		ActionResponse objResp = InteractionUtils.getObjectsInLocationResponse(gameState != null ? gameState.getGameObjectPlacements() : null, location);
		sb.append(objResp.getResponse());
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
		List<DoorLocationPlacement> doorPlacements = GameUtils.getDoorsOfLocation(location);
		if (doorPlacements != null && doorPlacements.size() > 0)
			sb.append("\nYou can see the following doors in the location: ");
		for (DoorLocationPlacement plc : doorPlacements) {
			sb.append("There's the ").append(plc.getDoorName()).append(" to the ").append(plc.getDirection().toLowerCase()).append("\n");
			//			sb.append(doorPlacements);
		}
		sb.append("\n");
		return sb.toString();
	}

	public void saveGameState(String fileName) throws GameException {
		File saveGameFile = new File(fileName);
		JAXBContext jc = null;
		GameState gameState = null;
		try {
			jc = JAXBContext.newInstance(GameEngine.JAXB_PACKAGE);
			Marshaller m = jc.createMarshaller();
			m.marshal(GameExecutor.getGameState(), saveGameFile);
		} catch (JAXBException e) {
			throw new GameException(e);
		}
	}

	public void loadGameState(String fileName) throws GameException {
		File saveGameFile = new File(fileName);
		JAXBContext jc = null;
		GameState gameState = null;
		try {
			jc = JAXBContext.newInstance(GameEngine.JAXB_PACKAGE);
			Unmarshaller um = jc.createUnmarshaller();
			gameState = (GameState) um.unmarshal(saveGameFile);
			if (gameState == null) {
				throw new GameException("Game state not found:" + fileName);
			} else {
				GameExecutor.gameState = gameState;
			}
		} catch (JAXBException e) {
			throw new GameException(e);
		}
	}
}
