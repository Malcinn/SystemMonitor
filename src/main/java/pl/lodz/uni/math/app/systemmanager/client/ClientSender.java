package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSender {

	private Socket socket = null;

	private ObjectOutputStream out = null;

	public ClientSender(Socket socket) throws IOException {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	public void sendData(Object object) throws IOException {
		try {
			out.writeObject(object);
			out.flush();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	public void closeConnections() throws IOException {
		try {
			out.close();
			socket.close();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
