package org.tads.gameRunner.core;

/**
 * Created by developer on 7/1/15.
 */
public enum GameFormat {
	tads2(".gam"), tads3(".t3"), glulx(".blb"), zcode(".z"), zcode5(".z5"), zcode8(
			".z8"), hugo(".hug"), blorb(".blorb");

	private final String extension;

	GameFormat(String val) {
		this.extension = val;
	}

	public String getExtension() {
		return extension;
	}
}
