package pl.lodz.uni.math.app.systemmanager.shared.services;

import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

public class SystemInfoFactoryImpl implements SystemInfoFactory{

	public SystemInfo createSystemInfo() {
		return new SystemInfo();
	}
}
