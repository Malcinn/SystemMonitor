package pl.lodz.uni.math.app.systemmanager.client.services;

import pl.lodz.uni.math.app.systemmanager.client.Client;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

public class ClientFactory {

	public Client createClient(int localPortNumber, String serverAddress, int serverPortNumber) {
		return new Client(new ThreadFactoryImpl(), new ClientListenerFactory(), new SenderFactory(), localPortNumber,
				serverAddress, serverPortNumber);
	}
}
