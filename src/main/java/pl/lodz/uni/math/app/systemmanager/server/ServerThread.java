package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.Socket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.server.services.dao.GenericDao;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ServerThread implements Runnable {

	private static final Logger log = LogManager.getLogger(ServerThread.class.getName());

	private Socket socekt = null;

	private ObjectInputStream in = null;

	private GenericDao<SocketInfo> socketInfoDao;
	
	public ServerThread(Socket socket, GenericDao<SocketInfo> socketInfoDao) throws IOException {
		try {
			this.socekt = socket;
			this.in = new ObjectInputStream(socket.getInputStream());
			this.socketInfoDao = socketInfoDao;
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void run() {
		try {
			Object data = readData();
			processData(data);
		} catch (ClassNotFoundException | IOException e) {
			log.error("Error ocurred while performing run() method of ServerThread object. Exception: ", e);
		} finally {
			try {
				closeConnections();
			} catch (IOException e1) {
				log.error(
						"Error ocurred while perfornimg closeConnections() method of ServerThread object. Exception: ",
						e1);
			}
		}
	}

	private Object readData() throws IOException, ClassNotFoundException {
		try {
			Object data = in.readObject();
			return data;
		} catch (IOException e) {
			throw new IOException(e);
		} catch (ClassNotFoundException e1) {
			throw new ClassNotFoundException(ServerThread.class.getName(), e1);
		}
	}

	private void processData(Object data) {
		if (data != null) {
			if (data instanceof SocketInfo) {
				SocketInfo socketInfo = (SocketInfo) data;
				socketInfoDao.create(socketInfo);
			}
		}
	}

	private void closeConnections() throws IOException {
		try {
			in.close();
			socekt.close();
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
