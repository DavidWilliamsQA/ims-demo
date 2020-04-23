create database if not exists ims;
CREATE TABLE if not exists ims.customer_table(customer_id int AUTO_INCREMENT, first_name varchar(100) NOT NULL, surname varchar(100) NOT NULL, email varchar(150) NOT NULL, phone_number varchar(100) NOT NULL, UNIQUE(customer_id), PRIMARY KEY(customer_id));
CREATE TABLE if not exists ims.product_table(product_id int AUTO_INCREMENT, name varchar(100) NOT NULL UNIQUE, price double NOT NULL, stock_remaining double NOT NULL, UNIQUE(product_id), PRIMARY KEY(product_id));
CREATE TABLE if not exists ims.order_table(order_id int AUTO_INCREMENT, customer_id int NOT NULL, total double, date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY(order_id), FOREIGN KEY (customer_id) REFERENCES customer_table(customer_id));
CREATE TABLE if not exists ims.orderline_table(orderId int NOT NULL, product_id int NOT NULL, amount int NOT NULL DEFAULT 1, FOREIGN KEY (orderId) REFERENCES order_table(order_id), FOREIGN KEY (product_id) REFERENCES product_table(product_id));