package org.imperativeFiction.engine;

import javazoom.jl.decoder.JavaLayerException;
import org.imperativeFiction.core.DirectionBoundary;
import org.imperativeFiction.core.GameAction;
import org.imperativeFiction.core.MovementTypes;
import org.imperativeFiction.core.UnknownCommandException;
import org.imperativeFiction.generated.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

	//	public static Boundary getBoundary(String boundaryName) {
	//		boolean found = false;
	//		Boundary res = null;
	//		if (boundaryName != null) {
	//			if ("wall".equalsIgnoreCase(boundaryName) || "emptyBoundary".equalsIgnoreCase(boundaryName)) {
	//				res = new Wall();
	//			}
	//			Iterator<Boundary> lit = GameExecutor.getRunningGame().getDefinition().getBoundaries().getBoundary().iterator();
	//			while (!found && lit.hasNext()) {
	//				Boundary loc = lit.next();
	//				if (loc != null && boundaryName.equalsIgnoreCase(loc.getName())) {
	//					res = loc;
	//				}
	//			}
	//		}
	//		return res;
	//	}
	public static Boundary getDoor(String boundaryName) {
		boolean found = false;
		Boundary res = null;
		if (boundaryName != null) {
			if ("wall".equalsIgnoreCase(boundaryName) || "emptyBoundary".equalsIgnoreCase(boundaryName)) {
				res = new Wall();
			}
			Iterator<Door> lit = GameExecutor.getRunningGame().getDefinition().getDoors().getDoor().iterator();
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

	public static List<DirectionBoundary> getPaths(Location location) {
		List<DirectionBoundary> boundaries = new ArrayList<DirectionBoundary>();
		if (location.getNorth() != null && !"emptyBoundary".equalsIgnoreCase(location.getNorth()) && !"wall".equalsIgnoreCase(location.getNorth())) {
			boundaries.add(new DirectionBoundary(MovementTypes.north, GameUtils.getDoor(location.getNorth())));
		}
		if (location.getSouth() != null && !"emptyBoundary".equalsIgnoreCase(location.getSouth()) && !"wall".equalsIgnoreCase(location.getSouth())) {
			boundaries.add(new DirectionBoundary(MovementTypes.south, GameUtils.getDoor(location.getSouth())));
		}
		if (location.getEast() != null && !"emptyBoundary".equalsIgnoreCase(location.getEast()) && !"wall".equalsIgnoreCase(location.getEast())) {
			boundaries.add(new DirectionBoundary(MovementTypes.east, GameUtils.getDoor(location.getEast())));
		}
		if (location.getWest() != null && !"emptyBoundary".equalsIgnoreCase(location.getWest()) && !"wall".equalsIgnoreCase(location.getWest())) {
			boundaries.add(new DirectionBoundary(MovementTypes.west, GameUtils.getDoor(location.getWest())));
		}
		if (location.getNortheast() != null && !"emptyBoundary".equalsIgnoreCase(location.getNortheast()) && !"wall".equalsIgnoreCase(location.getNortheast())) {
			boundaries.add(new DirectionBoundary(MovementTypes.northeast, GameUtils.getDoor(location.getNortheast())));
		}
		if (location.getNorthwest() != null && !"emptyBoundary".equalsIgnoreCase(location.getNorthwest()) && !"wall".equalsIgnoreCase(location.getNorthwest())) {
			boundaries.add(new DirectionBoundary(MovementTypes.northwest, GameUtils.getDoor(location.getNorthwest())));
		}
		if (location.getSoutheast() != null && !"emptyBoundary".equalsIgnoreCase(location.getSoutheast()) && !"wall".equalsIgnoreCase(location.getSoutheast())) {
			boundaries.add(new DirectionBoundary(MovementTypes.north, GameUtils.getDoor(location.getSoutheast())));
		}
		if (location.getSouthwest() != null && !"emptyBoundary".equalsIgnoreCase(location.getSouthwest()) && !"wall".equalsIgnoreCase(location.getSouthwest())) {
			boundaries.add(new DirectionBoundary(MovementTypes.north, GameUtils.getDoor(location.getSouthwest())));
		}
		return boundaries;
	}

	public static boolean isFirst(int i) {
		return i == 0;
	}

	//Automatic action executed on location entrance, object usage, etc...
	public void executeAutoAction() throws GameException {
		logger.debug("TODO executeAutoAction");
		throw new GameException("Unimplemented");
	}
}
