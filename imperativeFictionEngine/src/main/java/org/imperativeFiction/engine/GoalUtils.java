package org.imperativeFiction.engine;

import org.imperativeFiction.generated.Goal;

/**
 * Created by developer on 7/27/15.
 */
public class GoalUtils {

	public static void accomplishGoal(Goal goal) {
		if (goal != null) {
			GameExecutor.getGameState().getGainedGoals().getGainedGoal().add(goal.getName());

		}
	}
}
