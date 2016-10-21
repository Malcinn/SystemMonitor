package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.net.Socket;

import pl.lodz.uni.math.app.systemmanager.server.ServerThread;

public class ServerThreadFactoryImpl implements ServerThreadFactory {

	@Override
	public ServerThread newserverThread(Socket socket) throws IOException {
		try {
			return new ServerThread(socket);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
