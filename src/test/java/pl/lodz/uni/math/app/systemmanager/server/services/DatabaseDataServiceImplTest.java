package pl.lodz.uni.math.app.systemmanager.server.services;

import java.util.List;

import org.junit.Test;

import pl.lodz.uni.math.app.systemmanager.PersistenceBase;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.DatabaseGenericDaoImpl;
import pl.lodz.uni.math.app.systemmanager.server.services.dao.MyEntityManagerFactory;
import pl.lodz.uni.math.app.systemmanager.shared.SocketInfo;

import static org.junit.Assert.*;

public class DatabaseDataServiceImplTest extends PersistenceBase {

	private DatabaseDataServiceImpl databaseDataServiceImpl;

	private DatabaseGenericDaoImpl<SocketInfo> databaseGenericDaoImpl;

	@Override
	public void setUp() {
		super.setUp();
		databaseGenericDaoImpl = new DatabaseGenericDaoImpl<>(SocketInfo.class,
				MyEntityManagerFactory.createEntityManager());
		databaseDataServiceImpl = new DatabaseDataServiceImpl(databaseGenericDaoImpl);
	}

	@Override
	public void tearDown() {
		super.tearDown();
	}

	@Test
	public void getListOfSocketInfoTest() {
		databaseGenericDaoImpl.create(new SocketInfo("test", 1111));
		databaseGenericDaoImpl.create(new SocketInfo("test", 1111));
		databaseGenericDaoImpl.create(new SocketInfo("test", 1111));
		
		List<SocketInfo> socketInfoList = databaseDataServiceImpl.getListOfSocketInfo();
		System.out.println(socketInfoList.toString());
		assertEquals(3, socketInfoList.size());
		MyEntityManagerFactory.closeEntityManagerFactory();
	}
}
