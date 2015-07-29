package org.imperativeFiction.utils;

import javazoom.jl.decoder.JavaLayerException;
import org.imperativeFiction.core.*;
import org.imperativeFiction.core.equals.BeanNameEquals;
import org.imperativeFiction.core.equals.DoorNameEquals;
import org.imperativeFiction.core.equals.MessageNameEquals;
import org.imperativeFiction.engine.GameEngine;
import org.imperativeFiction.engine.GameException;
import org.imperativeFiction.engine.GameExecutor;
import org.imperativeFiction.generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by developer on 7/23/15.
 */
public class GameUtils {

	private static Logger logger = LoggerFactory.getLogger(GameUtils.class);

	public static void playMusic(File file) throws FileNotFoundException, JavaLayerException {
		//		FileInputStream fis = new FileInputStream(file);
		//Player player = new Player(fis);
		//player.play();
	}

	public static void showArtwork(String file) throws GameException {
		BufferedImage img = null;
		//		try {
		//			String fileName = "test-classes/" + file;
		//			logger.debug("reading image:" + fileName);
		//			img = ImageIO.read(new File(fileName));
		//		} catch (IOException e) {
		//			throw new GameException(e);
		//		}
		//		java.awt.Image image = img;
		//		javax.swing.JFrame frame = new javax.swing.JFrame();
		//		JLabel lblimage = new JLabel(new ImageIcon(image));
		//		frame.add(lblimage);
		//		frame.show();
	}

	public static ObjectType findObject(String objName) {
		ObjectType obj = null;
		if (objName != null) {
			Iterator<ObjectType> git = GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject().iterator();
			boolean found = false;
			while (git.hasNext() && !found) {
				ObjectType gobj = git.next();
				if (gobj != null) {
					ObjectType objNew = gobj;
					if (objNew != null && objNew.getName() != null && objNew.getName().trim().toLowerCase().startsWith(objName.trim().toLowerCase())) {
						obj = objNew;
						found = true;
					}
				}
			}
		}
		return obj;
	}

	private static boolean checkPreconditions(GameAction gAction, ObjectType obj) {
		// Drools?¿
		return true;
	}

	//	public static Boundary getBoundary(String boundaryName) {
	//		boolean found = false;
	//		Boundary res = null;
	//		if (boundaryName != null) {
	//			if ("wall".equalsIgnoreCase(boundaryName) || "emptyBoundary".equalsIgnoreCase(boundaryName)) {
	//				res = new Wall();
	//			}
	//			Iterator<Boundary> lit = GameExecutor.getRunningGame().getDefinition().getBoundaries().getBoundary().iterator();
	//			while (!found && lit.hasNext()) {
	//				Boundary loc = lit.next();
	//				if (loc != null && boundaryName.equalsIgnoreCase(loc.getName())) {
	//					res = loc;
	//				}
	//			}
	//		}
	//		return res;
	//	}
	public static Boundary getDoor(String boundaryName) {
		//		boolean found = false;
		Boundary res = null;
		if (boundaryName != null) {
			if ("wall".equalsIgnoreCase(boundaryName) || "emptyBoundary".equalsIgnoreCase(boundaryName)) {
				res = new Wall();
			}
			//			Iterator<Door> lit = GameExecutor.getRunningGame().getDefinition().getDoors().getDoor().iterator();
			//			while (!found && lit.hasNext()) {
			//				Boundary loc = lit.next();
			//				if (loc != null && boundaryName.equalsIgnoreCase(loc.getName())) {
			//					res = loc;
			//				}
			//			}
			res = GameExecutor.getGameDoors().get(boundaryName.toLowerCase());
		}
		return res;
	}

	public static Location getLocation(String locationName) {
		logger.debug("SEarching for location:" + locationName);
		Location res = GameExecutor.getGameLocations().get(locationName.toLowerCase());
		logger.debug("Got location:" + res);
		return res;
	}

	private static String getStatusMessage(ObjectType obj) {
		StringBuilder sb = new StringBuilder();
		sb.append("The ").append(obj.getName() + " is now " + obj.getStatus().name());
		return sb.toString();
	}

	public static ActionResponse setObjectsStatus(GameAction gAction, ObjectStatus status) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		StringBuilder sb = new StringBuilder();
		for (ObjectType obj : GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject()) {
			ObjectType objt = obj;
			if (checkPreconditions(gAction, objt)) {
				objt.setStatus(status);
				sb.append(getStatusMessage(objt));
			}
		}
		response.setResponse(sb.toString());
		return response;
	}

	public static List<DirectionBoundary> getPaths(Location location) {
		List<DirectionBoundary> boundaries = new ArrayList<DirectionBoundary>();
		if (location.getNorth() != null && !"emptyBoundary".equalsIgnoreCase(location.getNorth()) && !"wall".equalsIgnoreCase(location.getNorth())) {
			boundaries.add(new DirectionBoundary(MovementTypes.north, GameUtils.getDoor(location.getNorth())));
		}
		if (location.getSouth() != null && !"emptyBoundary".equalsIgnoreCase(location.getSouth()) && !"wall".equalsIgnoreCase(location.getSouth())) {
			boundaries.add(new DirectionBoundary(MovementTypes.south, GameUtils.getDoor(location.getSouth())));
		}
		if (location.getEast() != null && !"emptyBoundary".equalsIgnoreCase(location.getEast()) && !"wall".equalsIgnoreCase(location.getEast())) {
			boundaries.add(new DirectionBoundary(MovementTypes.east, GameUtils.getDoor(location.getEast())));
		}
		if (location.getWest() != null && !"emptyBoundary".equalsIgnoreCase(location.getWest()) && !"wall".equalsIgnoreCase(location.getWest())) {
			boundaries.add(new DirectionBoundary(MovementTypes.west, GameUtils.getDoor(location.getWest())));
		}
		if (location.getNortheast() != null && !"emptyBoundary".equalsIgnoreCase(location.getNortheast()) && !"wall".equalsIgnoreCase(location.getNortheast())) {
			boundaries.add(new DirectionBoundary(MovementTypes.northeast, GameUtils.getDoor(location.getNortheast())));
		}
		if (location.getNorthwest() != null && !"emptyBoundary".equalsIgnoreCase(location.getNorthwest()) && !"wall".equalsIgnoreCase(location.getNorthwest())) {
			boundaries.add(new DirectionBoundary(MovementTypes.northwest, GameUtils.getDoor(location.getNorthwest())));
		}
		if (location.getSoutheast() != null && !"emptyBoundary".equalsIgnoreCase(location.getSoutheast()) && !"wall".equalsIgnoreCase(location.getSoutheast())) {
			boundaries.add(new DirectionBoundary(MovementTypes.north, GameUtils.getDoor(location.getSoutheast())));
		}
		if (location.getSouthwest() != null && !"emptyBoundary".equalsIgnoreCase(location.getSouthwest()) && !"wall".equalsIgnoreCase(location.getSouthwest())) {
			boundaries.add(new DirectionBoundary(MovementTypes.north, GameUtils.getDoor(location.getSouthwest())));
		}
		return boundaries;
	}

	public static boolean isFirst(int i) {
		return i == 0;
	}

	//Automatic action executed on location entrance, object usage, etc...
	public void executeAutoAction() throws GameException {
		logger.debug("TODO executeAutoAction");
		throw new GameException("Unimplemented");
	}

	public static ObjectPlacement getPlacement(ObjectType obj, Location location) {
		ObjectPlacement objPlc = null;
		if (obj != null && location != null) {
			Iterator<ObjectPlacement> objPlcIt = GameExecutor.getGameState().getGameObjectPlacements().getObjectPlacements().iterator();
			boolean found = false;
			while (objPlcIt.hasNext() && !found) {
				ObjectPlacement plc = objPlcIt.next();
				if (plc != null && plc.getLocationName() != null && plc.getLocationName().equalsIgnoreCase(location.getName()) && plc.getObjectName().equalsIgnoreCase(obj.getName())) {
					objPlc = plc;
					found = true;
				}
			}
		}
		return objPlc;
	}

	public static <T> T getElement(BeanNameEquals<T> equals, List<T> o1, String name) {
		logger.debug("Searching element :" + name);
		T res = null;
		if (o1 != null) {
			Iterator<T> lit = o1.iterator();
			boolean found = false;
			while (lit.hasNext() && !found) {
				T elem = lit.next();
				if (equals.equals(elem, name)) {
					found = true;
					res = elem;
				}
			}
		}
		logger.debug("got element :" + res);
		return res;
	}

	public static ObjectType getGameObject(String name) {
		if (name != null) {
			logger.debug("Getting obj:" + name);
			logger.debug("from " + GameExecutor.getGameObjects());
			return GameExecutor.getGameObjects().get(name.toLowerCase());
		} else
			return null;
	}

	public static ActionResponse die() {
		ActionResponse response = new ActionResponse();
		GameExecutor.getGameState().getCharacterState().setLife(BigInteger.valueOf(0));
		GameMessages.Message msg = GameUtils.getElement(new MessageNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameMessages().getMessage(), MSG_DIE);
		if (msg != null)
			response.setResponse(msg.getMsg());
		return response;
	}

	public static ActionResponse finishGame() {
		ActionResponse response = new ActionResponse();
		GameMessages.Message msg = GameUtils.getElement(new MessageNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameMessages().getMessage(), MSG_GAME_FINISH);
		if (msg != null)
			response.setResponse(msg.getMsg());
		return response;
	}

	public static ActionResponse mergeActionResponses(ActionResponse r1, ActionResponse r2) {
		//		System.out.println("Act1:" + r1 + "\nAct2" + r2);
		if (r1 == null && r2 == null)
			return null;
		else if (r1 != null && r2 == null)
			return r1;
		else if (r2 != null && r1 == null)
			return r2;
		else {
			ActionResponse resp = new ActionResponse();
			resp.setResponse(r1.getResponse() + "\n" + r2.getResponse());
			//			System.out.println("Gor merged response: " + resp);
			return resp;
		}
	}

	public static List<DoorLocationPlacement> getDoorsOfLocation(Location location) {
		List<DoorLocationPlacement> doors = new ArrayList<DoorLocationPlacement>();
		if (location != null) {
			if ((location.getNorth() != null) && (MovementUtils.isBoundaryCrossable(location.getNorth()))) {
				//Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getNorth());
				Door door = (Door) GameUtils.getDoor(location.getNorth());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.NORTH.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getSouth() != null) && (MovementUtils.isBoundaryCrossable(location.getSouth()))) {
				//				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getSouth());
				Door door = (Door) GameUtils.getDoor(location.getSouth());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.SOUTH.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getEast() != null) && (MovementUtils.isBoundaryCrossable(location.getEast()))) {
				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getEast());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.EAST.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getWest() != null) && (MovementUtils.isBoundaryCrossable(location.getWest()))) {
				//				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getWest());
				Door door = (Door) GameUtils.getDoor(location.getWest());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.WEST.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getNortheast() != null) && (MovementUtils.isBoundaryCrossable(location.getNortheast()))) {
				//				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getNortheast());
				Door door = (Door) GameUtils.getDoor(location.getEast());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.NORTHEAST.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getNorthwest() != null) && (MovementUtils.isBoundaryCrossable(location.getNorthwest()))) {
				//				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getNorthwest());
				Door door = (Door) GameUtils.getDoor(location.getNorthwest());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.NORTHWEST.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getSoutheast() != null) && (MovementUtils.isBoundaryCrossable(location.getSoutheast()))) {
				//				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getSoutheast());
				Door door = (Door) GameUtils.getDoor(location.getSoutheast());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.SOUTHEAST.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
			if ((location.getSouthwest() != null) && (MovementUtils.isBoundaryCrossable(location.getSouthwest()))) {
				//				Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), location.getSouthwest());
				Door door = (Door) GameUtils.getDoor(location.getSouthwest());
				DoorLocationPlacement plc = new DoorLocationPlacement();
				plc.setDirection(Movements.SOUTHWEST.name());
				plc.setDoorName(door.getName());
				plc.setLocationName(location.getName());
				doors.add(plc);
			}
		}
		return doors;
	}

	public static String MSG_DIE = "dieMessage";
	public static String MSG_GAME_FINISH = "gameFinishMessage";
}
