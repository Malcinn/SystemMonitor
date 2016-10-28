package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import pl.lodz.uni.math.app.systemmanager.client.services.ClientListenerFactory;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientSenderFactory;
public class Client {
	
	private int localPortNumber = 0;

	private String serverAddress = null;

	private int serverPortNumber = 0;

	private ThreadFactory threadFactory = null;

	private ClientListenerFactory clientListenerFactory;

	private ClientSenderFactory senderFactory = null;

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

	public Client(ThreadFactory threadFactory, ClientListenerFactory clientListenerFactory, ClientSenderFactory senderFactory,
			int localPortNumber, String serverAddress, int serverPortNumber) {
		this.threadFactory = threadFactory;
		this.clientListenerFactory = clientListenerFactory;
		this.senderFactory = senderFactory;
		this.localPortNumber = localPortNumber;
		this.serverAddress = serverAddress;
		this.serverPortNumber = serverPortNumber;
	}

	public void run() throws IOException, ClassNotFoundException {
		try {
			ClientListener clientListener = clientListenerFactory.createClientListener(localPortNumber);
			threadFactory.newThread(clientListener).start();
			ClientSender sender = senderFactory.createSender(serverAddress, serverPortNumber);
			sender.sendData(clientListener.getSocketInfo());
			sender.closeConnections();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
