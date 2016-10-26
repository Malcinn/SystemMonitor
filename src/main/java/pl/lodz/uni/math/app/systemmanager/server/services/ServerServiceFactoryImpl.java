package pl.lodz.uni.math.app.systemmanager.server.services;

public class ServerServiceFactoryImpl implements ServerServiceFactory {

	@Override
	public ServerService createServerService() {
		return new ServerServiceImpl(new SocketDataServiceFactoryImpl().createSocketDataService(),
				new DatabaseDataServiceFactoryImpl().createDatabaseDataService());
	}
}
