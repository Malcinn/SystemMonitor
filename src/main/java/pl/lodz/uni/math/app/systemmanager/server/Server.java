package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import pl.lodz.uni.math.app.systemmanager.server.services.ServerListenerFactory;

public class Server {

	private int localPortNumber = 0;

	private ServerListenerFactory serverListenerFactory = null;

	private ThreadFactory threadFactory = null;

	public Server(int localPortNumber, ServerListenerFactory serverListenerFactory, ThreadFactory threadFactory) {
		this.localPortNumber = localPortNumber;
		this.serverListenerFactory = serverListenerFactory;
		this.threadFactory = threadFactory;
	}

	public void run() throws IOException {
		try {
			ServerListener serverListener = serverListenerFactory.createServerListener(localPortNumber, true);
			threadFactory.newThread(serverListener).start();
		} catch (Exception e) {
			throw new IOException(e);
		}
		
	}

}
