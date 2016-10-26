package pl.lodz.uni.math.app.systemmanager.server.services.dao;

import java.util.List;

import javax.persistence.EntityManager;

public class DataBaseGenericDaoImpl<T> implements GenericDao<T>{

	private Class<T> clazz;
	
	private EntityManager entityManager;
	
	public DataBaseGenericDaoImpl(Class<T> clazz, EntityManager entityManager) {
		this.clazz = clazz;
		this.entityManager = entityManager;
	}

	public synchronized void create(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public synchronized T update(T entity) {
		entityManager.getTransaction().begin();
		T tmpEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return tmpEntity;
	}

	public synchronized void delete(T entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	public synchronized T get(int id) {
		return entityManager.find(clazz, id);
	}

	public synchronized List<T> getAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

}
