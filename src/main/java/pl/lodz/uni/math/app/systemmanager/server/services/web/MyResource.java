package pl.lodz.uni.math.app.systemmanager.server.services.web;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.javafx.scene.control.behavior.ScrollBarBehavior.ScrollBarKeyBinding;

import pl.lodz.uni.math.app.systemmanager.client.ClientListener;
import pl.lodz.uni.math.app.systemmanager.server.services.ServerService;
import pl.lodz.uni.math.app.systemmanager.server.services.StaticServerServiceFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	private static final Logger LOG = LogManager.getLogger(ClientListener.class.getName());

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to
	 * the client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	@GET
	@Path("systeminfo")
	@Produces(MediaType.APPLICATION_JSON)
	public SystemInfo getSystemInfoFromClient(@QueryParam("host") String hostName, @QueryParam("port") int port)
			throws IOException {
		SystemInfo systemInfo = null;
		try {
			ServerService service = StaticServerServiceFactory.createServerService();
			systemInfo = service.getSystemInfoFromClient(new SocketInfo(hostName, port));
		} catch (IOException e) {
			LOG.error("Error ocured in getSystemInfoFromClient() method. Exception: " + e);
		}
		return systemInfo;
	}

	@GET
	@Path("socketInfoList")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SocketInfo> getListOfSocketInfo() throws IOException {
		ServerService service = StaticServerServiceFactory.createServerService();
		List<SocketInfo> socketInfoList = service.getListOfSocketInfo();
		return socketInfoList;
	}
}
