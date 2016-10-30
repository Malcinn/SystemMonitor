package pl.lodz.uni.math.app.systemmanager.shared.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Sigar;

public class OSFactoryImpl extends ComponentFactory implements OSFactory {

	private static final Logger LOG = LogManager.getLogger(OSFactoryImpl.class.getName());

	public OSFactoryImpl(Sigar sigar) {
		super(sigar);
	}

	@Override
	public OS createOS() {
		OS os = new OS();
		try {
			os.setUpTime(sigar.getUptime().getUptime());
			os.setName(System.getProperty("os.name"));
		} catch (Throwable t) {
			LOG.error("Error ocurred while gatherig information about OS. Throwable: " + t.getMessage());
		}
		return os;
	}

}
