package pl.lodz.uni.math.app.systemmanager.client.services;

import pl.lodz.uni.math.app.systemmanager.client.Client;

public interface ClientFactory {

	public Client createClient(int localPortNumber, String serverAddress, int serverPortNumber);

}
