package org.tads.gameRunner.core;

import java.util.Vector;
import java.util.List;

/**
 * Created by developer on 7/1/15.
 */
public class TadsUtils {

	public static GameFormat getFormat(String filename) {
		GameFormat format = null;
		if (filename != null && filename.endsWith(GameFormat.tads2.getExtension())) {
			format = GameFormat.tads2;
		} else if (filename != null && filename.endsWith(GameFormat.tads3.getExtension())) {
			format = GameFormat.tads3;
		}
		return format;
	}

	public static Vector listToVector(List elemList) {
		return new Vector(elemList);
	}

	public static Vector[] listToVectorList(List elemList) {
		Vector[] res = new Vector[3];
		int i = 0;
		for (Object obj : elemList) {
			Vector vec = new Vector(elemList);
			res[i] = vec;
			i++;
		}
		return res;
	}
}
