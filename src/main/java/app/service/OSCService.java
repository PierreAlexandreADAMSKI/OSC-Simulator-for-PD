package app.service;

import com.illposed.osc.OSCBundle;
import com.illposed.osc.OSCMessage;
import com.illposed.osc.OSCPacket;
import com.illposed.osc.OSCPortOut;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * app.service Created by Pierre-Alexandre Adamski on 30/01/2016.
 */
public class OSCService {

	public static int UDP_PORT;
	private OSCPortOut portOut;

	public OSCService() throws UnknownHostException, SocketException {
		this.portOut = new OSCPortOut();
	}

	public OSCService(int port) throws UnknownHostException, SocketException {
		UDP_PORT = port;
		this.portOut = new OSCPortOut(InetAddress.getLocalHost(), UDP_PORT);
	}

	public void sendMe(OSCMessage... messages) throws IOException {
		final OSCBundle bundle = new OSCBundle(Arrays.asList(messages));
		portOut.send(bundle);
	}

	public void sendMe(final String tag, Object... args) throws IOException {
		final OSCMessage message = new OSCMessage(tag, Arrays.asList(args));
		portOut.send(message);

	}
}
