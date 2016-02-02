package app.exceptions;

/**
 * app.exceptions Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class SleepException extends Exception {
	public SleepException() {
		super();
	}
	public SleepException(Throwable cause) {
		super(cause);
	}
	public SleepException(String message) {
		super(message);
	}
	public SleepException(String message, Throwable cause) {
		super(message, cause);
	}
}
