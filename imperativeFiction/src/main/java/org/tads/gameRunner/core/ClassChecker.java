package org.tads.gameRunner.core;

/**
 * Created by developer on 7/3/15.
 */
public class ClassChecker {

	public static boolean checkClass(String className) {
		boolean res = false;
		try {
			ClassLoader.getSystemClassLoader().loadClass(className);
			res = true;
		} catch (ClassNotFoundException e) {
			res = false;
		}
		return res;
	}
}
