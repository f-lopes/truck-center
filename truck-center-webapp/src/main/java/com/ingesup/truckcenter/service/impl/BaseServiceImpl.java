package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.service.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
public abstract class BaseServiceImpl<T, I extends Serializable> implements BaseService<T, I> {

	@Override
	public List<T> getAll() {
		return (List<T>) getRepository().findAll();
	}

	@Override
	public T add(T entity) {
		return (T) getRepository().save(entity);
	}

	@Override
	public Iterable<T> add(Iterable<T> entities) {
		return getRepository().save(entities);
	}

	@Override
	public T get(I primaryKey) {
		return (T) getRepository().findOne(primaryKey);
	}

	@Override
	public void removeEntity(T entity) {
		getRepository().delete(entity);
	}

	@Override
	public void remove(I primaryKey) {
		getRepository().delete(primaryKey);
	}

	@Override
	public T update(T entity) {
		return (T) getRepository().save(entity);
	}

	@Override
	public long count(T entity) {
		return getRepository().count();
	}
}
