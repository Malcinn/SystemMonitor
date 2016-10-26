package pl.lodz.uni.math.app.systemmanager;

import org.junit.Test;

import pl.lodz.uni.math.app.systemmanager.client.ClientSender;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientSenderFactory;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientSenderFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.server.Server;
import pl.lodz.uni.math.app.systemmanager.server.services.ServerFactory;
import pl.lodz.uni.math.app.systemmanager.server.services.ServerFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

import static pl.lodz.uni.math.app.systemmanager.StaticValues.*;

import java.io.IOException;

public class ClientServerWithDependenciesTest extends PersistenceBase{

	private ServerFactory serverFactory = new ServerFactoryImpl();

	private ClientSenderFactory clientSenderFactory = new ClientSenderFactoryImpl();
	
	@Test
	public void ServerListenAndSingleClientSendDataToIt() {
		try {
			Server server = serverFactory.createServer(DEFAULT_SERVER_PORT_NUMBER);
			server.run();
			ClientSender clientSender = clientSenderFactory.createSender(DEFAULT_SERVER_ADDRESS,
					DEFAULT_SERVER_PORT_NUMBER);
			clientSender.sendData(new SocketInfo("test", 1000));
			clientSender.closeConnections();
			MyEntityManagerFactory.closeEntityManagerFactory();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Test
	public void ServerListenAndMultipleClientsSendDataToIt() {
		try {
			Server server = serverFactory.createServer(DEFAULT_SERVER_PORT_NUMBER+1);
			server.run();
			for (int i = 0; i < 10; i++) {
				ClientSender clientSender = clientSenderFactory.createSender(DEFAULT_SERVER_ADDRESS,
						DEFAULT_SERVER_PORT_NUMBER+1);
				clientSender.sendData(new SocketInfo("test" + i, 1000));
				clientSender.closeConnections();
			}
			MyEntityManagerFactory.closeEntityManagerFactory();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
