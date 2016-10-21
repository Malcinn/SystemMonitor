package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;

import pl.lodz.uni.math.app.systemmanager.client.ClientSender;

public interface ClientSenderFactory {

	public ClientSender createSender(String host, int port) throws IOException;

}
