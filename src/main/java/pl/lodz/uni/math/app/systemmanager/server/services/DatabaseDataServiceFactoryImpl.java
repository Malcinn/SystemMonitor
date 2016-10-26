package pl.lodz.uni.math.app.systemmanager.server.services;


public class DatabaseDataServiceFactoryImpl implements DatabaseDataServiceFactory{

	@Override
	public DatabaseDataService createDatabaseDataService() {
		return new DatabaseDataServiceImpl();
	}

}
