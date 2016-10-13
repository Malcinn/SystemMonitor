package pl.lodz.uni.math.app.systemmanager.shared.services;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketFactory implements IServerSocketFactory{

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		return javax.net.ServerSocketFactory.getDefault().createServerSocket(port);
	}
	
	
}
