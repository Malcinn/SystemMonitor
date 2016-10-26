package pl.lodz.uni.math.app.systemmanager.server.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyEntityManagerFactory {
	
	private static String persistenceUnitName = "PU";
	
	private static EntityManagerFactory factory = null;

	public static EntityManager createEntityManager() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		}
		return factory.createEntityManager();
	}
	
	public static void closeEntityManagerFactory() {
		if (factory != null) {
			factory.close();
		}
	}
}
