package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class ProductController implements CrudController<Product> {

	public static final Logger LOGGER = Logger.getLogger(ProductController.class);

	private CrudServices<Product> productService;

	public ProductController(CrudServices<Product> productService) {
		this.productService = productService;
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Product> readAll() {
		List<Product> product = productService.readAll();
		for (Product prod : product) {
			LOGGER.info(prod.toString());
		}
		return product;
	}

	@Override
	public Product create() {
		LOGGER.info("Please enter a product name");
		String name = getInput();
		LOGGER.info("Please enter the product price");
		Double price = Double.valueOf(getInput());
		LOGGER.info("Please enter the amount of stock");
		Integer stock = Integer.valueOf(getInput());
		Product product = productService.create(new Product(name, price, stock));
		LOGGER.info("Product Created");
		return product;
	}

	@Override
	public Product update() {
		LOGGER.info("Please enter the id of the product you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter the product name");
		String name = getInput();
		LOGGER.info("Please enter the price of the product");
		Double price = Double.valueOf(getInput());
		LOGGER.info("Please enter the stock");
		Integer stock = Integer.valueOf(getInput());
		Product product = productService.update(new Product(id, name, price, stock));
		LOGGER.info("Product Updated");
		return product;
	}

	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the product you would like to delete");
		Long id = Long.valueOf(getInput());
		productService.delete(id);
		LOGGER.info("Product Deleted");
	}

}
