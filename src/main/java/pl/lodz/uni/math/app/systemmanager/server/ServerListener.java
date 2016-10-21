package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.server.services.ServerThreadFactory;

public class ServerListener implements Runnable {

	private static final Logger log = LogManager.getLogger(ServerListener.class.getName());

	private ServerSocket serverSocket = null;

	private boolean active = true;

	private ThreadFactory threadFactory = null;

	private ServerThreadFactory serverThreadFactory = null;

	public ServerListener(ServerSocket serverSocket, boolean active, ThreadFactory threadFactory,
			ServerThreadFactory serverThreadFactory) {
		this.serverSocket = serverSocket;
		this.setActive(active);
		this.threadFactory = threadFactory;
		this.serverThreadFactory = serverThreadFactory;
	}

	@Override
	public void run() {
		while (isActive()) {
			Socket socket;
			try {
				socket = serverSocket.accept();
				ServerThread serverThread = serverThreadFactory.newserverThread(socket);
				threadFactory.newThread(serverThread).start();
			} catch (IOException e) {
				log.error("IOException ecurred while performing run() method of ServerListener object. Exception:", e);
			}

		}
	}

	public boolean isActive() {
		return active;
	}

	public synchronized void setActive(boolean active) {
		this.active = active;
	}

}
