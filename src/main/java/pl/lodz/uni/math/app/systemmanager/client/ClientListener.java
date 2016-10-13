package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ClientListener implements Runnable {

	private ServerSocket serverSocket = null;

	public ClientListener(int localPortNumber) throws IOException {
		try {
			serverSocket = ServerSocketFactory.getDefault().createServerSocket(localPortNumber);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				new Thread(clientThread).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public SocketInfo getSocketInfo() {
		return new SocketInfo(serverSocket);
	}

}
