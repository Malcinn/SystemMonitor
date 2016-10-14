package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;

import javax.net.SocketFactory;

import pl.lodz.uni.math.app.systemmanager.client.Sender;

public class SenderFactory {

	public Sender createSender(String host, int port) throws IOException{
		try {
			return new Sender(SocketFactory.getDefault().createSocket(host, port));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
