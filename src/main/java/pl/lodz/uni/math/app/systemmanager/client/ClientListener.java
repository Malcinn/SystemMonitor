package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.client.services.ClientThreadFactory;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ClientListener implements Runnable {

	private static final Logger log = LogManager.getLogger(ClientListener.class.getName());

	private ServerSocket serverSocket = null;

	private ThreadFactory threadFactory = null;

	private ClientThreadFactory clientThreadFactory = null;

	private boolean active = true;

	public ClientListener(ServerSocket serverSocket, ThreadFactory threadFactory,
			ClientThreadFactory clientThreadFactory) {
		this.serverSocket = serverSocket;
		this.threadFactory = threadFactory;
		this.clientThreadFactory = clientThreadFactory;
	}

	@Override
	public void run() {
		while (isActive()) {
			try {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = clientThreadFactory.createClientThread(socket);
				threadFactory.newThread(clientThread).start();
			} catch (IOException e) {
				log.error("Error ocurred while performing run method of the ClientListener object. Exception: ", e);
				try {
					closeConnections();
					MyEntityManagerFactory.closeEntityManagerFactory();
				} catch (IOException e1) {
					log.error("IOException ecurred while closing the connections. Exception:", e1);
				}
			}
		}
	}

	public SocketInfo getSocketInfo() {
		return new SocketInfo(serverSocket);
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
