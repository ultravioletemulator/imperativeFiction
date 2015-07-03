package org.tads.gameRunner.engine;

import org.tads.gameRunner.core.GameFormat;
import russotto.zplet.ZJApp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 7/3/15.
 */
public class ZCodeEngine extends Engine {

	public ZCodeEngine() {
		this.setName(GameFormat.zcode.name());
		this.setMainClassName("russotto.zplet.ZJApp");
		List<String> zcodeFormats = new ArrayList<String>();
		zcodeFormats.add(GameFormat.zcode.getExtension());
		zcodeFormats.add(GameFormat.zcode5.getExtension());
		zcodeFormats.add(GameFormat.zcode8.getExtension());
		this.setSupportedFormats(zcodeFormats);
	}

	@Override
	public void run(File file) throws IOException {
		ZJApp.main(new String[] { file.getAbsolutePath() });
	}
}
