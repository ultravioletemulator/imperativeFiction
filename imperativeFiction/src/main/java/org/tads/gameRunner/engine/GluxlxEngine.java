package org.tads.gameRunner.engine;

import org.p2c2e.zag.Main;
import org.tads.gameRunner.core.GameFormat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 7/3/15.
 */
public class GluxlxEngine extends Engine {

	public GluxlxEngine() {
		List<String> zagFormats = new ArrayList<String>();
		zagFormats.add(GameFormat.glulx.getExtension());
		this.setSupportedFormats(zagFormats);
		this.setName(GameFormat.glulx.name());
		this.setMainClassName("org.p2c2e.zag.Main");
	}

	@Override
	public void run(File file) throws IOException {
		Main.main(new String[] { file.getAbsolutePath() });
	}
}
