package pl.lodz.uni.math.app.systemmanager.server.services;

import java.io.IOException;
import java.util.concurrent.ThreadFactory;

import javax.net.SocketFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.lodz.uni.math.app.systemmanager.client.ClientListener;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientListenerFactory;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientListenerFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.SystemInfo;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

import static org.junit.Assert.*;

public class SocketDataServiceImplTest {

	private static final Logger LOG = LogManager.getLogger(SocketDataServiceImpl.class.getName());

	private static final String CLIENT_HOST_NAME = "127.0.0.1";

	private static final int CLIENT_PORT_NUMBER = 2223;

	private SocketDataServiceFactory socketDataServiceFactory = new SocketDataServiceFactory() {
		@Override
		public SocketDataService createSocketDataService() {
			return new SocketDataServiceImpl(SocketFactory.getDefault());
		}
	};

	private SocketDataService socketDataService = new SocketDataServiceImpl(SocketFactory.getDefault());

	private static ThreadFactory threadFactory = new ThreadFactoryImpl();

	private static ClientListenerFactory clientListenerFactory = new ClientListenerFactoryImpl();

	private static ClientListener clientListener;

	@BeforeClass
	public static void setUp() {
		try {
			clientListener = clientListenerFactory.createClientListener(CLIENT_PORT_NUMBER);
			threadFactory.newThread(clientListener).start();
		} catch (IOException e) {
			LOG.error("Error ocurred while setting up test environment. Exception: " + e);
		}

	}

	@Test
	public void singleSocketDataServiceGetDataFromSingleClient() {
		SystemInfo systemInfo = null;
		try {
			socketDataService = socketDataServiceFactory.createSocketDataService();
			systemInfo = socketDataService
					.getSystemInfoFromClient(new SocketInfo(CLIENT_HOST_NAME, CLIENT_PORT_NUMBER));
		} catch (IOException e) {
			LOG.error("Error ocurred while obtaining information form client. Exception: " + e);
		}
		assertTrue(systemInfo instanceof SystemInfo);
	}

	@Test
	public void multipleSocketDataServiceGetDataFromSingleClient() {
		int size = 10;
		SystemInfo systemInfo = null;
		try {
			for (int i = 0; i < size; i++) {
				socketDataService = socketDataServiceFactory.createSocketDataService();
				systemInfo = socketDataService
						.getSystemInfoFromClient(new SocketInfo(CLIENT_HOST_NAME, CLIENT_PORT_NUMBER));
				assertTrue(systemInfo instanceof SystemInfo);
			}
		} catch (IOException e) {
			LOG.error("Error ocurred while obtaining multiple information form client. Exception: " + e);
		}
	}

	@Test
	public void singleSocketDataServiceGetDataFromMultipleClients() {
		int numberOfClients = 2;
		int clientPortNumber = CLIENT_PORT_NUMBER;
		try {
			socketDataService = socketDataServiceFactory.createSocketDataService();
			for (int i = 0; i < numberOfClients; i++) {
				clientListener = clientListenerFactory.createClientListener(++clientPortNumber);
				threadFactory.newThread(clientListener).start();
			}
			for (int i = 0; i < numberOfClients; i++) {
				
				SystemInfo systemInfo = socketDataService
						.getSystemInfoFromClient(new SocketInfo(CLIENT_HOST_NAME, clientPortNumber--));
				assertTrue(systemInfo instanceof SystemInfo);
			}
		} catch (IOException e) {
			LOG.error("Error ocurred while obtaining information form multiple client. Exception: " + e);
		}
	}

	@Test
	public void multipleSocketDataServiceGetDataFromMultipleClients() {
		int numberOfClients = 2;
		int clientPortNumber = 3333;
		try {
			
			for (int i = 0; i < numberOfClients; i++) {
				clientListener = clientListenerFactory.createClientListener(++clientPortNumber);
				threadFactory.newThread(clientListener).start();
			}
			for (int i = 0; i < numberOfClients; i++) {
				socketDataService = socketDataServiceFactory.createSocketDataService();
				SystemInfo systemInfo = socketDataService
						.getSystemInfoFromClient(new SocketInfo(CLIENT_HOST_NAME, clientPortNumber--));
				assertTrue(systemInfo instanceof SystemInfo);
			}
		} catch (IOException e) {
			LOG.error("Error ocurred while obtaining information form multiple client. Exception: " + e);
		}
	}
}
