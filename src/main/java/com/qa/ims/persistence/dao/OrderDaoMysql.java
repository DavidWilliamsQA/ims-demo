package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql implements Dao<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://35.240.38.154:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("order_id");
		Long customer_id = resultSet.getLong("customer_id");
		Double total = resultSet.getDouble("total");

		// there is no method in the resultSet for 'getlist' as there is
		// in the others to 'getlong' and 'getdouble'
		//
		// so i am going to have to access the order id via the orderline table

		String query = "SELECT * FROM orderline_table WHERE order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement statement = connection.prepareStatement(query);) {

			List<Long> products = new ArrayList<Long>();
			List<Integer> amount = new ArrayList<Integer>();
			statement.setLong(1, id);

			try (ResultSet result = statement.executeQuery();) {
				while (result.next()) {
					products.add(result.getLong("product_id"));
					amount.add(result.getInt("amount"));
				}
			}

			return new Order(id, customer_id, total, products, amount);
		} catch (Exception e) {
			LOGGER.info(e.getStackTrace());
			LOGGER.info(e.getMessage());
		}
		return null;

	}

	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_table");) {
			ArrayList<Order> order = new ArrayList<>();

			while (resultSet.next()) {
				order.add(orderFromResultSet(resultSet));
			}
			return order;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"SELECT order_table.order_id, order_table.customer_id, order_table.total, orderline_table.product_id, orderline_table.amount FROM order_table LEFT JOIN orderline_table ON order_table.order_id=orderline_table.order_id ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order create(Order order) {
		String queryOrdersInsert = "INSERT INTO order_table(customer_id, total) VALUES (?,?)";
		String queryOrderlineInsert = "INSERT INTO orderline_table(order_id, product_id, amount) VALUES (?, ?, ?)";
		String queryOrderIdSelect = "SELECT order_id FROM order_table ORDER BY order_id DESC LIMIT 1";

		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement statementOrdersInsert = connection.prepareStatement(queryOrdersInsert);
				PreparedStatement statementOrderlineInsert = connection.prepareStatement(queryOrderlineInsert);
				PreparedStatement statementOrderId = connection.prepareStatement(queryOrderIdSelect);) {
			statementOrdersInsert.setLong(1, order.getCustomerId());
			statementOrdersInsert.setDouble(2, totalCalculation(order.getProducts(), order.getAmount()));
			statementOrdersInsert.executeUpdate();

			try (ResultSet resultset = statementOrderId.executeQuery();) {
				resultset.next();
				Long order_id = resultset.getLong("order_id");
				List<Long> products = order.getProducts();
				List<Integer> amount = order.getAmount();

				for (int i = 0; i < order.getProducts().size(); i++) {
					statementOrderlineInsert.setLong(1, order_id);
					statementOrderlineInsert.setLong(2, products.get(i));
					statementOrderlineInsert.setDouble(3, amount.get(i));
					statementOrderlineInsert.executeUpdate();
				}
			}

			return readLatest();

		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}

		return null;
	}

	public Order readOrder(Long id) {
		String query = "SELECT order_table.order_id, order_table.customer_id, order_table.total, orderline_table.product_id, orderline_table.amount FROM order_table LEFT JOIN orderline_table ON order_table.order_id=orderline_table.order_id WHERE order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return orderFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		String queryOrder = "DELETE FROM order_table WHERE order_id = ?";
		String queryOrderline = "DELETE FROM orderline_table WHERE order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement statementOrder = connection.prepareStatement(queryOrder);
				PreparedStatement statementOrderline = connection.prepareStatement(queryOrderline);) {

			statementOrderline.setLong(1, id);
			statementOrderline.executeUpdate();
			statementOrder.setLong(1, id);
			statementOrder.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	// Calculate the total
	public Double totalCalculation(List<Long> products, List<Integer> amount) {
		Double total = 0.00;
		for (int i = 0; i < products.size(); i++) {
			Long id = products.get(i);
			String query = "SELECT price FROM product_table WHERE product_id = ?";
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					PreparedStatement statement = connection.prepareStatement(query);) {
				statement.setLong(1, id);
				try (ResultSet resultSet = statement.executeQuery();) {
					resultSet.next();
					Double price = resultSet.getDouble(1);
					Integer specificAmount = amount.get(i);
					Double pricePerOrder = price * specificAmount;
					total += pricePerOrder;
				}
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}

		}

		return total;
	}

}
