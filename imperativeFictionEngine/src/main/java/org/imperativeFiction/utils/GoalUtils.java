package org.imperativeFiction.utils;

import org.imperativeFiction.core.equals.GoalNameEquals;
import org.imperativeFiction.engine.GameExecutor;
import org.imperativeFiction.generated.ActionResponse;
import org.imperativeFiction.generated.GainedGoals;
import org.imperativeFiction.generated.Goal;

import java.util.ArrayList;

/**
 * Created by developer on 7/27/15.
 */
public class GoalUtils {

	public static ActionResponse accomplishGoal(Goal goal) {
		ActionResponse response = new ActionResponse();
		if (goal != null) {
			if (GameExecutor.getGameState().getGainedGoals() == null)
				GameExecutor.getGameState().setGainedGoals(new GainedGoals());
			if (GameExecutor.getGameState().getGainedGoals().getGainedGoal() == null)
				GameExecutor.getGameState().getGainedGoals().setGainedGoal(new ArrayList<String>());
			GameExecutor.getGameState().getGainedGoals().getGainedGoal().add(goal.getName());
			if (goal.getName().equals(GOAL_GAMEFINISHED))
				response.setQuit(true);
		}
		ActionResponse r = showGoals();
		response.setResponse("You accomplished " + goal.getName() + " goal.\n" + r.getResponse());
		return response;
	}

	public static ActionResponse showGoals() {
		ActionResponse resp = new ActionResponse();
		StringBuilder sb = new StringBuilder();
		sb.append("You already have accomplished the next goals:\n");
		for (String goalName : GameExecutor.getGameState().getGainedGoals().getGainedGoal()) {
			sb.append(goalName).append(": ");
			Goal goal = GameUtils.getElement(new GoalNameEquals(), GameExecutor.getRunningGame().getDefinition().getGameGoals().getGoal(), goalName);
			sb.append(goal.getDescription());
		}
		resp.setResponse(sb.toString());
		return resp;
	}

	public static String GOAL_GAMEFINISHED = "gameFinished";
}
