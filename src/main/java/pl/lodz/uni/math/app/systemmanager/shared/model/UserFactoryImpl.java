package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Who;

public class UserFactoryImpl extends ComponentFactory implements UserFactory {

	private static final Logger LOG = LogManager.getLogger(UserFactoryImpl.class.getName());

	public UserFactoryImpl(Sigar sigar) {
		super(sigar);
	}

	@Override
	public List<User> createUsers() {
		List<User> users = new ArrayList<>();
		try {
			Who[] whos = sigar.getWhoList();
			for (Who who : whos) {
				User user = new User();
				try {
					user.setName(who.getUser());
					user.setDevice(who.getDevice());
					users.add(user);
					user.setUpTime(who.getTime());
				} catch (Throwable t) {
					LOG.error("Error ocurred while gatherig information about User. Throwable: " + t.getMessage());
				}
			}
		} catch (Throwable t) {
			LOG.error("Error ocurred while gatherig information about Users. Throwable: " + t.getMessage());
		}
		return users;
	}

}
