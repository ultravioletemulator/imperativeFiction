package org.imperativeFiction.utils;

import org.imperativeFiction.core.equals.ObjectTypeNameEquals;
import org.imperativeFiction.engine.GameEngine;
import org.imperativeFiction.engine.GameExecutor;
import org.imperativeFiction.generated.ActionResponse;
import org.imperativeFiction.generated.ObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by developer on 7/28/15.
 */
public class InventoryUtils {

	public static Logger logger = LoggerFactory.getLogger(InteractionUtils.class);

	public static ActionResponse addToInventory(ObjectType obj) {
		GameExecutor.getGameState().getInventory();
		return null;
	}

	public static ActionResponse removeFromInventory(ObjectType obj) {
		GameExecutor.getGameState().getInventory();
		return null;
	}

	public static ActionResponse showInventory() {
		ActionResponse res = new ActionResponse();
		StringBuilder sb = new StringBuilder();
		sb.append("\n\niiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii\n");
		if (GameExecutor.getGameState() != null && GameExecutor.getGameState().getInventory() != null) {
			// SearhcObjects
			for (String objName : GameExecutor.getGameState().getInventory().getObjectName()) {
				ObjectType obj = GameUtils.getGameObject(objName);
				if (obj != null) {
					sb.append(obj.toString()).append("\n");
				}
			}
		}
		sb.append("\niiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii\n\n");
		res.setResponse("Inventory: " + sb.toString() + "\n");
		return res;
	}
}
