package org.tads.gameRunner.engine;

import org.apache.commons.io.FileUtils;
import org.tads.engines.tads2.jetty.Jetty;
import org.tads.gameRunner.core.GameFormat;
import org.tads.gameRunner.io.ConsoleIO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 7/3/15.
 */
public class Tads2Engine extends Engine {

	public Tads2Engine() {
		this.setName(GameFormat.tads2.name());
		this.setMainClassName("org.tads.engines.tads2.jetty.Jetty");
		List<String> jettyFormats = new ArrayList<String>();
		jettyFormats.add(GameFormat.tads2.getExtension());
		this.setSupportedFormats(jettyFormats);
	}

	@Override
	public void run(File gameFile) throws IOException {
		System.out.println("Running Jetty...");
		Jetty tads2Interpreter = new Jetty(new ConsoleIO(), FileUtils.openInputStream(gameFile));
		System.out.println("Loading Game:" + gameFile);
		if (tads2Interpreter.load()) {
			System.out.println("Running Game: " + gameFile);
			tads2Interpreter.run();
		}
	}
}
