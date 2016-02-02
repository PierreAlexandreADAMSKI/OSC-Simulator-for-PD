package app.exceptions;

import javax.sound.midi.Track;

/**
 * app.exceptions Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class OSCReceiveException extends Throwable {
	public OSCReceiveException() {
		super();
	}
	public OSCReceiveException(Throwable cause) {
		super(cause);
	}
	public OSCReceiveException(String message) {
		super(message);
	}
	public OSCReceiveException(String message, Throwable cause) {
		super(message, cause);
	}
}
