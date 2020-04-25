package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	private Order other;
	private Order otherVariables;
	private Order listVariables;
	List<Long> products = new ArrayList<>();
	List<Long> productsDiff = new ArrayList<>();
	List<Integer> amount = new ArrayList<>();
	List<Integer> amountDiff = new ArrayList<>();

	@Before
	public void setUp() {
		productsDiff.add(1l);
		amountDiff.add(2);
		order = new Order(1L, 1L, 5.0, products, amount);
		other = new Order(1L, 1L, 5.0, products, amount);
		otherVariables = new Order(1L, 1L, 3.0);
		listVariables = new Order(products, amount, 1L);
	}

	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomerId());
		assertNotNull(order.getTotal());
		assertNotNull(order.getProducts());
		assertNotNull(order.getAmount());

		order.setId(null);
		assertNull(order.getId());
		order.setCustomerId(null);
		assertNull(order.getCustomerId());
		order.setTotal(null);
		assertNull(order.getTotal());
		order.setProducts(null);
		assertNull(order.getProducts());
		order.setAmount(null);
		assertNull(order.getAmount());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getId(), 0);
		assertEquals(1L, order.getCustomerId(), 0);
		assertEquals(5.0, order.getTotal(), 0);
		assertEquals(products, order.getProducts());
		assertEquals(amount, order.getAmount());

	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	@Test
	public void orderIdNullButOtherNameNotNull() {
		order.setId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void orderIdNotEqual() {
		other.setId(9L);
		assertFalse(order.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullId() {
		order.setId(null);
		other.setId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void nullId() {
		order.setId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		order.setId(null);
		other.setId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullCustomerId() {
		order.setCustomerId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullCustomerIdOnBoth() {
		order.setCustomerId(null);
		other.setCustomerId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherCustomerIdDifferent() {
		other.setCustomerId(5L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullTotal() {
		order.setTotal(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullTotalOnBoth() {
		order.setTotal(null);
		other.setTotal(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherTotalDifferent() {
		other.setTotal(6.0);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullAmount() {
		order.setAmount(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullAmountOnBoth() {
		order.setAmount(null);
		other.setAmount(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherAmountDifferent() {
		other.setAmount(amountDiff);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullProducts() {
		order.setProducts(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullProductsOnBoth() {
		order.setProducts(null);
		other.setProducts(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherProductsDifferent() {
		other.setProducts(productsDiff);
		assertFalse(order.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Order order = new Order(1L, products, amount);
		assertNull(order.getId());
		assertNotNull(order.getProducts());
		assertNotNull(order.getAmount());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(null, null, null, null, null);
		Order other = new Order(null, null, null, null, null);
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1, customerId:1, total:5.0";
		assertEquals(toString, order.toString());
	}
}
