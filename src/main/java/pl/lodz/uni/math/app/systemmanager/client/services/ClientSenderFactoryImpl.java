package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;

import javax.net.SocketFactory;

import pl.lodz.uni.math.app.systemmanager.client.ClientSender;

public class ClientSenderFactoryImpl implements ClientSenderFactory{

	public ClientSender createSender(String host, int port) throws IOException {
		try {
			return new ClientSender(SocketFactory.getDefault().createSocket(host, port));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
