package pl.lodz.uni.math.app.systemmanager.server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;

import pl.lodz.uni.math.app.systemmanager.server.services.web.RESTfullWebServiceMain;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class.getName());

	public static void main(String[] args) {
		try {
			int localPortNumber = 0;
			if (args.length == 1) {
				
				/*
				 * Before you run this Class, please run the DATABASE !!
				 */
				localPortNumber = Integer.parseInt(args[0]);
				ServerFactory serverFactory = new ServerFactoryImpl();
				Server server = serverFactory.createServer(localPortNumber);
				server.run();
				HttpServer httpServer = RESTfullWebServiceMain.startServer();
				System.out.println("press ctrl + c - to stop server");
				System.in.read();
/*				RESTfullWebServiceMain.shutDownServer(httpServer);
				server.shutDown();*/
			} else {
				log.info("program expects one argument, which is local port number for server.");
			}
		} catch (NumberFormatException e) {
			log.error("Passed local port number is invalid. Exception: ", e);
		} catch (IOException e) {
			log.error("Error ocurred while performing run() method of the Server object. Exception: ", e);
		}
	}
	
}
