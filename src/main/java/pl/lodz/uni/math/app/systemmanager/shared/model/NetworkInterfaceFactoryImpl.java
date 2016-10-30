package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;

public class NetworkInterfaceFactoryImpl extends ComponentFactory implements NetworkInterfaceFactory {

	private static final Logger LOG = LogManager.getLogger(NetworkInterfaceFactoryImpl.class.getName());

	public NetworkInterfaceFactoryImpl(Sigar sigar) {
		super(sigar);
	}

	@Override
	public List<NetworkInterface> createNetworkInterface() {
		List<NetworkInterface> networkInterfaces = new ArrayList<>();
		try {
			String[] netInterfaceList = sigar.getNetInterfaceList();
			for (String netInterface : netInterfaceList) {
				NetworkInterface networkInterface = new NetworkInterface();
				try {
					NetInterfaceConfig netInterfaceConfig = sigar.getNetInterfaceConfig(netInterface);

					networkInterface.setName(netInterfaceConfig.getName());
					networkInterface.setAddress(netInterfaceConfig.getAddress());
					networkInterface.setHardwareAddress(netInterfaceConfig.getHwaddr());
					networkInterface.setType(netInterfaceConfig.getType());
					NetInterfaceStat netInterfaceStat = sigar.getNetInterfaceStat(netInterface);
					networkInterface.setRxBytes(netInterfaceStat.getRxBytes());
					networkInterface.setTxBytes(netInterfaceStat.getTxBytes());
				} catch (Throwable t) {
					LOG.error("Error ocurred while gatherig information about Network Interface. Throwable: "
							+ t.getMessage());
				}
				networkInterfaces.add(networkInterface);
			}
		} catch (Throwable t) {
			LOG.error(
					"Error ocurred while gatherig information about Network Interfaces. Throwable: " + t.getMessage());
		}
		return networkInterfaces;
	}

}
