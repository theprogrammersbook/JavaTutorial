package com.muthyatechnology.generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
	public static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	public static final String JDBC_URL = "jdbc:derby:nagaderby;create=true";

	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName(DRIVER);
		try(    
				Connection connection = DriverManager.getConnection(JDBC_URL);
				Statement statement = connection.createStatement();
				) {

			String createTable = "CREATE TABLE company (  name varchar(255)) ";
			statement.execute(createTable);
			statement.execute("insert into company values "+"('muthya')");
		   System.out.println("Created One Table and inserted data into Derby DB");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
