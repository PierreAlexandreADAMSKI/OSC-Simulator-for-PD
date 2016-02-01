package app.listeners;

import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;

import java.util.Date;

/**
 * app.listeners Created by Pierre-Alexandre Adamski on 31/01/2016.
 */
public class SimpleOSCListener implements OSCListener {

	private OSCMessage message;
	private boolean messageReceived = false;
	private Date receivedTimestamp = null;

	public OSCMessage getMessage() {
		return message;
	}

	public Date getReceivedTimestamp() {
		return receivedTimestamp;
	}

	public boolean isMessageReceived() {
		return messageReceived;
	}

	@Override
	public void acceptMessage(Date time, OSCMessage message) {
		messageReceived = true;
		receivedTimestamp = time;
		this.message = message;
	}
}
