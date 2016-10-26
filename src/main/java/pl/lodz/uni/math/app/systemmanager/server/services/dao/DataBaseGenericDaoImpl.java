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

	public void create(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
	}

	public T update(T entity) {
		entityManager.getTransaction().begin();
		T tmpEntity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return tmpEntity;
	}

	public void delete(T entity) {
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
	}

	public T get(long id) {
		return entityManager.find(clazz, id);
	}

	public List<T> getAll() {
		return entityManager.createQuery("from " + clazz.getName()).getResultList();
	}

}
