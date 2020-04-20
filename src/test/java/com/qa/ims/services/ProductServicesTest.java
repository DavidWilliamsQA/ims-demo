package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Product;

@RunWith(MockitoJUnitRunner.class)
public class ProductServicesTest {

	@Mock
	private Dao<Product> productDao;

	@InjectMocks
	private ProductServices productServices;

	@Test
	public void productServicesCreate() {
		Product product = new Product("Nutella", 1.75, 74);
		productServices.create(product);
		Mockito.verify(productDao, Mockito.times(1)).create(product);
	}

	@Test
	public void productServicesRead() {
		productServices.readAll();
		Mockito.verify(productDao, Mockito.times(1)).readAll();
	}

	@Test
	public void productServicesUpdate() {
		Product product = new Product("Nutella", 1.75, 74);
		productServices.update(product);
		Mockito.verify(productDao, Mockito.times(1)).update(product);
	}

	@Test
	public void productServicesDelete() {
		productServices.delete(1L);
		Mockito.verify(productDao, Mockito.times(1)).delete(1L);
	}
}
