package org.imperativeFiction.presentations;

import org.imperativeFiction.engine.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * Created by developer on 7/22/15.
 */
public class SwingPresentation implements Presentation {

	private Logger logger = LoggerFactory.getLogger(SwingPresentation.class);

	public void init() {
		// Create images frame
		JFrame frame = new JFrame("imperativeFictionMain");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea textArea = new JTextArea();
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
		Image background = Toolkit.getDefaultToolkit().createImage("src/main/resources/images/infocom.png");
		logger.debug("Image:" + background.getSource());
		JLabel label = new JLabel(new ImageIcon(background));
		JLabel labelTitle = new JLabel();
		labelTitle.setText("Imperative Fiction");
		frame.add(label);
		frame.add(labelTitle);
		frame.pack();
		frame.setLocation(200, 200);
		frame.setSize(new Dimension(800, 600));
		frame.setVisible(true);
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
		//Read from STD input
		String command = null;
		Scanner console = new Scanner(System.in);
		if (console.hasNextLine()) {
			command = console.nextLine();
		}
		//		System.out.println("Command read: " + command);
		return command;
	}
}
