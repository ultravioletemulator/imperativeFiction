package org.imperativeFiction.utils;

import org.imperativeFiction.core.equals.AutomaticActionLocationNameEquals;
import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.core.equals.GoalNameEquals;
import org.imperativeFiction.engine.GameExecutor;
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
			logger.debug("Movement:" + param);
			Movements movement = param != null ? Movements.valueOf(param.toUpperCase()) : null;
			if (movement != null) {
				switch (movement) {
				case NORTH:
					logger.debug("North..." + location);
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
			}
			boolean canGo = canGoDirection(boundary);
			//			System.out.println("canGo?" + canGo);
			if (canGo) {
				GameExecutor.getGameState().setLocation(getOtherLocation((Path) boundary, location));
				response.setResponse("You go " + param + " to " + location.getName());
			} else {
				response.setResponse("Could not go to the " + param);
			}
			logger.debug("Executing location actions...");
			ActionResponse autoActionResponse = executeAutomaticAction(GameExecutor.getGameState().getLocation());
			if (autoActionResponse != null && autoActionResponse.isQuit() != null && autoActionResponse.isQuit())
				response = autoActionResponse;
			else
				response = GameUtils.mergeActionResponses(response, autoActionResponse);
		}
		return response;
	}

	private static ActionResponse executeAutomaticAction(Location location) {
		//		... Get Automatic Action with location ...
		ActionResponse resp = new ActionResponse();
		logger.debug("executing action " + location);
		AutomaticAction autoAction = GameUtils.getElement(new AutomaticActionLocationNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameAutomaticActions().getAutomaticAction(), location != null && location.getName() != null ? location.getName() : null);
		logger.debug("Automatic action " + autoAction);
		if (autoAction != null) {
			if (autoAction.isDie() != null && autoAction.isDie()) {
				resp = GameUtils.die();
			}
			if (autoAction.getAccomplishGoal() != null) {
				logger.debug("Get accomplishedGoal");
				Iterator<String> ait = autoAction.getAccomplishGoal().iterator();
				boolean found = false;
				while (!found && ait.hasNext()) {
					String goalName = ait.next();
					logger.debug("Goal name: " + goalName);
					Goal goal = GameUtils.getElement(new GoalNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameGoals().getGoal(), goalName);
					logger.debug("Goal:" + goal);
					resp = GoalUtils.accomplishGoal(goal);
				}
			}
		}
		return resp;
	}

	private static Location getOtherLocation(Path path, Location location) {
		Location other = null;
		String toName = path.getToLocation();
		String fromName = path.getFromLocation();
		logger.debug("Path:" + path);
		logger.debug("Current Location:" + location);
		logger.debug("fromLocation:" + fromName);
		logger.debug("toLocation:" + toName);
		if (location != null && location.getName() != null && location.getName().equalsIgnoreCase(toName))
			other = GameUtils.getLocation(fromName);
		if (location != null && location.getName() != null && location.getName().equalsIgnoreCase(fromName))
			other = GameUtils.getLocation(toName);
		return other;
	}

	private static boolean canGoDirection(Boundary boundary) {
		//		System.out.println("boundary:" + boundary.getName());
		logger.debug("boundary:" + boundary);
		if (boundary != null) {
			logger.debug("boundary Name:" + boundary.getName());
			logger.debug("isPath?" + (boundary instanceof Path));
			logger.debug("IsDoor?" + (boundary instanceof Door));
			logger.debug("Door Status:" + ((Door) boundary).getDoorStatus().value());
			logger.debug("is door Open?" + ((Door) boundary).getDoorStatus().value().equalsIgnoreCase(ObjectStatus.OPEN.name()));
			boolean res = ((((boundary instanceof Path && !(boundary instanceof Door)) || (boundary instanceof Door && ((Door) boundary).getDoorStatus().value().equalsIgnoreCase(ObjectStatus.OPEN.name())))));
			logger.debug("canGo:" + res);
			return res;
		} else
			return false;
	}

	private static boolean checkMovementPreconditions(Location location, GameAction gAction) {
		// Drools?¿
		return true;
	}

	public static boolean isBoundaryCrossable(String boundaryName) {
		if (boundaryName == null || boundaryName != null && (boundaryName.equalsIgnoreCase("emptyBoundary") || boundaryName.equalsIgnoreCase("wall"))) {
			return false;
		} else
			return true;
	}
}
