package org.imperativeFiction.engine;

import org.imperativeFiction.core.AutomaticActionLocationNameEquals;
import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.core.GoalNameEquals;
import org.imperativeFiction.generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created by developer on 7/23/15.
 */
public class MovementUtils {

	public static Logger logger = LoggerFactory.getLogger(MovementUtils.class);

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
				boundary = GameUtils.getDoor(location.getNorth());
				break;
			case SOUTH:
				boundary = GameUtils.getDoor(location.getSouth());
				break;
			case EAST:
				boundary = GameUtils.getDoor(location.getEast());
				break;
			case WEST:
				boundary = GameUtils.getDoor(location.getWest());
				break;
			case NORTHEAST:
				boundary = GameUtils.getDoor(location.getNortheast());
				break;
			case NORTHWEST:
				boundary = GameUtils.getDoor(location.getNorthwest());
				break;
			case SOUTHEAST:
				boundary = GameUtils.getDoor(location.getSoutheast());
				break;
			case SOUTHWEST:
				boundary = GameUtils.getDoor(location.getSouthwest());
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
			executeAutomaticAction(GameExecutor.getGameState().getLocation());
		}
		return response;
	}

	private static void executeAutomaticAction(Location location) {
		//		... Get Automatic Action with location ...
		AutomaticAction autoAction = GameUtils.getElement(new AutomaticActionLocationNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameAutomaticActions().getAutomaticAction(), location != null && location.getName() != null ? location.getName() : null);
		if (autoAction != null) {
			if (autoAction.isDie()) {
				GameUtils.die();
			}
			if (autoAction.getAccomplishGoal() != null) {
				Iterator<String> ait = autoAction.getAccomplishGoal().iterator();
				boolean found = false;
				while (!found && ait.hasNext()) {
					String goalName = ait.next();
					Goal goal = GameUtils.getElement(new GoalNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameGoals().getGoal(), goalName);
					GoalUtils.accomplishGoal(goal);
				}
			}
		}
	}

	private static Location getOtherLocation(Path path, Location location) {
		Location other = null;
		String toName = path.getToLocation();
		String fromName = path.getFromLocation();
		System.out.println("Path:" + path);
		System.out.println("Current Location:" + location);
		System.out.println("fromLocation:" + fromName);
		System.out.println("toLocation:" + toName);
		if (location != null && location.getName() != null && location.getName().equalsIgnoreCase(toName))
			other = GameUtils.getLocation(fromName);
		if (location != null && location.getName() != null && location.getName().equalsIgnoreCase(fromName))
			other = GameUtils.getLocation(toName);
		return other;
	}

	private static boolean canGoDirection(Boundary boundary) {
		//		System.out.println("boundary:" + boundary.getName());
		System.out.println("boundary:" + boundary);
		if (boundary != null) {
			System.out.println("boundary Name:" + boundary.getName());
			System.out.println("isPath?" + (boundary instanceof Path));
			System.out.println("IsDoor?" + (boundary instanceof Door));
			System.out.println("is door Open?" + ((Door) boundary).getStatus().value().equalsIgnoreCase(ObjectStatus.OPEN.name()));
			return (((boundary instanceof Path || (boundary instanceof Door && ((Door) boundary).getStatus().value().equalsIgnoreCase(ObjectStatus.OPEN.name())))));
		} else
			return false;
	}

	private static boolean checkMovementPreconditions(Location location, GameAction gAction) {
		// Drools?¿
		return true;
	}
}
