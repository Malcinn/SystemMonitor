package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import pl.lodz.uni.math.app.systemmanager.server.ServerListener;

public interface ServerListenerFactory {

	public ServerListener createServerListener(int localPortNumber) throws IOException;

	public ServerListener createServerListener(int localPortNumber, ThreadFactory threadFactory,
			ServerThreadFactory serverThreadFactory) throws IOException;
}
