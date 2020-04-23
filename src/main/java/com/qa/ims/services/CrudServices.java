package com.qa.ims.services;

import java.util.List;

public interface CrudServices<T> {

	public List<T> readAll();

	T create(T t);

	T update(T t, Long l);

	void delete(Long id);

}
