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

import com.qa.ims.persistence.domain.Product;
import com.qa.ims.services.ProductServices;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private ProductServices productServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the product
	 * controller
	 */
	@Spy
	@InjectMocks
	private ProductController productController;

	@Test
	public void readAllTest() {
		ProductController productController = new ProductController(productServices);
		List<Product> products = new ArrayList<>();
		products.add(new Product("Digestives", 1.00, 50));
		products.add(new Product("Custard Creams", 0.90, 100));
		products.add(new Product("Orange Juice", 1.50, 70));
		Mockito.when(productServices.readAll()).thenReturn(products);
		assertEquals(products, productController.readAll());
	}

	@Test
	public void createTest() {
		String name = "Tangerine";
		Double price = 2.0;
		Integer stock = 50;
		Mockito.doReturn(name, price.toString(), stock.toString()).when(productController).getInput();
		Product product = new Product(name, price, stock);
		Product savedProduct = new Product(1L, "Tangerine", 2.0, 50);
		Mockito.when(productServices.create(product)).thenReturn(savedProduct);
		assertEquals(savedProduct, productController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		Long id = 1L;
		String name = "Bananna";
		Double price = 6.25;
		Integer stock = 80;
		Mockito.doReturn(id.toString(), name, price.toString(), stock.toString()).when(productController).getInput();
		Product product = new Product(1L, name, price, stock);
		Mockito.when(productServices.update(product, id)).thenReturn(product);
		assertEquals(product, productController.update());
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(productController).getInput();
		productController.delete();
		Mockito.verify(productServices, Mockito.times(1)).delete(1L);
	}

}
