create database if not exists ims;
CREATE TABLE if not exists ims.customer_table(customer_id int AUTO_INCREMENT, first_name varchar(100) NOT NULL, surname varchar(100) NOT NULL, email varchar(150) NOT NULL, phone_number varchar(100) NOT NULL, UNIQUE(customer_id), PRIMARY KEY(customer_id));
