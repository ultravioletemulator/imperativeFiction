package org.tads.gameRunner;

import org.tads.gameRunner.engine.Engine;
import org.tads.gameRunner.engine.GluxlxEngine;
import org.tads.gameRunner.engine.Tads2Engine;
import org.tads.gameRunner.engine.ZCodeEngine;

import java.io.File;
import java.util.*;

/**
 * Created by developer on 7/3/15.
 */
public class EngineSelector {

	private static List<Engine> engines = new ArrayList<Engine>();
	static {
		loadEngines();
	}

	private static void loadEngines() {
		Engine engTads2 = new Tads2Engine();
		engines.add(engTads2);
		//GLULX
		Engine engGlulx = new GluxlxEngine();
		engines.add(engGlulx);
		//Zcode
		Engine engZcode = new ZCodeEngine();
		engines.add(engZcode);
	}

	public static Engine selectEngine(File file) {
		System.out.println("selecting engine...");
		System.out.println("Supported engines are: " + engines);
		String filename = file.getName();
		String extension = filename.substring(filename.lastIndexOf('.'), filename.length());
		System.out.println("Game extension:" + extension);
		Engine selectedEngine = null;
		for (Engine eng : engines) {
			System.out.println("Checking engine:" + eng);
			Iterator<String> eit = eng.getSupportedFormats().iterator();
			boolean found = false;
			while (eit.hasNext() && !found) {
				String extFormat = eit.next();
				System.out.println("format:" + extFormat);
				if (extFormat != null && extension != null && extFormat.equalsIgnoreCase(extension)) {
					found = true;
					selectedEngine = eng;
				}
			}
		}
		return selectedEngine;
	}
}
