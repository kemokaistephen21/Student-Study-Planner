package com.studentstudyplanner.data;

import java.util.List;

public interface DataAccessInterface<T> {
	public List<T> findAll();
	public T findById(Long id);
	public void create(T t);
	public void update(T t);
	public void delete(T t);
}
