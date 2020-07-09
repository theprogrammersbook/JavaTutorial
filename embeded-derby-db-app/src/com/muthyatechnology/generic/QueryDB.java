package com.muthyatechnology.generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryDB {

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName(CreateDB.DRIVER);
		String select = "select * from company";
		try (Connection connection = DriverManager.getConnection(CreateDB.JDBC_URL);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(select);) {
			while (result.next()) {
				System.out.println(result.getString("name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
