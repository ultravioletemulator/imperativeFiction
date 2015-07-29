package org.imperativeFiction.engine;

import javazoom.jl.decoder.JavaLayerException;
import org.imperativeFiction.generated.File;
import org.imperativeFiction.utils.GameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;

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
						int i = 0;
						for (File file : GameExecutor.getRunningGame().getMusic().getFile()) {
							//						String fileName = GameExecutor.getRunningGame().getMusic().getFile().get(0).getPath();
							if (file != null) {
								String fileName = file.getPath();
								logger.debug("Playing :" + fileName);
								GameUtils.playMusic(new java.io.File(fileName));
								logger.debug(fileName + " finished.");
							}
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (JavaLayerException e) {
					e.printStackTrace();
				}
			}
		} else {
			GameEngine.getPresentation().presentText("Music is DISABLED.");
		}
	}
}
