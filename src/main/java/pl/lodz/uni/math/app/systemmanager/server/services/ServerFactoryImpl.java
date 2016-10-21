package pl.lodz.uni.math.app.systemmanager.server.services;

import pl.lodz.uni.math.app.systemmanager.server.Server;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

public class ServerFactoryImpl implements ServerFactory{

	@Override
	public Server createServer(int localPortNumber) {
		return new Server(localPortNumber, new ServerListenerFactoryImpl(), new ThreadFactoryImpl());
	}

}
