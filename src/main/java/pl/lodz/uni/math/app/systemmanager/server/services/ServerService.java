package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.util.List;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

public interface ServerService {

	public SystemInfo getSystemInfoFromClient(SocketInfo socketInfo) throws IOException;

	public List<SocketInfo> getListOfSocketInfo();
}
