package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.net.Socket;

import pl.lodz.uni.math.app.systemmanager.server.ServerThread;

public interface ServerThreadFactory {

	public ServerThread newServerThread(Socket socket) throws IOException;

}
