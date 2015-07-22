package org.imperativeFiction.engine;

/**
 * Created by developer on 7/22/15.
 */
public class GameException extends Exception {

	public GameException() {
		super();
	}

	public GameException(Throwable cause) {
		super(cause);
	}

	public GameException(String message) {
		super(message);
	}

	public GameException(String message, Throwable cause) {
		super(message, cause);
	}
}
