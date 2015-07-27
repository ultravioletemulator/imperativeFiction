package org.imperativeFiction.engine;

import org.imperativeFiction.core.DoorNameEquals;
import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.core.ObjectTypeNameEquals;
import org.imperativeFiction.generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by developer on 7/23/15.
 */
public class InteractionUtils {

	public static Logger logger = LoggerFactory.getLogger(InteractionUtils.class);

	public static ActionResponse examineObject(GameAction gAction) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		ObjectType obj = null;
		if (gAction == null || ((gAction != null) && gAction.getParameters() != null && gAction.getParameters().size() > 0)) {
			obj = GameUtils.findObject(gAction.getParameters().get(0));
		} else {
			response.setResponse(gAction.getAction().getBasicAction() + " I don't know what to " + gAction.getAction().getName());
		}
		if (obj != null) {
			response.setResponse(obj.getDescription());
		}
		return response;
	}

	public static ActionResponse openObjects(GameAction gAction) {
		if (gAction != null && gAction.getParameters().get(0) != null) {
			String doorName = gAction.getParameters().get(0);
			String keyName = gAction.getParameters().get(2);
			Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), doorName);
			ObjectType key = GameUtils.getElement(new ObjectTypeNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject(), keyName);
			openDoor(door, key);
		}
		return GameUtils.setObjectsStatus(gAction, ObjectStatus.OPEN);
	}

	public static ActionResponse closeObjects(GameAction gAction) {
		return GameUtils.setObjectsStatus(gAction, ObjectStatus.CLOSED);
	}

	public static ActionResponse getObjectsInLocation(GameObjectPlacements placements, Location location) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		java.util.List<ObjectPlacement> foundPlacements = new ArrayList<ObjectPlacement>();
		if (placements != null && location != null) {
			for (ObjectPlacement plc : placements.getObjectPlacements()) {
				if (plc != null && plc.getLocationName() != null && plc.getLocationName().equalsIgnoreCase(location.getName())) {
					foundPlacements.add(plc);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int i = 0;
		if (foundPlacements != null && foundPlacements.size() > 0) {
			sb.append("You can see ");
			for (ObjectPlacement plc : foundPlacements) {
				if (!GameUtils.isFirst(i)) {
					sb.append(" , ");
				}
				sb.append(" a ").append(plc.getObjectName());
			}
		}
		response.setResponse(sb.toString());
		return response;
	}

	public static void leaveObject(ObjectType obj) throws GameException {
		logger.debug("TODO leaveObject");
		throw new GameException("Unimplemented");
	}

	public static ActionResponse getObject(GameAction gAction, Location location) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		if (gAction != null && gAction.getParameters() != null && gAction.getParameters().size() > 0) {
			ObjectType obj = GameUtils.getElement(new ObjectTypeNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject(), gAction.getParameters().get(0));
			System.out.println("Found Object:" + obj);
			if (obj != null) {
				System.out.println("Adding to inventory:" + GameExecutor.getGameState().getInventory());
				//				if (GameExecutor.getInventory()==null)
				GameExecutor.getGameState().getInventory().getObjectName().add(obj.getName());
				System.out.println("inventory after:" + GameExecutor.getGameState().getInventory());
				//removeObject from location ....
				ObjectPlacement placement = GameUtils.getPlacement(obj, location);
				GameExecutor.getRunningGame().getDefinition().getGameObjectPlacements().getObjectPlacements().remove(placement);
			}
			response.setResponse("You got " + obj.getName());
		} else {
			response.setResponse("I don't know what to " + gAction.getAction().getName());
		}
		return response;
	}

	public static ActionResponse showInventory() {
		ActionResponse res = new ActionResponse();
		StringBuilder sb = new StringBuilder();
		System.out.println(" invetnroy:" + GameExecutor.getGameState().getInventory());
		if (GameExecutor.getGameState() != null && GameExecutor.getGameState().getInventory() != null)
			sb.append(GameExecutor.getGameState().getInventory());
		res.setResponse("Inventory: " + sb.toString());
		return res;
	}

	public static ActionResponse openDoor(Door door, ObjectType obj) {
		ActionResponse res = new ActionResponse();
		if (door != null && door.getOpenWith() != null && obj.getName() != null) {
			// No object needed to open
			if (obj == null && door.getOpenWith() == null || (door.getOpenWith() != null && !"".equals(door.getOpenWith()))) {
				door.setDoorStatus(ObjectStatus.OPEN);
			}
			// Open using object
			if (door.getOpenWith().equalsIgnoreCase(obj.getName())) {
				door.setDoorStatus(ObjectStatus.OPEN);
			}
			res.setResponse("You opened the door " + door.getName() + ".");
		} else {
			res.setResponse("The door " + door.getName() + " could no t be opened with " + obj.getName() + ".");
		}
		return res;
	}
}
