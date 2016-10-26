package pl.lodz.uni.math.app.systemmanager;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pl.lodz.uni.math.app.systemmanager.client.ClientSender;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientSenderFactory;
import pl.lodz.uni.math.app.systemmanager.client.services.ClientSenderFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.server.ServerListener;
import pl.lodz.uni.math.app.systemmanager.server.ServerListenerFactory;
import pl.lodz.uni.math.app.systemmanager.server.ServerListenerFactoryImpl;
import pl.lodz.uni.math.app.systemmanager.server.ServerThread;
import pl.lodz.uni.math.app.systemmanager.server.ServerThreadFactory;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.GenericDao;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;
import pl.lodz.uni.math.app.systemmanager.shared.services.ThreadFactoryImpl;

import static org.junit.Assert.*;

public class ClientServerTest {

	private static final Logger LOG = LogManager.getLogger(ClientServerTest.class.getName());

	private static final int SERVER_PORT_NUMBER = 2222;

	private static final String SERVER_HOST_NAME = "127.0.0.1";

	private static ServerListenerFactory serverListenerFactory = new ServerListenerFactoryImpl();

	private static ThreadFactory threadFactory = new ThreadFactoryImpl();

	private static GenericDao<SocketInfo> SocketInfoDaoList = new GenericDao<SocketInfo>() {

		private List<SocketInfo> socketInfoList = new ArrayList<>();

		@Override
		public synchronized void create(SocketInfo entity) {
			socketInfoList.add(entity);
		}

		@Override
		public synchronized SocketInfo update(SocketInfo entity) {
			SocketInfo socketInfo = socketInfoList.get(entity.getId());
			socketInfo.setPort(entity.getPort());
			socketInfo.setHostName(entity.getHostName());
			return socketInfo;
		}

		@Override
		public synchronized void delete(SocketInfo entity) {
			socketInfoList.remove(entity);
		}

		@Override
		public synchronized SocketInfo get(int id) {
			return socketInfoList.get(id);
		}

		@Override
		public synchronized List<SocketInfo> getAll() {
			return socketInfoList;
		}

	};

	private static ServerThreadFactory serverThreadFactory = new ServerThreadFactory() {

		@Override
		public ServerThread newServerThread(Socket socket) throws IOException {
			return new ServerThread(socket, SocketInfoDaoList);
		}
	};

	private ClientSenderFactory clientSenderFactory = new ClientSenderFactoryImpl();

	@BeforeClass
	public static void beforeClass() {
		ServerListener serverListener;
		try {
			serverListener = serverListenerFactory.createServerListener(SERVER_PORT_NUMBER, threadFactory,
					serverThreadFactory);
			threadFactory.newThread(serverListener).start();
		} catch (IOException e) {
			LOG.error("While procesing serverListener object. Exception: " + e);
		}
	}

	@Before
	public void setUp() {
		SocketInfoDaoList.getAll().clear();
	}

	public void clientsSendData(int numberOfClients) {
		try {
			for (int i = 0; i < numberOfClients; i++) {
				ClientSender clientSender = clientSenderFactory.createSender(SERVER_HOST_NAME, SERVER_PORT_NUMBER);
				clientSender.sendData(new SocketInfo("test" + i, 0000));
				clientSender.closeConnections();
			}
		} catch (IOException e) {
			LOG.error("While procesing ClientsSendData() method. Exception: " + e);
		}
	}

	@Test
	public void SingleClientSendsData() {
		try {
			int numberOfClients = 1;
			clientsSendData(numberOfClients);
			while (SocketInfoDaoList.getAll().size() != numberOfClients);
			assertEquals(numberOfClients, SocketInfoDaoList.getAll().size());
		} catch (Exception e) {
			LOG.error("While procesing SingleClientSendsData() method. Exception: " + e);
		}
	}

	@Test
	public void MultipleClientsSendsData() {
		try {
			int numberOfClients = 100;
			clientsSendData(numberOfClients);
			while (SocketInfoDaoList.getAll().size() != numberOfClients);
			assertEquals(numberOfClients, SocketInfoDaoList.getAll().size());
		} catch (Exception e) {
			LOG.error("While procesing MultipleClientsSendsData() method. Exception: " + e);
		}
	}

}
