package org.imperativeFiction.presentations;

import org.imperativeFiction.engine.GameException;

import java.awt.*;

/**
 * Created by developer on 7/22/15.
 */
public interface Presentation {

	public void presentText(String text);

	public void presentLocation();

	public void presentAction();

	public void showImage(Image image);

	public String readCommand() throws GameException;
}
