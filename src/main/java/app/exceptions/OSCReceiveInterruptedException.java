package app.exceptions;

/**
 * app.exceptions Created by Pierre-Alexandre Adamski on 02/02/2016.
 */
public class OSCReceiveInterruptedException extends InterruptedException {
	public OSCReceiveInterruptedException() {
		super();
	}
	public OSCReceiveInterruptedException(String s) {
		super(s);
	}
}
