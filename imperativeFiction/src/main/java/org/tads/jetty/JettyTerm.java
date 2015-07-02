package org.tads.jetty;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class JettyTerm {

	public static void main(String[] args) {
		GameTerminal term = new GameTerminal();
		try {
			FileInputStream file = new FileInputStream(args[0]);
			Jetty j = new Jetty(term, file);
			if (j.load())
				j.run();
		} catch (FileNotFoundException fnf) {
			System.err.println("Error: file not found (" + args[0] + "): " + fnf);
		}
	}
}
