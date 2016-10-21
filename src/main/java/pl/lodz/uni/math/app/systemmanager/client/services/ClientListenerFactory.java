package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;

import pl.lodz.uni.math.app.systemmanager.client.ClientListener;

public interface ClientListenerFactory {

	public ClientListener createClientListener(int portNumber, boolean active) throws IOException;

}
