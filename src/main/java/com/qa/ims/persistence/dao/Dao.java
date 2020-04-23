package com.qa.ims.persistence.dao;

import java.util.List;

public interface Dao<T> {

	List<T> readAll();

	T create(T t);

	T update(T t, Long l);

	void delete(long id);

}
