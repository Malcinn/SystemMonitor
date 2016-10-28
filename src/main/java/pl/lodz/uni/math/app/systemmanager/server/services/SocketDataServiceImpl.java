package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.net.SocketFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.shared.CommunicationProtocol;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

public class SocketDataServiceImpl implements SocketDataService {

	private static final Logger LOG = LogManager.getLogger(SocketDataServiceImpl.class.getName());

	private SocketFactory socketFactory;

	public SocketDataServiceImpl(SocketFactory socketFactory) {
		this.socketFactory = socketFactory;
	}

	@Override
	public SystemInfo getSystemInfoFromClient(SocketInfo socketInfo) throws IOException {
		SystemInfo systemInfo = null;
		try {
			Socket socket = socketFactory.createSocket(socketInfo.getHostName(), socketInfo.getPort());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			sendRequest(out, CommunicationProtocol.GET);
			systemInfo = readResponse(in);
		} catch (IOException e) {
			LOG.error("Error in getSystemInfoFromClient() method. Exception: " + e);
			throw new IOException(e);
		}
		return systemInfo;
	}

	private void sendRequest(ObjectOutputStream out, CommunicationProtocol get) throws IOException {
		try {
			out.writeObject(get);
		} catch (IOException e) {
			LOG.error("Error ocurred while writing data to socket. Exception: " + e);
			throw new IOException(e);
		}
	}

	private SystemInfo readResponse(ObjectInputStream in) throws IOException {
		try {
			Object object = in.readObject();
			if (object instanceof SystemInfo)
				return (SystemInfo) object;
		} catch (ClassNotFoundException | IOException e) {
			LOG.error("Error ocurred while reading data from socket. Exception: " + e);
			throw new IOException(e);
		}
		return null;
	}

}
