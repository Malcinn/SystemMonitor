package pl.lodz.uni.math.app.systemmanager.shared;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pl.lodz.uni.math.app.systemmanager.shared.model.CPU;
import pl.lodz.uni.math.app.systemmanager.shared.model.CPUFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.CPUFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.FileSystem;
import pl.lodz.uni.math.app.systemmanager.shared.model.FileSystemFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.FileSystemFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.Memory;
import pl.lodz.uni.math.app.systemmanager.shared.model.MemoryFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.MemoryFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.NetworkInterface;
import pl.lodz.uni.math.app.systemmanager.shared.model.NetworkInterfaceFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.NetworkInterfaceFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.OS;
import pl.lodz.uni.math.app.systemmanager.shared.model.OSFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.OSFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.model.User;
import pl.lodz.uni.math.app.systemmanager.shared.model.UserFactory;
import pl.lodz.uni.math.app.systemmanager.shared.model.UserFactoryImpl;

public class ComponentsTest {

	private static final Logger LOG = LogManager.getLogger(ComponentsTest.class.getName());

	private Sigar sigar;

	@Test
	public void SystemInfoTest() {
		SystemInfoFactory factory = new SystemInfoFactoryImpl();
		SystemInfo systemInfo = factory.createSystemInfo();
		LOG.info(systemInfo.toString());
	}
	
	@Before
	public void setUp() {
		try {
			sigar = new Sigar();
		} catch (Throwable t) {
			LOG.error("Error ocurred while creating sigar object.");
		}
	}

	@After
	public void tearDown() {
		try {
			sigar.close();
		} catch (Throwable t2) {
			LOG.error("Error ocurred while closing sigar object.");
		}
	}

	@Test
	public void CPUTest() {
		CPUFactory factory = new CPUFactoryImpl(sigar);
		CPU cpu = factory.createCPU();
		LOG.info(cpu.toString());
	}

	@Test
	public void FileSystemTest() {
		FileSystemFactory factory = new FileSystemFactoryImpl(sigar);
		List<FileSystem> fileSystems = factory.createFileSystem();
		LOG.info(fileSystems.toString());
	}
	
	@Test
	public void MemoryTest() {
		MemoryFactory factory = new MemoryFactoryImpl(sigar);
		Memory memory = factory.createMemory();
		LOG.info(memory.toString());
	}
	
	@Test
	public void NetworkInterfaceTest() {
		NetworkInterfaceFactory factory = new NetworkInterfaceFactoryImpl(sigar);
		List<NetworkInterface> networkInterfaces = factory.createNetworkInterface();
		LOG.info(networkInterfaces.toString());
	}
	
	@Test
	public void OSTest() {
		OSFactory factory = new OSFactoryImpl(sigar);
		OS os = factory.createOS();
		LOG.info(os.toString());
	}
	
	@Test
	public void UserTest() {
		UserFactory factory = new UserFactoryImpl(sigar);
		List<User> users = factory.createUsers();
		LOG.info(users.toString());
	}
	
}
