package pl.lodz.uni.math.app.systemmanager.shared;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Sigar;

import pl.lodz.uni.math.app.systemmanager.shared.model.CPUFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.CPUFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.FileSystemFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.FileSystemFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.MemoryFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.MemoryFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.NetworkInterfaceFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.NetworkInterfaceFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.OSFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.OSFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.UserFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.UserFactoryImpl;

public class SystemInfoFactoryImpl implements SystemInfoFactory {

	private static final Logger LOG = LogManager.getLogger(SystemInfoFactoryImpl.class.getName());

	private CPUFactory cPUFactory;

	private FileSystemFactory fileSystemFactory;

	private MemoryFactory memoryFactory;

	private NetworkInterfaceFactory networkInterfaceFactory;

	private OSFactory oSFactory;

	private UserFactory userFactory;

	@Override
	public SystemInfo createSystemInfo() {
		Sigar sigar = null;
		SystemInfo systemInfo = new SystemInfo();
		try {
			sigar = new Sigar();
			setUpFactories(sigar);
			systemInfo.setCpu(cPUFactory.createCPU());
			systemInfo.setFileSystems(fileSystemFactory.createFileSystem());
			systemInfo.setMemory(memoryFactory.createMemory());
			systemInfo.setNetworkInterfaces(networkInterfaceFactory.createNetworkInterface());
			systemInfo.setOs(oSFactory.createOS());
			systemInfo.setUsers(userFactory.createUsers());
		} catch (Throwable t) {
			LOG.error("Error ocurred while creating sigar object.");
		} finally {
			try {
				sigar.close();
			} catch (Throwable t2) {
				LOG.error("Error ocurred while closing sigar object.");
			}
		}
		return systemInfo;
	}

	private void setUpFactories(Sigar sigar) {
		cPUFactory = new CPUFactoryImpl(sigar);
		fileSystemFactory = new FileSystemFactoryImpl(sigar);
		memoryFactory = new MemoryFactoryImpl(sigar);
		networkInterfaceFactory = new NetworkInterfaceFactoryImpl(sigar);
		oSFactory = new OSFactoryImpl(sigar);
		userFactory = new UserFactoryImpl(sigar);
	}
}
