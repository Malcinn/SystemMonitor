package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;

import javax.net.ServerSocketFactory;

import pl.lodz.uni.math.app.systemmanager.client.ClientListener;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

public class ClientListenerFactoryImpl implements ClientListenerFactory {

	public ClientListener createClientListener(int portNumber, boolean active) throws IOException {
		try {
			return new ClientListener(ServerSocketFactory.getDefault().createServerSocket(portNumber),
					new ThreadFactoryImpl(), active);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
