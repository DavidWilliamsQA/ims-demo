package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	private Product product;
	private Product other;

	@Before
	public void setUp() {
		product = new Product(1L, "Nutella", 1.50, 38);
		other = new Product(1L, "Nutella", 1.50, 38);
	}

	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void settersTest() {
		assertNotNull(product.getId());
		assertNotNull(product.getName());
		assertNotNull(product.getPrice());
		assertNotNull(product.getStock());

		product.setId(null);
		assertNull(product.getId());
		product.setName(null);
		assertNull(product.getName());
		product.setPrice(null);
		assertNull(product.getPrice());
		product.setStock(null);
		assertNull(product.getStock());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(product.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(product.equals(new Object()));
	}

	@Test
	public void createProductWithId() {
		assertEquals(1L, product.getId(), 0);
		assertEquals("Nutella", product.getName());
		assertEquals(1.50, product.getPrice(), 0.01);
	}

	@Test
	public void checkEquality() {
		assertTrue(product.equals(product));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(product.equals(other));
	}

	@Test
	public void productNameNullButOtherNameNotNull() {
		product.setName(null);
		assertFalse(product.equals(other));
	}

	@Test
	public void productNamesNotEqual() {
		other.setName("rhys");
		assertFalse(product.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		product.setName(null);
		other.setName(null);
		assertTrue(product.equals(other));
	}

	@Test
	public void nullId() {
		product.setId(null);
		assertFalse(product.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		product.setId(null);
		other.setId(null);
		assertTrue(product.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(product.equals(other));
	}

	@Test
	public void nullPrice() {
		product.setPrice(null);
		assertFalse(product.equals(other));
	}

	@Test
	public void nullPriceOnBoth() {
		product.setPrice(null);
		other.setPrice(null);
		assertTrue(product.equals(other));
	}

	@Test
	public void otherPriceDifferent() {
		other.setPrice(3.00);
		assertFalse(product.equals(other));
	}

	@Test
	public void nullStock() {
		product.setStock(null);
		assertFalse(product.equals(other));
	}

	@Test
	public void nullStockOnBoth() {
		product.setStock(null);
		other.setStock(null);
		assertTrue(product.equals(other));
	}

	@Test
	public void otherStockDifferent() {
		other.setStock(5);
		assertFalse(product.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Product product = new Product("Nutella", 2.50, 877);
		assertNull(product.getId());
		assertNotNull(product.getName());
		assertNotNull(product.getPrice());
		assertNotNull(product.getStock());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(product.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Product product = new Product(null, null, null, null);
		Product other = new Product(null, null, null, null);
		assertEquals(product.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1, name:Nutella, price:1.5, stock:38";
		assertEquals(toString, product.toString());
	}
}
