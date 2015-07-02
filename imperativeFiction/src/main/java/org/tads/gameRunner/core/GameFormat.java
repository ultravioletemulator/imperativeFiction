package org.tads.gameRunner.core;

/**
 * Created by developer on 7/1/15.
 */
public enum GameFormat {
	tads2(".gam"), tads3("t3");

	private final String extension;

	GameFormat(String val) {
		this.extension = val;
	}

	public String getExtension() {
		return extension;
	}
}
