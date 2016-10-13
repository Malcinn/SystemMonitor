package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import javax.net.SocketFactory;

public class Client {

	private int localPortNumber = 0;

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
	public Client(int localPortNumber, String serverAddress, int serverPortNumber) {
		this.localPortNumber = localPortNumber;
		this.serverAddress = serverAddress;
		this.serverPortNumber = serverPortNumber;
	}

	public void run() throws IOException, ClassNotFoundException {
		try {
			ClientListener clientListener = new ClientListener(localPortNumber);
			new Thread(clientListener).start();
			Sender sender = new Sender(SocketFactory.getDefault().createSocket(serverAddress, serverPortNumber));
			sender.sendData(clientListener.getSocketInfo());
			sender.closeConnections();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
