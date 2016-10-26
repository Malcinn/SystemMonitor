package pl.lodz.uni.math.app.systemmanager.server.services;

import java.util.List;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

public interface ServerService {

	public SystemInfo getSystemInfoFromClient(SocketInfo socketInfo);

	public List<SocketInfo> getListOfSocketInfo();
}
