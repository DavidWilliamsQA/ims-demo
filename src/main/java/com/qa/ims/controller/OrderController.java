package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order ord : orders) {
			LOGGER.info(ord.toString());
		}
		return orders;
	}

	@Override
	public Order create() {
		ArrayList<Long> listOfProducts = new ArrayList<>();
		ArrayList<Integer> listOfAmounts = new ArrayList<>();
		String addMoreItems = "Y";

		LOGGER.info("Which customer is making an order?");
		Long custID = Long.valueOf(getInput());

		while (addMoreItems.equals("Y")) {
			LOGGER.info("Enter the Product ID of the Product you would like to add to the order");
			listOfProducts.add(Long.valueOf(getInput()));
			LOGGER.info("How many of this product would you like?");
			listOfAmounts.add(Integer.valueOf(getInput()));
			LOGGER.info("Would you like to add more products to this order? (Y/N)");
			addMoreItems = getInput().toUpperCase();
		}

		Order order = orderService.create(new Order(custID, listOfProducts, listOfAmounts));
		LOGGER.info("Order Created!");
		return order;
	}

	@Override
	public Order update() {
		ArrayList<Long> listOfProducts = new ArrayList<>();
		ArrayList<Integer> listOfAmounts = new ArrayList<>();
		String addMoreItems = "Y";

		LOGGER.info("What is the Order ID for the Order that you would like to update?");
		Long ordID = Long.valueOf(getInput());
		while (addMoreItems.equals("Y")) {
			LOGGER.info("Enter the Product ID of the product you'd like to add to the order");
			listOfProducts.add(Long.valueOf(getInput()));
			LOGGER.info("How many of this product would you like?");
			listOfAmounts.add(Integer.valueOf(getInput()));
			LOGGER.info("Would you like to add more products to this order? (Y/N)");
			addMoreItems = getInput().toUpperCase();
		}

		Order order = orderService.create(new Order(listOfProducts, listOfAmounts, ordID));
		LOGGER.info("Order Updated!");
		return order;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the ID of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Order Deleted!");
	}

}
