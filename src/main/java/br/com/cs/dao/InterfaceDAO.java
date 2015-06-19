package br.com.cs.dao;

import java.io.Serializable;
import java.util.List;

public interface InterfaceDAO<T> {

	void save(T entity);

	void update(T entity);

	void remove(T entity);

	void merge(T entity);

	T getEntity(Serializable id);

	List<T> getEntities();
}
