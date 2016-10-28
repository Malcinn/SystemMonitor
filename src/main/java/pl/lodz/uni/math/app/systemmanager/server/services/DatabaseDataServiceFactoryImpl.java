package pl.lodz.uni.math.app.systemmanager.server.services;

import pl.lodz.uni.math.app.systemmanager.server.services.dao.DatabaseGenericDaoImpl;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public class DatabaseDataServiceFactoryImpl implements DatabaseDataServiceFactory {

	@Override
	public DatabaseDataService createDatabaseDataService() {
		return new DatabaseDataServiceImpl(
				new DatabaseGenericDaoImpl<>(SocketInfo.class, MyEntityManagerFactory.createEntityManager()));
	}

}
