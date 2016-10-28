package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;

public class ServerListener implements Runnable {

	private static final Logger log = LogManager.getLogger(ServerListener.class.getName());

	private ServerSocket serverSocket = null;

	private boolean active = true;

	private ThreadFactory threadFactory = null;

	private ServerThreadFactory serverThreadFactory = null;

	public ServerListener(ServerSocket serverSocket, ThreadFactory threadFactory,
			ServerThreadFactory serverThreadFactory) {
		this.serverSocket = serverSocket;
		this.threadFactory = threadFactory;
		this.serverThreadFactory = serverThreadFactory;
	}

	@Override
	public void run() {
		while (isActive()) {
			try {
				Socket socket = serverSocket.accept();
				ServerThread serverThread = serverThreadFactory.newServerThread(socket);
				threadFactory.newThread(serverThread).start();
			} catch (IOException e) {
				log.error("IOException ecurred while performing run() method of ServerListener object. Exception:", e);
				try {
					closeConnections();
					MyEntityManagerFactory.closeEntityManagerFactory();
				} catch (IOException e1) {
					log.error("IOException ecurred while closing the connections. Exception:", e1);
				}
			}
		}
	}

	public boolean isActive() {
		return active;
	}

	public synchronized void setActive(boolean active) {
		this.active = active;
	}

	private void closeConnections() throws IOException {
		try {
			this.setActive(false);
			serverSocket.close();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
