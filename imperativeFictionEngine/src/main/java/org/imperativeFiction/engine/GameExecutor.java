package org.imperativeFiction.engine;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.imperativeFiction.generated.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by developer on 7/22/15.
 */
public class GameExecutor {

	private Logger logger = LoggerFactory.getLogger(GameExecutor.class);

	public void executeGame(Game game) throws GameException {
		try {
			playMusic(new File(game.getMusic().getFile().get(0).getPath()));
		} catch (FileNotFoundException e) {
			throw new GameException(e);
		} catch (JavaLayerException e) {
			throw new GameException(e);
		}
	}

	private void playMusic(File file) throws FileNotFoundException, JavaLayerException {
		FileInputStream fis = new FileInputStream(file);
		//Player player = new Player(fis);
		//player.play();
	}
}
