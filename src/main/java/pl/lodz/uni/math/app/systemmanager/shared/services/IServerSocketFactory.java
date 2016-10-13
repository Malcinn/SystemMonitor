package pl.lodz.uni.math.app.systemmanager.shared.services;

import java.io.IOException;
import java.net.ServerSocket;

public interface IServerSocketFactory {

	public ServerSocket createServerSocket(int port) throws IOException;
}
