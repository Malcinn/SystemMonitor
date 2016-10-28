package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.util.List;

import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

public class ServerServiceImpl implements ServerService {

	private SocketDataService socketDataService;

	private DatabaseDataService databaseDataService;

	public ServerServiceImpl(SocketDataService socketDataService, DatabaseDataService databaseDataService) {
		this.socketDataService = socketDataService;
		this.databaseDataService = databaseDataService;
	}

	@Override
	public SystemInfo getSystemInfoFromClient(SocketInfo socketInfo) throws IOException {
		try {
			return socketDataService.getSystemInfoFromClient(socketInfo);
		} catch (IOException e) {
			throw new IOException(e);
		}
	}

	@Override
	public List<SocketInfo> getListOfSocketInfo() {
		return databaseDataService.getListOfSocketInfo();
	}

}
