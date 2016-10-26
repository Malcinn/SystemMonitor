package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

public interface ServerListenerFactory {

	public ServerListener createServerListener(int localPortNumber) throws IOException;

	public ServerListener createServerListener(int localPortNumber, ThreadFactory threadFactory,
			ServerThreadFactory serverThreadFactory) throws IOException;
}
