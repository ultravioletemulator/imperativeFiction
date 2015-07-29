package org.imperativeFiction.utils;

import org.imperativeFiction.core.*;
import org.imperativeFiction.core.equals.DoorNameEquals;
import org.imperativeFiction.core.equals.ObjectCombinationEquals;
import org.imperativeFiction.core.equals.ObjectTypeNameEquals;
import org.imperativeFiction.core.equals.StringEquals;
import org.imperativeFiction.engine.GameException;
import org.imperativeFiction.engine.GameExecutor;
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
		//		if (gAction != null && gAction.getParameters().get(0) != null) {
		//			String doorName = gAction.getParameters().get(0);
		//			String keyName = gAction.getParameters().get(2);
		//			Door door = GameUtils.getElement(new DoorNameEquals(), GameExecutor.getRunningGame().getDefinition().getDoors().getDoor(), doorName);
		//			//ObjectType key = GameUtils.getElement(new ObjectTypeNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject(), keyName);
		//			ObjectType key = GameUtils.getGameObject(keyName);
		//			openDoor(door, key);
		//		}
		String objName = gAction.getParameters().get(0);
		ObjectType obj = GameUtils.getGameObject(objName);
		if (obj != null) {
			if (obj instanceof Door) {
				Door door = (Door) obj;
				openDoor(door, null);
			} else {
				GameUtils.setObjectsStatus(gAction, ObjectStatus.OPEN);
			}
		}
		return GameUtils.setObjectsStatus(gAction, ObjectStatus.OPEN);
	}

	public static ActionResponse closeObjects(GameAction gAction) {
		return GameUtils.setObjectsStatus(gAction, ObjectStatus.CLOSED);
	}

	public static java.util.List<ObjectPlacement> getObjectsInLocation(GameObjectPlacements placements, Location location) {
		//		System.out.println("getObjectsInLocation...");
		java.util.List<ObjectPlacement> foundPlacements = new ArrayList<ObjectPlacement>();
		if (placements != null && location != null) {
			for (ObjectPlacement plc : placements.getObjectPlacements()) {
				if (plc != null && plc.getLocationName() != null && plc.getLocationName().equalsIgnoreCase(location.getName())) {
					foundPlacements.add(plc);
				}
			}
		}
		return foundPlacements;
	}

	public static ActionResponse getObjectsInLocationResponse(GameObjectPlacements placements, Location location) {
		//		System.out.println("getObjectsInLocationResponse...");
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		java.util.List<ObjectPlacement> foundPlacements = new ArrayList<ObjectPlacement>();
		foundPlacements = getObjectsInLocation(placements, location);
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
			String objName = gAction.getParameters().get(0);
			//			ObjectType obj = GameUtils.getElement(new ObjectTypeNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject(), objName);
			ObjectType obj = GameUtils.getGameObject(objName != null ? objName.toLowerCase() : null);
			//			System.out.println("Found Object:" + obj);
			if (obj != null) {
				GameExecutor.getGameState().getInventory().getObjectName().add(obj.getName());
				ObjectPlacement placement = GameUtils.getPlacement(obj, location);
				GameExecutor.getGameState().getGameObjectPlacements().getObjectPlacements().remove(placement);
				response.setResponse("You got " + obj.getName());
			}
			response.setResponse("Could not get " + objName);
		} else {
			response.setResponse("I don't know what to " + gAction.getAction().getName());
		}
		return response;
	}

	public static ActionResponse closeDoor(Door door) {
		ActionResponse response = new ActionResponse();
		door.setDoorStatus(ObjectStatus.CLOSED);
		response.setResponse("You closed the " + door.getName() + " door.");
		return response;
	}

	public static ActionResponse openDoor(Door door, ObjectType obj) {
		//		logger.debug("Opening door " + door);
		ActionResponse res = new ActionResponse();
		if (door != null && door.getOpenWithObject() != null && obj.getName() != null) {
			// No object needed to open
			logger.debug("opencondition1:" + (obj == null && door.getOpenWithObject() == null));
			logger.debug("opencondition2:" + (door.getOpenWithObject() != null && !"".equals(door.getOpenWithObject())));
			logger.debug("OPenCondition full :" + (obj == null && door.getOpenWithObject() == null || (door.getOpenWithObject() != null && !"".equals(door.getOpenWithObject()))));
			if (obj == null && door.getOpenWithObject() == null || (door.getOpenWithObject() != null && !"".equals(door.getOpenWithObject()))) {
				door.setDoorStatus(ObjectStatus.OPEN);
			}
			// Open using object
			if (door.getOpenWithObject().equalsIgnoreCase(obj.getName())) {
				door.setDoorStatus(ObjectStatus.OPEN);
			}
			logger.debug("New Door status:" + door);
			res.setResponse("You opened the door " + door.getName() + ".");
		} else {
			res.setResponse("The door " + door.getName() + " could no t be opened with " + obj.getName() + ".");
		}
		return res;
	}

	public static ObjectCombination findObjectCombination(ObjectType o1, ObjectType o2) {
		ObjectCombination res = null;
		Iterator<ObjectCombination> cit = GameExecutor.getRunningGame().getDefinition().getGameObjectCombinations().getObjectCombination().iterator();
		boolean found = false;
		ObjectCombinationEquals ocEq = new ObjectCombinationEquals();
		ocEq.setO1(o1);
		ocEq.setO2(o2);
		while (cit.hasNext() && !found) {
			ObjectCombination oc = cit.next();
			ocEq.setCombination(oc);
			if (ocEq.equals()) {
				found = true;
				res = oc;
			}
		}
		return res;
	}

	public static ActionResponse combineObjects(ObjectType o1, ObjectType o2) {
		// TODO
		//System.out.println("TODO");
		ActionResponse resp = new ActionResponse();
		ObjectCombination oc = findObjectCombination(o1, o2);
		//		ObjectType resObj = GameUtils.getElement(new ObjectTypeNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameObjects().getObject(), oc.getObjectResult());
		ObjectType resObj = GameUtils.getGameObject(oc.getObjectResult());
		if (resObj != null) {
			InventoryUtils.removeFromInventory(o1);
			InventoryUtils.removeFromInventory(o2);
			InventoryUtils.addToInventory(resObj);
			resp.setResponse("You combined the " + o1.getName() + " and " + o2.getName() + " to get " + resObj.getName());
		} else
			resp.setResponse(o1.getName() + " and " + o2.getName() + " could not be combined.");
		return resp;
	}

	public static ActionResponse use(GameAction gAction) {
		ActionResponse response = new ActionResponse();
		logger.debug("ParamSize:" + gAction.getParameters().size());
		if (gAction.getParameters().size() != 1 && gAction.getParameters().size() != 3) {
			response.setResponse("I need to know what to use and with what...");
		} else if (gAction.getParameters().size() == 1) {
			response.setResponse("use Obj");
		} else if (gAction.getParameters().size() == 3) {
			String o1Name = GameUtils.getElement(new StringEquals(), GameExecutor.getGameState().getInventory().getObjectName(), gAction.getParameters().get(0));
			String o2Name = GameUtils.getElement(new StringEquals(), GameExecutor.getGameState().getInventory().getObjectName(), gAction.getParameters().get(2));
			ObjectType o2 = null;
			ObjectType o1 = GameUtils.getGameObject(o1Name);
			if (o2Name != null) {
				o2 = GameUtils.getGameObject(o2Name);
			} else {
				o2Name = gAction.getParameters().get(2);
				o2 = GameUtils.getDoor(o2Name);
			}
			logger.debug("UseObject:o1:" + o1 + " o2:" + o2);
			response = useObject(o1, o2);
		}
		return response;
	}

	public static ActionResponse useObject(ObjectType o1, ObjectType o2) {
		ActionResponse resp = new ActionResponse();
		logger.debug("useObject:");
		logger.debug("o1" + o1);
		logger.debug("o2" + o2);
		if (o2 instanceof Door) {
			openDoor((Door) o2, o1);
		} else {
			resp = combineObjects(o1, o2);
		}
		return resp;
	}
}
