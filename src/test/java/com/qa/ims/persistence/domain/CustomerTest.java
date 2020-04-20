package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

	private Customer customer;
	private Customer other;

	@Before
	public void setUp() {
		customer = new Customer(1L, "Chris", "Perrins", "Chris.perrins@hotmail.com", "075622287631");
		other = new Customer(1L, "Chris", "Perrins", "Chris.perrins@hotmail.com", "075622287631");
	}

	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
		assertNotNull(customer.getEmail());
		assertNotNull(customer.getPhone());

		customer.setId(null);
		assertNull(customer.getId());
		customer.setFirstName(null);
		assertNull(customer.getFirstName());
		customer.setSurname(null);
		assertNull(customer.getSurname());
		customer.setEmail(null);
		assertNull(customer.getEmail());
		customer.setPhone(null);
		assertNull(customer.getPhone());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(customer.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(customer.equals(new Object()));
	}

	@Test
	public void createCustomerWithId() {
		assertEquals(1L, customer.getId(), 0);
		assertEquals("Chris", customer.getFirstName());
		assertEquals("Perrins", customer.getSurname());
	}

	@Test
	public void checkEquality() {
		assertTrue(customer.equals(customer));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(customer.equals(other));
	}

	@Test
	public void customerNameNullButOtherNameNotNull() {
		customer.setFirstName(null);
		assertFalse(customer.equals(other));
	}

	@Test
	public void customerNamesNotEqual() {
		other.setFirstName("rhys");
		assertFalse(customer.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		customer.setFirstName(null);
		other.setFirstName(null);
		assertTrue(customer.equals(other));
	}

	@Test
	public void nullId() {
		customer.setId(null);
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		customer.setId(null);
		other.setId(null);
		assertTrue(customer.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullSurname() {
		customer.setSurname(null);
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullSurnameOnBoth() {
		customer.setSurname(null);
		other.setSurname(null);
		assertTrue(customer.equals(other));
	}

	@Test
	public void otherSurnameDifferent() {
		other.setSurname("thompson");
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullEmail() {
		customer.setEmail(null);
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullEmailOnBoth() {
		customer.setEmail(null);
		other.setEmail(null);
		assertTrue(customer.equals(other));
	}

	@Test
	public void otherEmailDifferent() {
		other.setEmail("tris.perrins@hotmail.com");
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullPhone() {
		customer.setPhone(null);
		assertFalse(customer.equals(other));
	}

	@Test
	public void nullPhoneOnBoth() {
		customer.setPhone(null);
		other.setPhone(null);
		assertTrue(customer.equals(other));
	}

	@Test
	public void otherPhoneDifferent() {
		other.setPhone("4444555");
		assertFalse(customer.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Customer customer = new Customer("Chris", "Perrins", "Chris.p@hotmail.com", "0759862415");
		assertNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(customer.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Customer customer = new Customer(null, null, null, null);
		Customer other = new Customer(null, null, null, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1, firstName:Chris, surname:Perrins, Email:Chris.perrins@hotmail.com, Phone:075622287631";
		assertEquals(toString, customer.toString());
	}
}
