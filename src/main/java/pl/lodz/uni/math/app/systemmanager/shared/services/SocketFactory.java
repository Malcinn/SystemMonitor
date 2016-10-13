package pl.lodz.uni.math.app.systemmanager.shared.services;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketFactory implements ISocketFactory{

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return javax.net.SocketFactory.getDefault().createSocket(host, port);
	}

}
