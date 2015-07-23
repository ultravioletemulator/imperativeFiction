package org.imperativeFiction.engine;

import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.generated.ActionResponse;
import org.imperativeFiction.generated.ObjectStatus;
import org.imperativeFiction.generated.ObjectType;

/**
 * Created by developer on 7/23/15.
 */
public class InteractionUtils {

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
}
