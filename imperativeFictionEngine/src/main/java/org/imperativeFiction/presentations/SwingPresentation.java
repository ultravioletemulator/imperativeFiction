package org.imperativeFiction.presentations;

import org.imperativeFiction.engine.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by developer on 7/22/15.
 */
public class SwingPresentation implements Presentation {

	private Logger logger = LoggerFactory.getLogger(SwingPresentation.class);

	public void presentAction() {
	}

	public void presentLocation() {
	}

	public void presentText(String text) {
		logger.debug(text);
	}

	public void showImage(Image image) {
		JFrame frame = new JFrame();
		JLabel lblimage = new JLabel(new ImageIcon(image));
		frame.getContentPane().add(lblimage, BorderLayout.CENTER);
		frame.setSize(image.getWidth(null), image.getHeight(null));
		frame.setVisible(true);
	}

	public String readCommand() throws GameException {
		return null;
	}
}
