package org.imperativeFiction.core;

/**
 * Created by developer on 7/22/15.
 */
public class UnknownCommandException extends Exception {

	public UnknownCommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnknownCommandException(String message) {
		super(message);
	}

	public UnknownCommandException(Throwable cause) {
		super(cause);
	}

	public UnknownCommandException() {
		super();
	}
}
