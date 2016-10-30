package pl.lodz.uni.math.app.systemmanager.server.services.web;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadFactory;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.scene.control.behavior.ScrollBarBehavior.ScrollBarKeyBinding;

import pl.lodz.uni.math.app.systemmanager.HsqldbServer;
import pl.lodz.uni.math.app.systemmanager.client.ClientListener;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientListenerFactory;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientListenerFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.server.ServerListener;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.DatabaseGenericDaoImpl;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

public class MyResourceTest {

	private static final Logger LOG = LogManager.getLogger(MyResourceTest.class.getName());

	private HttpServer server;
	
	private WebTarget target;

	private HsqldbServer hsqldbServer;

	@Before
	public void setUp() throws Exception {
		// start the server
		server = RESTfullWebServiceMain.startServer();
		// create the client
		Client c = ClientBuilder.newClient();

		// uncomment the following line if you want to enable
		// support for JSON in the client (you also have to uncomment
		// dependency on jersey-media-json module in pom.xml and
		// Main.startServer())
		// --
		// c.configuration().enable(new
		// org.glassfish.jersey.media.json.JsonJaxbFeature());

		target = c.target(RESTfullWebServiceMain.BASE_URI);
		HsqldbServer hsqldbServer = new HsqldbServer();
		hsqldbServer.start();
	}

	@After
	public void tearDown() throws Exception {
		server.shutdown();
		if (hsqldbServer != null)
			hsqldbServer.stop();
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 */
	@Test
	public void testGetIt() {
		String responseMsg = target.path("myresource").request().get(String.class);
		assertEquals("Got it!", responseMsg);
	}

	@Test
	public void getSystemInfoFromClientTest() {

		String host = "127.0.0.1";
		int port = 2223;
		ClientListenerFactory clientListenerFactory = new ClientListenerFactoryImpl();
		ThreadFactory threadFactory = new ThreadFactoryImpl();
		SystemInfo responseSystemInfo = null;
		try {
			ClientListener clientListener = clientListenerFactory.createClientListener(port);
			threadFactory.newThread(clientListener).start();

			responseSystemInfo = target.path("myresource/systeminfo").queryParam("host", host).queryParam("port", port)
					.request().get(SystemInfo.class);
		} catch (IOException e) {
			LOG.error("Error ocurred while getting resource. Exception: " + e);
		}

		assertTrue(responseSystemInfo instanceof SystemInfo);
	}

	@Test
	public void getSystemInfoFromNotExistingClientTest() {
		String host = "127.0.0.1";
		int port = 2224;
		SystemInfo responseSystemInfo = null;
		responseSystemInfo = target.path("myresource/systeminfo").queryParam("host", host).queryParam("port", port)
				.request().get(SystemInfo.class);

		assertTrue(responseSystemInfo == null);
	}

	@Test
	public void getListOfSocketInfoTest() {
		HsqldbServer hsqldbServer = new HsqldbServer();
		hsqldbServer.start();

		DatabaseGenericDaoImpl<SocketInfo> databaseGenericDaoImpl = new DatabaseGenericDaoImpl<>(SocketInfo.class,
				MyEntityManagerFactory.createEntityManager());
		databaseGenericDaoImpl.create(new SocketInfo("test", 1111));
		databaseGenericDaoImpl.create(new SocketInfo("test", 1111));
		databaseGenericDaoImpl.create(new SocketInfo("test", 1111));

		SocketInfo[] SocketInfoArray = target.path("myresource/socketInfoList").request().get(SocketInfo[].class);

		if (hsqldbServer != null)
			hsqldbServer.stop();
		assertTrue(SocketInfoArray instanceof SocketInfo[]);
	}

	@Test
	public void getEmptyListOfSocketInfoTest() {

		SocketInfo[] SocketInfoArray = target.path("myresource/socketInfoList").request().get(SocketInfo[].class);

		assertTrue(SocketInfoArray instanceof SocketInfo[]);
	}

}
