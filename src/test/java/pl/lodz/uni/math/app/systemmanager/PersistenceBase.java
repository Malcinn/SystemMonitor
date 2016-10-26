package pl.lodz.uni.math.app.systemmanager;

import org.junit.After;
import org.junit.Before;

public class PersistenceBase {

	protected HsqldbServer hsqldbServer;
	
	@Before
	public void setUp() {
		hsqldbServer = new HsqldbServer();
		hsqldbServer.start();
	}
	
	@After
	public void tearDown() {
		if (hsqldbServer != null)
			hsqldbServer.shutdown();
	}
}
