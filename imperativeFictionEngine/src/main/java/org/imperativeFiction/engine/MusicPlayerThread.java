package org.imperativeFiction.engine;

import javazoom.jl.decoder.JavaLayerException;
import org.apache.commons.io.FileUtils;
import org.imperativeFiction.generated.File;
import org.imperativeFiction.utils.GameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by developer on 7/29/15.
 */
public class MusicPlayerThread extends Thread {

	private Logger logger = LoggerFactory.getLogger(MusicPlayerThread.class);

	@Override
	public synchronized void run() {
		logger.debug("Running Music player thread");
		if (GameEngine.getExConfig().isMusic()) {
			while (true) {
				try {
					if (GameExecutor.getRunningGame() != null && GameExecutor.getRunningGame().getMusic() != null && GameExecutor.getRunningGame().getMusic().getFile() != null && GameExecutor.getRunningGame().getMusic().getFile().size() > 0) {
						//						String fileName = GameExecutor.getRunningGame().getMusic().getFile().get(0).getPath();
						for (File file : GameExecutor.getRunningGame().getMusic().getFile())
							if (file != null) {
								String fileName = file.getPath();
								java.io.File f = null;
								if (fileName != null && fileName.startsWith("http")) {
									logger.debug("Getting:" + fileName);
									f = java.io.File.createTempFile("imperativeFictionMusic", "mp3tmp");
									f.deleteOnExit();
									FileUtils.copyURLToFile(new URL(fileName), f);
								} else {
									f = new java.io.File(fileName);
								}
								logger.debug("Playing :" + f.getName());
								GameUtils.playMusic(f);
								logger.debug(fileName + " finished.");
							}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			GameEngine.getPresentation().presentText("Music is DISABLED.");
		}
	}
}
