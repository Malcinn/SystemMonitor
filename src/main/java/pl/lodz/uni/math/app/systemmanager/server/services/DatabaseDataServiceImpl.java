package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.net.SocketFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.server.services.dao.GenericDao;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class DatabaseDataServiceImpl implements DatabaseDataService {

	private static final Logger LOG = LogManager.getLogger(DatabaseDataServiceImpl.class.getName());

	private GenericDao<SocketInfo> socketInfoDAO;

	public DatabaseDataServiceImpl(GenericDao<SocketInfo> socketInfoDAO) {
		this.socketInfoDAO = socketInfoDAO;
	}

	@Override
	public List<SocketInfo> getListOfSocketInfo() {
		clearNotRespondingClients(socketInfoDAO.getAll());
		return socketInfoDAO.getAll();
	}

	private List<SocketInfo> clearNotRespondingClients(List<SocketInfo> socketInfoList) {
		for (SocketInfo socketInfo : socketInfoList) {
			Socket socket = null;
			try {
				socket = SocketFactory.getDefault().createSocket(socketInfo.getHostName(), socketInfo.getPort());
				socket.close();
			} catch (IOException e) {
				deleteNotRespondingClient(socketInfo);
				LOG.error("Could not connect to Client that work on host: " + socketInfo.getHostName() + ", and port: "
						+ socketInfo.getPort() + " Exception: " + e);
			}
		}
		return socketInfoList;
	}

	private void deleteNotRespondingClient(SocketInfo socketInfo) {
		socketInfoDAO.delete(socketInfo);
	}

}
