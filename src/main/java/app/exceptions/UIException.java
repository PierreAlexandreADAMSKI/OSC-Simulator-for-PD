package app.exceptions;

import java.io.IOException;

/**
 * app.exceptions Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class UIException extends IOException {
	public UIException() {
		super();
	}
	public UIException(Throwable cause) {
		super(cause);
	}
	public UIException(String message) {
		super(message);
	}
	public UIException(String message, Throwable cause) {
		super(message, cause);
	}
}
