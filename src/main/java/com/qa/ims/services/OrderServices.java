package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Order;

public class OrderServices implements CrudServices<Order> {

	private Dao<Order> orderDao;

	public OrderServices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	@Override
	public Order create(Order ord) {
		return orderDao.create(ord);
	}

	@Override
	public Order update(Order ord) {
		return orderDao.update(ord);
	}

	@Override
	public void delete(Long id) {
		orderDao.delete(id);
	}

}
