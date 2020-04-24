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

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private OrderServices orderServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the order
	 * controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;

	List<Long> products = new ArrayList<>();
	List<Integer> amount = new ArrayList<>();

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, 3.0, products, amount));
		orders.add(new Order(2L, 1L, 4.0, products, amount));
		orders.add(new Order(3L, 2L, 2.0, products, amount));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

//	@Test
//	public void createTest() {
//		Long id = 1L;
//		Long custId = 1L;
//		Double total = 3.0;
//		Mockito.doReturn(id.toString(), custId.toString(), total.toString(), products.toString(), amount.toString())
//				.when(orderController).getInput();
//		Order order = new Order(id, custId, total, products, amount);
//		Order savedOrder = new Order(1L, 1L, 3.0, products, amount);
//		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
//		assertEquals(savedOrder, orderController.create());
//	}

	/**
	 * 
	 */
//	@Test
//	public void updateTest() {
//		Long id = 1L;
//		Long custId = 1L;
//		Double total = 3.0;
//		Mockito.doReturn(id.toString(), custId.toString(), total.toString(), products.toString(), amount.toString())
//				.when(orderController).getInput();
//		Order order = new Order(1L, 1L, 3.0, products, amount);
//		Mockito.when(orderServices.update(order, id)).thenReturn(order);
//		assertEquals(order, orderController.update());
//	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}

}
