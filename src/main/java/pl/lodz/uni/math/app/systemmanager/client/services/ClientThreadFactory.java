package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;
import java.net.Socket;

import pl.lodz.uni.math.app.systemmanager.client.ClientThread;
import pl.lodz.uni.math.app.systemmanager.shared.services.SystemInfoFactory;

public interface ClientThreadFactory {

	public ClientThread createClientThread(Socket socket) throws IOException;

	public ClientThread createClientThread(Socket socket, SystemInfoFactory systemInfoFactory) throws IOException;
}
