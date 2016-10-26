package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.net.Socket;

public interface ServerThreadFactory {

	public ServerThread newServerThread(Socket socket) throws IOException;

}
