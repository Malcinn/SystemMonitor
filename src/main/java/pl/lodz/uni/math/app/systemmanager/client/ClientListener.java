package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ClientListener implements Runnable {

	private ServerSocket serverSocket = null;

	private ThreadFactory threadFactory = null;
	
	private boolean active = true;

	public ClientListener(ServerSocket serverSocket, ThreadFactory threadFactory, boolean active) throws IOException {
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
				e.printStackTrace();
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
