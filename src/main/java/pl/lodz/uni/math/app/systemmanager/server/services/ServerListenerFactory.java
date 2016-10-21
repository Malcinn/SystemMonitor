package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;

import pl.lodz.uni.math.app.systemmanager.server.ServerListener;

public interface ServerListenerFactory {

	ServerListener createServerListener(int localPortNumber, boolean b) throws IOException;

}
