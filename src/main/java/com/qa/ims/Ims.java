package com.qa.ims;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.controller.ProductController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.dao.ProductDaoMysql;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.OrderServices;
import com.qa.ims.services.ProductServices;
import com.qa.ims.utils.Utils;

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);
	boolean returnToStart = true;
	boolean returnToDomainSelect = false;
	String username = "";
	String password = "";

	public void imsSystem() {

		while (returnToStart) {
			LOGGER.info("What is your username");
			username = Utils.getInput();
			LOGGER.info("What is your password");
			password = Utils.getInput();
			returnToStart = false;
		}

		init(username, password);

		returnToDomainSelect = false;

		LOGGER.info("Which entity would you like to use?");

		Domain.printDomains();
		Domain domain = Domain.getDomain();

		if (domain == Domain.valueOf("STOP")) {
			System.exit(0);
		}

		LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + "?:");

		Action.printActions();
		Action action = Action.getAction();

		switch (domain) {
		case CUSTOMER:
			CustomerController customerController = new CustomerController(
					new CustomerServices(new CustomerDaoMysql(username, password)));
			doAction(customerController, action);

			if (!returnToDomainSelect) {
				Action.printSecondaryActions();
				action = Action.getSecondaryAction();
				doAction(customerController, action);
			}
			imsSystem();
			break;
		case PRODUCT:
			ProductController productController = new ProductController(
					new ProductServices(new ProductDaoMysql(username, password)));
			doAction(productController, action);

			if (!returnToDomainSelect) {
				Action.printSecondaryActions();
				action = Action.getSecondaryAction();
				doAction(productController, action);
			}
			imsSystem();
			break;
		case ORDER:
			OrderController orderController = new OrderController(
					new OrderServices(new OrderDaoMysql(username, password)));
			doAction(orderController, action);

			if (!returnToDomainSelect) {
				Action.printSecondaryActions();
				action = Action.getSecondaryAction();
				doAction(orderController, action);
			}
			imsSystem();
			break;
		default:
			break;
		}

	}

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			returnToDomainSelect = true;
			break;
		case BEGINNING:
			returnToStart = true;
			imsSystem();
			break;
		default:
			break;
		}
	}

	/**
	 * To initialise the database schema. DatabaseConnectionUrl will default to
	 * localhost.
	 * 
	 * @param username
	 * @param password
	 */
	public void init(String username, String password) {
		init("jdbc:mysql://35.240.38.154:3306/", username, password, "src/main/resources/sql-schema.sql");
	}

	public String readFile(String fileLocation) {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				stringBuilder.append(string);
				stringBuilder.append("\r\n");
			}
		} catch (IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
		return stringBuilder.toString();
	}

	/**
	 * To initialise the database with the schema needed to run the application
	 */
	public void init(String jdbcConnectionUrl, String username, String password, String fileLocation) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement();) {
					statement.executeUpdate(string);
				}
			}
		} catch (SQLException | IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
	}

}
