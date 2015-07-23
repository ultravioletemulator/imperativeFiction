package org.imperativeFiction.engine;

import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.generated.*;

/**
 * Created by developer on 7/23/15.
 */
public class MovementUtils {

	public static ActionResponse move(Location location, GameAction gAction) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		response.setResponse("");
		if (checkMovementPreconditions(location, gAction)) {
			Boundary boundary = null;
			String param = gAction.getParameters() != null ? gAction.getParameters().get(0) : null;
			System.out.println("Movement:" + param);
			Movements movement = param != null ? Movements.valueOf(param.toUpperCase()) : null;
			switch (movement) {
			case NORTH:
				System.out.println("North..." + location);
				boundary = GameUtils.getBoundary(location.getNorth());
				break;
			case SOUTH:
				boundary = GameUtils.getBoundary(location.getSouth());
				break;
			case EAST:
				boundary = GameUtils.getBoundary(location.getEast());
				break;
			case WEST:
				boundary = GameUtils.getBoundary(location.getWest());
				break;
			case NORTHEAST:
				boundary = GameUtils.getBoundary(location.getNortheast());
				break;
			case NORTHWEST:
				boundary = GameUtils.getBoundary(location.getNorthwest());
				break;
			case SOUTHEAST:
				boundary = GameUtils.getBoundary(location.getSoutheast());
				break;
			case SOUTHWEST:
				boundary = GameUtils.getBoundary(location.getSouthwest());
				break;
			default:
				response.setResponse("Could not go to the " + param);
				break;
			}
			boolean canGo = canGoDirection(boundary);
			System.out.println("canGo?" + canGo);
			if (canGo) {
				GameExecutor.getGameState().setLocation(getOtherLocation((Path) boundary, location));
				response.setResponse("You go " + param + " to " + location.getName());
			} else {
				response.setResponse("Could not go to the " + param);
			}
		}
		return response;
	}

	private static Location getOtherLocation(Path path, Location location) {
		Location other = null;
		String toName = path.getToLocation();
		String fromName = path.getFromLocation();
		if (location != null && location.getName() != null && location.getName().equalsIgnoreCase(toName))
			other = GameUtils.getLocation(toName);
		if (location != null && location.getName() != null && location.getName().equalsIgnoreCase(fromName))
			other = GameUtils.getLocation(fromName);
		return other;
	}

	private static boolean canGoDirection(Boundary boundary) {
		System.out.println("boundary:" + boundary.getName());
		return (((boundary instanceof Path || (boundary instanceof Door && ((Door) boundary).getStatus().value().equalsIgnoreCase(ObjectStatus.OPEN.name())))));
	}

	private static boolean checkMovementPreconditions(Location location, GameAction gAction) {
		// Drools?¿
		return true;
	}
}
