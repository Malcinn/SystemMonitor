package pl.lodz.uni.math.app.systemmanager.server.services;

import java.util.List;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

public interface DatabaseDataService {

	public List<SocketInfo> getListOfSocketInfo();

}
