package com.qa.ims.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ActionTest {

	@Test
	public void createTest() {
		Action action = Action.CREATE;
		assertTrue(action.getDescription().toLowerCase().contains("save"));
	}

	@Test
	public void readTest() {
		Action action = Action.READ;
		assertTrue(action.getDescription().toLowerCase().contains("read"));
	}

	@Test
	public void updateTest() {
		Action action = Action.UPDATE;
		assertTrue(action.getDescription().toLowerCase().contains("change"));
	}

	@Test
	public void deleteTest() {
		Action action = Action.DELETE;
		assertTrue(action.getDescription().toLowerCase().contains("remove"));
	}

	@Test
	public void returnTest() {
		Action action = Action.RETURN;
		assertTrue(action.getDescription().toLowerCase().contains("return"));
	}

	@Test
	public void beginningTest() {
		Action action = Action.BEGINNING;
		assertTrue(action.getDescription().toLowerCase().contains("start"));
	}

}
