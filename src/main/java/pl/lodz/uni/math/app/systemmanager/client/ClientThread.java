package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.shared.CommunicationProtocol;

public class ClientThread implements Runnable {

	private static final Logger log = LogManager.getLogger(ClientThread.class.getName());
	
	private Socket socket = null;

	private ObjectOutputStream out = null;

	private ObjectInputStream in = null;

	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Methods readsRequest from server, process request, make a response and
	 * send it backs, after that operations client close the socket and streams.
	 */
	@Override
	public void run() {
		CommunicationProtocol request;
		try {
			request = readRequest();
			Object response = processRequest(request);
			sendResponse(response);
		} catch (ClassNotFoundException | IOException e) {
			log.error("Error ocured while ", e);
		} finally {
			try {
				closeConnections();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void closeConnections() throws IOException{
		try {
			this.out.close();
			this.in.close();
			this.socket.close();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Method reads request from socket
	 * @return CommunicationProtocol or null if object read from socket is of another type.
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	private CommunicationProtocol readRequest() throws IOException, ClassNotFoundException {
		try {
			Object request = in.readObject();
			if ((request instanceof CommunicationProtocol))
				return (CommunicationProtocol) request;
		} catch ( IOException e) {
			throw new IOException(e);
		} catch (ClassNotFoundException e1) {
			throw new ClassNotFoundException(CommunicationProtocol.class.getName(), e1);
		}
		return null;
	}

	/**
	 * 
	 * @param request - CommunicationProtocol type
	 * @return SystemInfo or null If the error occurred while getting information about system.
	 */
	private Object processRequest(CommunicationProtocol request) {
		if (request != null) {
			if (request.equals(CommunicationProtocol.GET)) {
				
			}
		}
		return null;
	}

	
	private void sendResponse(Object response) throws IOException{
		try {
			out.writeObject(response);
			out.flush();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
