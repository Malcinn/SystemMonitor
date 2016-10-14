package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;

import pl.lodz.uni.math.app.systemmanager.client.services.ClientFactory;

public class Application {

	private static final String SERVER_ADDRESS = "127.0.0.1";

	private static final int SERVER_PORT_NUMBER = 2222;

	private static final int LOCAL_PORT_NUMBER = 2223;

	public static void main(String[] args) {
		int localPortNumber = 0;
		String serverAddress = null;
		int serverPortNumber = 0;
		/*
		 * Program takes 3 arguments : 1) port number on which client will be
		 * listening 2) server IP address 3) port number on which server
		 * application running If the number of arguments is different than 3,
		 * then IP address and port number are set to default values
		 */
		if (args.length == 3) {
			localPortNumber = Integer.parseInt(args[0]);
			serverAddress = args[1];
			serverPortNumber = Integer.parseInt(args[2]);
		} else {
			localPortNumber = LOCAL_PORT_NUMBER;
			serverAddress = SERVER_ADDRESS;
			serverPortNumber = SERVER_PORT_NUMBER;
		}
		Client client;
		try {
			ClientFactory clientFactory = new ClientFactory();
			client = clientFactory.createClient(localPortNumber, serverAddress, serverPortNumber);
			client.run();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
