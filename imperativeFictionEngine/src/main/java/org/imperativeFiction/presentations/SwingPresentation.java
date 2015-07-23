package org.imperativeFiction.presentations;

import org.imperativeFiction.engine.GameException;

import javax.swing.*;
import java.awt.*;

/**
 * Created by developer on 7/22/15.
 */
public class SwingPresentation implements Presentation {

	public void presentAction() {
	}

	public void presentLocation() {
	}

	public void presentText(String text) {
		System.out.println(text);
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
