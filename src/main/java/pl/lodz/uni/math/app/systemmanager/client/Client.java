package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class Client {

	private ServerSocket serverSocket = null;

	private String serverAddress = null;

	private int serverPortNumber = 0;

	/**
	 * Constructor with parameters.
	 * 
	 * @param localPortNumber
	 *            - port number on which client will be listening
	 * @param serverAddress
	 *            - IP address of the server
	 * @param serverPortNumber
	 *            - port number on which server application runs
	 * @throws IOException
	 */
	public Client(int localPortNumber, String serverAddress, int serverPortNumber)
			throws IOException {
		try {
			serverSocket = ServerSocketFactory.getDefault().createServerSocket(localPortNumber);
			this.serverAddress = serverAddress;
			this.serverPortNumber = serverPortNumber;
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Method performs actions:
	 * Send info about serverSocket to  Server
	 * then listens for connections from server
	 * 
	 * @throws ClassNotFoundException
	 */
	public void run() throws IOException, ClassNotFoundException {
		try {
			sendSocketInfoToServer();
			while (true) {
				Socket socket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(socket);
				new Thread(clientThread).start();
			}
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			serverSocket.close();
		}
	}

	private void sendSocketInfoToServer() throws IOException{
		Socket socket = null;
		ObjectOutputStream out = null;
		try {
			socket = SocketFactory.getDefault().createSocket(serverAddress, serverPortNumber);
			out = new ObjectOutputStream(socket.getOutputStream());
			SocketInfo socketInfo = new SocketInfo(serverSocket);
			out.writeObject(socketInfo.toString());
			out.flush();
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			out.close();
			socket.close();
		}
	}

}
