package pl.lodz.uni.math.app.systemmanager.server.services;

import javax.net.SocketFactory;

public class SocketDataServiceFactoryImpl implements SocketDataServiceFactory{

	@Override
	public SocketDataService createSocketDataService() {
		return new SocketDataServiceImpl(SocketFactory.getDefault());
	}

}
