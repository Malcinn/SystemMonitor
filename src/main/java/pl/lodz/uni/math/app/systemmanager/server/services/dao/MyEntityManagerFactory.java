package pl.lodz.uni.math.app.systemmanager.server.services.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.type.descriptor.java.SerializableTypeDescriptor.SerializableMutabilityPlan;

import pl.lodz.uni.math.app.systemmanager.server.ServerListener;

public class MyEntityManagerFactory {

	private static final Logger LOG = LogManager.getLogger(MyEntityManagerFactory.class.getName());

	private static String persistenceUnitName = "PU";

	private static EntityManagerFactory factory = null;

	public static EntityManager createEntityManager() {
		if (factory == null) {
			try {
				factory = Persistence.createEntityManagerFactory(persistenceUnitName);
			} catch (Exception e) {
				LOG.error("No Database connection. Check that database is working. Exception: " + e);
			}
		}
		if (factory != null)
			return factory.createEntityManager();
		return null;
	}

	public static void closeEntityManagerFactory() {
		if (factory != null) {
			factory.close();
		}
	}
}
