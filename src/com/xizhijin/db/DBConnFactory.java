package com.xizhijin.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnFactory {
	// static reference to itself.
	private static DBConnFactory instance = new DBConnFactory();

	String url = "jdbc:mysql://localhost/exampledb";
	String driverClass = "com.mysql.jdbc.Driver";
	String user = "root";
	String passwd = "1231527";

	// private constructor.
	private DBConnFactory() {
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public static DBConnFactory getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection connection = DriverManager.getConnection(url, user, passwd);
		return connection;
	}
}
