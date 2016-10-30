package pl.lodz.uni.math.app.systemmanager.server.services;

public class StaticServerServiceFactory {

	private static final ServerServiceFactory FACTORY = new ServerServiceFactoryImpl();
	
	public  static ServerService createServerService() {
		return FACTORY.createServerService();
	}
}
