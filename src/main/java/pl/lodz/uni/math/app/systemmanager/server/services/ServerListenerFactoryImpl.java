package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;

import javax.net.ServerSocketFactory;

import pl.lodz.uni.math.app.systemmanager.server.ServerListener;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

public class ServerListenerFactoryImpl implements ServerListenerFactory {

	@Override
	public ServerListener createServerListener(int localPortNumber, boolean active) throws IOException {
		try {
			return new ServerListener(ServerSocketFactory.getDefault().createServerSocket(localPortNumber), active,
					new ThreadFactoryImpl(), new ServerThreadFactoryImpl());
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
