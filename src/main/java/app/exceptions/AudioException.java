package app.exceptions;

/**
 * app.exceptions Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class AudioException extends Exception {
	public AudioException() {
		super();
	}
	public AudioException(Throwable cause) {
		super(cause);
	}
	public AudioException(String message) {
		super(message);
	}
	public AudioException(String message, Throwable cause) {
		super(message, cause);
	}
}
