package org.tads.gameRunner;

import java.io.File;
import java.io.IOException;

import org.tads.engines.tads2.jetty.GameWindow;
import org.tads.gameRunner.engine.Engine;
import org.tads.gameRunner.core.GameFormat;
import org.tads.gameRunner.core.TadsUtils;

/**
 * Created by developer on 7/1/15.
 */
public class GameRunner {

	private GameWindow _display = null;

	public void runGame(String fileName) throws IOException {
		System.out.println("runGame:" + fileName);
		String[] fonts = new String[3];
		String[] fg_colors = new String[3];
		String[] bg_colors = new String[3];
		//fonts[0] = getParameter("statusFont");
		fonts[0] = "Arial";
		//		fg_colors[0] = getParameter("statusForegroundColor");
		fg_colors[0] = "white";
		//bg_colors[0] = getParameter("statusBackgroundColor");
		bg_colors[0] = "black";
		//		fonts[1] = getParameter("mainFont");
		fonts[1] = "Courier";
		//fg_colors[1] = getParameter("mainForegroundColor");
		fg_colors[1] = "blue";
		//		bg_colors[1] = getParameter("mainBackgroundColor");
		bg_colors[1] = "white";
		//		fonts[2] = getParameter("inputFont");
		fonts[2] = "Arial";
		//		fg_colors[2] = getParameter("inputForegroundColor");
		fg_colors[2] = "blue";
		//bg_colors[2] = getParameter("cursorColor");
		bg_colors[2] = "black";
		int[] font_sizes = { 0, 0, 0 };
		_display = new GameWindow(fonts, font_sizes, fg_colors, bg_colors);
		GameFormat format = TadsUtils.getFormat(fileName);
		System.out.println("GameFormat:" + format);
		File gameFile = new File(fileName);
		Engine engine = EngineSelector.selectEngine(gameFile);
		System.out.println("SelectedEngine:" + engine);
		engine.run(gameFile);
		/*
		 * 
		 * switch (format) { case tads2: System.out.println("Running Jetty...");
		 * Jetty tads2Interpreter = new Jetty(new ConsoleIO(),
		 * FileUtils.openInputStream(gameFile));
		 * System.out.println("Loading Game:" + gameFile); if
		 * (tads2Interpreter.load()) { System.out.println("Running Game: " +
		 * gameFile); tads2Interpreter.run(); } break; case tads3: break; case
		 * glulx: // /mnt/shared/software/textAdventures/games/2044.gam //Run
		 * zag Main.main(new String[] { fileName }); break; case zcode5:
		 * ZJApp.main(new String[] { fileName }); break; case zcode8:
		 * ZJApp.main(new String[] { fileName }); break; default: break; }
		 */
	}
}
