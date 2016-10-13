package pl.lodz.uni.math.app.systemmanager.shared.services;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public interface ISocketFactory {

	public Socket createSocket(String host, int port) throws IOException, UnknownHostException;
}
