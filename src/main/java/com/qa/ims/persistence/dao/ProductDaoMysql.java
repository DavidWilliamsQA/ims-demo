package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Product;

public class ProductDaoMysql implements Dao<Product> {
	
	public static final Logger LOGGER = Logger.getLogger(ProductDaoMysql.class);
	
	private String connectionUrl;
	private String username;
	private String password;
	
	public ProductDaoMysql(String username, String password) {
		this.connectionUrl = "jdbc:mysql://35.240.38.154:3306/ims";
		this.username = username;
		this.password = password;
	}
	
	public ProductDaoMysql(String connectionUrl, String username, String password) {
		this.connectionUrl = connectionUrl;
		this.username = username;
		this.password = password;
	}

	Product productFromResultSet(ResultSet resultSet) throws SQLException{
		Long id = resultSet.getLong("product_id");
		String name = resultSet.getString("name");
		Double price = resultSet.getDouble("price");
		Integer stock = resultSet.getInt("stock_remaining");
		return new Product(id, name, price, stock);
	}
	
	@Override
	public List<Product> readAll() {
		try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from product_table");) {
			ArrayList<Product> product = new ArrayList<>();
			while (resultSet.next()) {
				product.add(productFromResultSet(resultSet));
			}
			return product;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public Product readLatest() {
		try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM product_table ORDER BY product_id DESC LIMIT 1");) {
			resultSet.next();
			return productFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Product create(Product product) {
		try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("insert into product_table(name, price, stock_remaining) values('" + product.getName() + "', '"  
				+  product.getPrice() + "', '" +  product.getStock() + "' )");
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public Product readProduct(Long id) {
		try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM product_table where product_id = " + id);) {
			resultSet.next();
			return productFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Product update(Product product) {
		try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("update product_table set name ='" + product.getName() + "', price ='"
					+ product.getPrice() + "' where product_id =" + product.getId());
			return readProduct(product.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("delete from product_table where product_id = " + id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

}
