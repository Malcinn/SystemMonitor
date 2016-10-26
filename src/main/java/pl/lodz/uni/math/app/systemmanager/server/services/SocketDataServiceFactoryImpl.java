package pl.lodz.uni.math.app.systemmanager.server.services;

public class SocketDataServiceFactoryImpl implements SocketDataServiceFactory{

	@Override
	public SocketDataService createSocketDataService() {
		return new SocketDataServiceImpl();
	}

}
