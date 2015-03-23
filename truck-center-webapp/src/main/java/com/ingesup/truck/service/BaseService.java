package com.ingesup.truck.service;


import com.ingesup.truck.repository.BaseRepository;

import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface BaseService<T, I> {

	public List<T> getAll();

	public T add(T entity);

	public Iterable<T> add(Iterable<T> entities);

	public T get(I primaryKey);

	public void removeEntity(T entity);

	public void remove(I primaryKey);

	public T update(T entity);

	public long count(T entity);

	public BaseRepository getRepository();
}
