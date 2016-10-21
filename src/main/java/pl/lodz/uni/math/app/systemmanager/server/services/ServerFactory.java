package pl.lodz.uni.math.app.systemmanager.server.services;

import pl.lodz.uni.math.app.systemmanager.server.Server;

public interface ServerFactory {

	public Server createServer(int localPortNumber);
}
