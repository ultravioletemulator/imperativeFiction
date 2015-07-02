package org.tads.interfaces;

import org.tads.gameRunner.GameRunner;

import java.io.IOException;

/**
 * Created by developer on 7/1/15.
 */
public class Cli {

	public static void main(String[] args) throws IOException {
		System.out.println("args:" + args);
		String filename = args[0];
		//		String filename= System.getProperty("game");
		GameRunner runner = new GameRunner();
		runner.runGame(filename);
	}
}
