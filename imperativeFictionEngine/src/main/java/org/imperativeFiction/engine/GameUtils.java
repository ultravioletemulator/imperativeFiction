package org.imperativeFiction.engine;

import javazoom.jl.decoder.JavaLayerException;
import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.core.UnknownCommandException;
import org.imperativeFiction.generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by developer on 7/23/15.
 */
public class GameUtils {

	private static Logger logger = LoggerFactory.getLogger(GameUtils.class);

	public static void playMusic(File file) throws FileNotFoundException, JavaLayerException {
		//		FileInputStream fis = new FileInputStream(file);
		//Player player = new Player(fis);
		//player.play();
	}

	public static void showArtwork(String file) throws GameException {
		BufferedImage img = null;
		//		try {
		//			String fileName = "test-classes/" + file;
		//			logger.debug("reading image:" + fileName);
		//			img = ImageIO.read(new File(fileName));
		//		} catch (IOException e) {
		//			throw new GameException(e);
		//		}
		//		java.awt.Image image = img;
		//		javax.swing.JFrame frame = new javax.swing.JFrame();
		//		JLabel lblimage = new JLabel(new ImageIcon(image));
		//		frame.add(lblimage);
		//		frame.show();
	}

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
					res.setAction(supAction);
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

	public static ObjectType findObject(String objName) {
		ObjectType obj = null;
		if (objName != null) {
			Iterator<GameObjects> git = GameExecutor.getRunningGame().getDefinition().getGameObjects().iterator();
			boolean found = false;
			while (git.hasNext() && !found) {
				GameObjects gobj = git.next();
				if (gobj != null) {
					ObjectType objNew = gobj.getObject();
					if (objNew != null && objNew.getName() != null && objNew.getName().trim().toLowerCase().startsWith(objName.trim().toLowerCase())) {
						obj = objNew;
						found = true;
					}
				}
			}
		}
		return obj;
	}

	private static boolean checkPreconditions(GameAction gAction, ObjectType obj) {
		// Drools?¿
		return true;
	}


	public static Boundary getBoundary(String boundaryName) {
		boolean found = false;
		Boundary res = null;
		if (boundaryName != null) {
			if ("wall".equalsIgnoreCase(boundaryName) || "emptyBoundary".equalsIgnoreCase(boundaryName)) {
				res = new Wall();
			}
			Iterator<Boundary> lit = GameExecutor.getRunningGame().getDefinition().getBoundaries().getBoundary().iterator();
			while (!found && lit.hasNext()) {
				Boundary loc = lit.next();
				if (loc != null && boundaryName.equalsIgnoreCase(loc.getName())) {
					res = loc;
				}
			}
		}
		return res;
	}

	public static Location getLocation(String locationName) {
		boolean found = false;
		Location res = null;
		if (locationName != null) {
			Iterator<Location> lit = GameExecutor.getRunningGame().getDefinition().getLocations().getLocation().iterator();
			while (!found && lit.hasNext()) {
				Location loc = lit.next();
				if (loc != null && locationName.equalsIgnoreCase(loc.getName())) {
					res = loc;
				}
			}
		}
		return res;
	}

	public static ActionResponse getObject(GameAction gAction) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		if (gAction != null && gAction.getParameters() != null && gAction.getParameters().size() > 0) {
			ObjectType obj = findObject(gAction.getParameters().get(0));
			if (obj != null) {
				GameExecutor.getInventory().getObjectName().add(obj);
			}
			response.setResponse("You got " + obj.getName());
		} else {
			response.setResponse("I don't know what to " + gAction.getAction().getName());
		}
		return response;
	}

	private static String getStatusMessage(ObjectType obj) {
		StringBuilder sb = new StringBuilder();
		sb.append("The ").append(obj.getName() + " is now " + obj.getStatus().name());
		return sb.toString();
	}

	public static ActionResponse setObjectsStatus(GameAction gAction, ObjectStatus status) {
		ActionResponse response = GameExecutor.getFactory().createActionResponse();
		StringBuilder sb = new StringBuilder();
		for (GameObjects obj : GameExecutor.getRunningGame().getDefinition().getGameObjects()) {
			ObjectType objt = obj.getObject();
			if (checkPreconditions(gAction, objt)) {
				objt.setStatus(status);
				sb.append(getStatusMessage(objt));
			}
		}
		response.setResponse(sb.toString());
		return response;
	}
}
