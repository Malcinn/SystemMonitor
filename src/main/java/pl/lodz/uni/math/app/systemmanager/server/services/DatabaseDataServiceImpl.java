package pl.lodz.uni.math.app.systemmanager.server.services;

import java.util.List;

import pl.lodz.uni.math.app.systemmanager.server.services.dao.GenericDao;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class DatabaseDataServiceImpl implements DatabaseDataService {
	
	private GenericDao<SocketInfo> socketInfoDAO;
	
	public DatabaseDataServiceImpl(GenericDao<SocketInfo> socketInfoDAO) {
		this.socketInfoDAO =  socketInfoDAO;
	}

	@Override
	public List<SocketInfo> getListOfSocketInfo() {
		return socketInfoDAO.getAll();
	}

}
