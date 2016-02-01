package app.service;

import app.listeners.SimpleOSCListener;
import com.illposed.osc.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

/**
 * app.service Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class OSCService {

	public static int UDP_PORT;
	private OSCPortOut portOut;
	private OSCPortIn portIn;

	public OSCService(Way way) throws UnknownHostException, SocketException {
		if (way == Way.OUT) {
			portOut = new OSCPortOut();
		}
		if (way == Way.IN){
			portIn = new OSCPortIn(OSCPort.DEFAULT_SC_OSC_PORT);
		}
	}

	public OSCService(int port, Way way) throws UnknownHostException, SocketException {
		UDP_PORT = port;
		if (way == Way.OUT) {
			portOut = new OSCPortOut(InetAddress.getLocalHost(), UDP_PORT);
		}
		if (way == Way.IN){
			portIn = new OSCPortIn(UDP_PORT);
		}
	}

	public void sendMe(OSCMessage... messages) throws IOException {
		final OSCBundle bundle = new OSCBundle(Arrays.asList(messages));
		portOut.send(bundle);
	}

	public void sendMe(final String tag, Object... args) throws IOException {
		final OSCMessage message = new OSCMessage(tag, Arrays.asList(args));
		portOut.send(message);

	}

	//TODO works once in a while
	public void receiveMe(String tag) throws InterruptedException {
		OSCListener listener = (date, oscMessage) -> System.out.println("message received");
		portIn.addListener(tag, listener);
		portIn.startListening();
	}

}
