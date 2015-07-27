package org.imperativeFiction.engine;

import org.imperativeFiction.core.GameAction;
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

	public static void getObject(ObjectType obj) throws GameException {
		logger.debug("TODO getObject");
		throw new GameException("Unimplemented");
	}

	public static void leaveObject(ObjectType obj) throws GameException {
		logger.debug("TODO leaveObject");
		throw new GameException("Unimplemented");
	}
}
