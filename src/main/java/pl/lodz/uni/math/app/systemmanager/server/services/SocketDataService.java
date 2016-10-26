package pl.lodz.uni.math.app.systemmanager.server.services;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

public interface SocketDataService {

	public SystemInfo getSystemInfoFromClient(SocketInfo socketInfo);

}
