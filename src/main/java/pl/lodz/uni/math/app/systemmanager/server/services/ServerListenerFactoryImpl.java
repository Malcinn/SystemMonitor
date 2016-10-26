package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import javax.net.ServerSocketFactory;

import pl.lodz.uni.math.app.systemmanager.server.ServerListener;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

public class ServerListenerFactoryImpl implements ServerListenerFactory {

	@Override
	public ServerListener createServerListener(int localPortNumber) throws IOException {
		try {
			return new ServerListener(ServerSocketFactory.getDefault().createServerSocket(localPortNumber),
					new ThreadFactoryImpl(), new ServerThreadFactoryImpl());
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public ServerListener createServerListener(int localPortNumber, ThreadFactory threadFactory,
			ServerThreadFactory serverThreadFactory) throws IOException {
		try {
			return new ServerListener(ServerSocketFactory.getDefault().createServerSocket(localPortNumber),
					threadFactory, serverThreadFactory);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
