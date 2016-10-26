package pl.lodz.uni.math.app.systemmanager.server.services.dao;

import java.util.List;

public interface GenericDao<T> {

	public void create(T entity);

	public T update(T entity);

	public void delete(T entity);

	public T get(long id);

	public List<T> getAll();
}
