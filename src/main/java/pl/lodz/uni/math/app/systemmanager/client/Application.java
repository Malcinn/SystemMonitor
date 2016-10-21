package pl.lodz.uni.math.app.systemmanager.client;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pl.lodz.uni.math.app.systemmanager.client.services.ClientFactory;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientFactoryImpl;

public class Application {

	private static final Logger log = LogManager.getLogger(Application.class.getName());

	public static void main(String[] args) {
		/*
		 * Program takes 3 arguments : 1) port number on which client will be
		 * listening 2) server IP address 3) port number on which server
		 * application running If the number of arguments is different than 3,
		 * then IP address and port number are set to default values
		 */
		if (args.length == 3) {
			int localPortNumber = Integer.parseInt(args[0]);
			String serverAddress = args[1];
			int serverPortNumber = Integer.parseInt(args[2]);

			Client client;
			try {
				ClientFactory clientFactory = new ClientFactoryImpl();
				client = clientFactory.createClient(localPortNumber, serverAddress, serverPortNumber);
				client.run();
			} catch (IOException | ClassNotFoundException e) {
				log.error("Error ocurred while performing Client run() method. Exception: ", e);
			} catch (NumberFormatException e) {
				log.error("Error ocurred while parsing passed parameters. Exception: ", e);
			}
		}

	}
}
