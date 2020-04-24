package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private CustomerServices customerServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void readAllTest() {
		CustomerController customerController = new CustomerController(customerServices);
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Chris", "Atkinson", "Atkin.Chris@ymail.com", "0745267712"));
		customers.add(new Customer(2L, "Robin", "Hood", "Rhood@gmail.com", "07548862799"));
		customers.add(new Customer(3L, "Norton", "Internet", "InterNort@yahoo.com", "2080080808"));
		Mockito.when(customerServices.readAll()).thenReturn(customers);
		assertEquals(customers, customerController.readAll());
	}

	@Test
	public void createTest() {
		String firstName = "Chris";
		String surname = "Perrins";
		String email = "Chris.perrins@hotmail.com";
		String phone = "07695318874";
		Mockito.doReturn(firstName, surname, email, phone).when(customerController).getInput();
		Customer customer = new Customer(firstName, surname, email, phone);
		Customer savedCustomer = new Customer(1L, "Chris", "Perrins", "Chris.perrins@hotmail.com", "07695318874");
		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
		assertEquals(savedCustomer, customerController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		Long id = 1L;
		String firstName = "Rhys";
		String surname = "Thompson";
		String email = "fuvnnv";
		String phone = "yhuj";
		Mockito.doReturn(id.toString(), firstName, surname, email, phone).when(customerController).getInput();
		Customer customer = new Customer(1L, firstName, surname, email, phone);
		Mockito.when(customerServices.update(customer, id)).thenReturn(customer);
		assertEquals(customer, customerController.update());
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(customerController).getInput();
		customerController.delete();
		Mockito.verify(customerServices, Mockito.times(1)).delete(1L);
	}

}
