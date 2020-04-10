package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Product;

public class ProductServices implements CrudServices<Product> {

	private Dao<Product> productDao;
	
	public ProductServices(Dao<Product> productDao) {
		this.productDao = productDao;
	}

	public List<Product> readAll() {
		return productDao.readAll();
	}

	public Product create(Product p) {
		return productDao.create(p);
	}

	public Product update(Product p) {
		return productDao.update(p);
	}

	public void delete(Long id) {
		productDao.delete(id);
	}

}
