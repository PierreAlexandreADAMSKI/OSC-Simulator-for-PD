package app.exceptions;

/**
 * app.exceptions Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class OSCSendException extends Throwable{
	public OSCSendException() {
		super();
	}
	public OSCSendException(Throwable cause) {
		super(cause);
	}
	public OSCSendException(String message) {
		super(message);
	}
	public OSCSendException(String message, Throwable cause) {
		super(message, cause);
	}
}
