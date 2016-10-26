package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.net.Socket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.lodz.uni.math.app.systemmanager.server.ServerThread;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.DataBaseGenericDaoImpl;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ServerThreadFactoryImpl implements ServerThreadFactory {

	private static String persistenceUnitName = "PU";
	
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(persistenceUnitName);
	
	private static EntityManager entityManager = factory.createEntityManager();
	
	@Override
	public ServerThread newServerThread(Socket socket) throws IOException {
		try {
			return new ServerThread(socket, new DataBaseGenericDaoImpl<>(SocketInfo.class, entityManager));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

}
