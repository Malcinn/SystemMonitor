package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.net.Socket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.lodz.uni.math.app.systemmanager.server.ServerThread;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.DataBaseGenericDaoImpl;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ServerThreadFactoryImpl implements ServerThreadFactory {

	@Override
	public ServerThread newServerThread(Socket socket) throws IOException {
		try {
			return new ServerThread(socket,
					new DataBaseGenericDaoImpl<>(SocketInfo.class, MyEntityManagerFactory.createEntityManager()));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
