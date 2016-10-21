package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ClientListener implements Runnable {

	private static final Logger log = LogManager.getLogger(ClientListener.class.getName());
	
	private ServerSocket serverSocket = null;

	private ThreadFactory threadFactory = null;
	
	private boolean active = true;

	public ClientListener(ServerSocket serverSocket, ThreadFactory threadFactory, boolean active) {
		this.serverSocket = serverSocket;
		this.threadFactory = threadFactory;
		this.active = active;
	}

	@Override
	public void run() {
		while (isActive()) {
			try {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				threadFactory.newThread(clientThread).start();
			} catch (IOException e) {
				log.error("Error ocurred while performing run method of the ClientListener object. Exception: ", e);
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

}
