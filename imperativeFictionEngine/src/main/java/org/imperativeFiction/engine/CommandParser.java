package org.imperativeFiction.engine;

import org.imperativeFiction.core.ActionNameEquals;
import org.imperativeFiction.core.ActionTypes;
import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.core.UnknownCommandException;
import org.imperativeFiction.generated.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by developer on 7/24/15.
 */
public class CommandParser {

	public static Logger logger = LoggerFactory.getLogger(CommandParser.class);

	private static String cleanCommand(String command) {
		return command;
	}

	public static GameAction parseCommand(org.imperativeFiction.generated.GenericActions actions, String command) throws UnknownCommandException {
		logger.debug("Parsing " + command);
		System.out.println("Parsing " + command);
		GameAction gAction = new GameAction();
		boolean found = false;
		Iterator<Action> ait = actions.getAction().iterator();
		while (!found && ait.hasNext()) {
			org.imperativeFiction.generated.Action supAct = ait.next();
			gAction = matches(supAct, cleanCommand(command));
			System.out.println("gAction:" + gAction);
			found = (gAction != null);
		}
		if (!found) {
			throw new UnknownCommandException("Sorry, I could not undestand what " + command + " means.");
		}
		return gAction;
	}

	private static GameAction matches(Action supAction, String command) {
		logger.debug("matchesL:");
		System.out.println("matchesS:" + command + " action" + supAction.getName() + "?");
		GameAction res = null;
		if (supAction != null && supAction.getName() != null && command != null) {
			//			System.out.println("Not empty");
			String supActCom = supAction.getName().trim().toLowerCase();
			String comQuery = command.trim().toLowerCase();
			System.out.println("Command:" + comQuery + " Action:" + supActCom + " ?" + comQuery.startsWith(supActCom));
			String commandToParse = command.trim().toLowerCase();
			StringTokenizer st = new StringTokenizer(commandToParse, " ");
			int i = 0;
			while (st.hasMoreTokens()) {
				String token = st.nextToken();
				System.out.println("token :" + token);
				if (i == 0) {
					res = new GameAction();
					res.setAction(getActionFromCommand(token));
				} else {
					if (res != null && res.getParameters() == null)
						res.setParameters(new ArrayList<String>());
					res.getParameters().add(token);
				}
				i++;
			}
			//			if (comQuery.startsWith(supActCom)) {
			//				res = new GameAction();
			//				// GEt Basic Action of command
			//				res.setAction(supAction);
			//				//			 Parse parameters
			//				String params = command.trim().substring(supAction.getName().trim().length() + 1, command.length() - 1);
			//				System.out.println("Params:" + params);
			//				StringTokenizer st = new StringTokenizer(params.trim(), " ");
			//				while (st.hasMoreTokens()) {
			//					String param = st.nextToken();
			//					if (res != null && res.getParameters() == null)
			//						res.setParameters(new ArrayList<String>());
			//					res.getParameters().add(param);
			//				}
			//			}
		}
		return res;
	}

	public static Action getActionFromCommand(String command) {
		Action action = null;
		if (command != null) {
			ActionTypes actType = ActionTypes.valueOf(command.trim());
			Action act = actType != null ? GameUtils.getElement(new ActionNameEquals(), GameExecutor.getRunningGame().getDefinition().getGenericActions().getAction(), actType.name()) : null;
			action = act;
		}
		return action;
	}
}
