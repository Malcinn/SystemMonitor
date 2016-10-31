package pl.lodz.uni.math.app.systemmanager.server.services.web;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class RESTfullWebServiceMain {

	// Base URI the Grizzly HTTP server will listen on
	public static final String BASE_URI = "http://localhost:8080/manager/";

	/**
	 * Starts Grizzly HTTP server exposing JAX-RS resources defined in this
	 * application.
	 * 
	 * @return Grizzly HTTP server.
	 */
	public static HttpServer startServer() {
		// create a resource config that scans for JAX-RS resources and
		// providers
		// in com.example package
		final ResourceConfig rc = new ResourceConfig()
				.packages("pl.lodz.uni.math.app.systemmanager.server.services.web");
		rc.register(pl.lodz.uni.math.app.systemmanager.server.services.web.CORSResponseFilter.class);

		// create and start a new instance of grizzly http server
		// exposing the Jersey application at BASE_URI
		System.out.println(
				String.format("Jersey app started with WADL available at " + "%sapplication.wadl\n", BASE_URI));
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	}

	public static void shutDownServer(HttpServer server) {
		server.shutdown();
	}
}
