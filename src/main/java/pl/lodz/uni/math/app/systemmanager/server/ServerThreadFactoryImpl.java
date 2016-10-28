package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.net.Socket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pl.lodz.uni.math.app.systemmanager.server.services.dao.DatabaseGenericDaoImpl;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class ServerThreadFactoryImpl implements ServerThreadFactory {

	@Override
	public ServerThread newServerThread(Socket socket) throws IOException {
		try {
			return new ServerThread(socket,
					new DatabaseGenericDaoImpl<>(SocketInfo.class, MyEntityManagerFactory.createEntityManager()));
		} catch (IOException e) {
			throw new IOException(e);
		}
	}
}
