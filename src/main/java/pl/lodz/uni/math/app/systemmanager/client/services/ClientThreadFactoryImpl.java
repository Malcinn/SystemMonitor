package pl.lodz.uni.math.app.systemmanager.client.services;

import java.io.IOException;
import java.net.Socket;

import pl.lodz.uni.math.app.systemmanager.client.ClientThread;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfoFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfoFactoryImpl;

public class ClientThreadFactoryImpl implements ClientThreadFactory {

	@Override
	public ClientThread createClientThread(Socket socket) throws IOException {
		try {
			return new ClientThread(socket, new SystemInfoFactoryImpl());
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public ClientThread createClientThread(Socket socket, SystemInfoFactory systemInfoFactory) throws IOException {
		try {
			return new ClientThread(socket, systemInfoFactory);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
