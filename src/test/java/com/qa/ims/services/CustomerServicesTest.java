package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTest {

	@Mock
	private Dao<Customer> customerDao;

	@InjectMocks
	private CustomerServices customerServices;

	@Test
	public void customerServicesCreate() {
		Customer customer = new Customer("chris", "perrins", "c.pers@yahoo.com", "777333999");
		customerServices.create(customer);
		Mockito.verify(customerDao, Mockito.times(1)).create(customer);
	}

	@Test
	public void customerServicesRead() {
		customerServices.readAll();
		Mockito.verify(customerDao, Mockito.times(1)).readAll();
	}

	@Test
	public void customerServicesUpdate() {
		Customer customer = new Customer("chris", "perrins", "c.perrins@yahoo.com", "8473783838");
		customerServices.update(customer, 1L);
		Mockito.verify(customerDao, Mockito.times(1)).update(customer, 1L);
	}

	@Test
	public void customerServicesDelete() {
		customerServices.delete(1L);
		Mockito.verify(customerDao, Mockito.times(1)).delete(1L);
	}

}
