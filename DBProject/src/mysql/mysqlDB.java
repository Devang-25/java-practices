package mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Crunchify.com Simple Hello World MySQL Tutorial on how to make JDBC
 *         connection, Add and Retrieve Data by App Shah
 * 
 */

public class mysqlDB {

	static Connection mysqlConn = null;
	static PreparedStatement prepareStat = null;
	static int count=20;

	public static void main(String[] argv) {

		try {
			log("-------- how to make JDBC connection to MySQL DB locally  ------------");
			makeJDBCConnection();

			log("\n---------- Adding company 'Crunchify LLC' to DB ----------");
			addDataToDB("Crunchify, LLC.", "NYC, US", 5, "https://crunchify.com");
			addDataToDB("Google Inc.", "Mountain View, CA, US", 50000, "https://google.com");
			addDataToDB("Apple Inc.", "Cupertino, CA, US", 30000, "http://apple.com");

			log("\n---------- Let's get Data from DB ----------");
			getDataFromDB();

			prepareStat.close();
			mysqlConn.close(); // connection close

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static void makeJDBCConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			log("Congrats - Seems your MySQL JDBC Driver Registered!");
		} catch (ClassNotFoundException e) {
			log("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
			e.printStackTrace();
			return;
		}

		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			mysqlConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "canthack1!");
			if (mysqlConn != null) {
				log("Connection Successful! Enjoy. Now it's time to push data");
			} else {
				log("Failed to make connection!");
			}
		} catch (SQLException e) {
			log("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		}

	}

	private static void addDataToDB(String companyName, String address, int totalEmployee, String webSite) {

		try {
			String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?, ?)";
			int id = count++;
			prepareStat = mysqlConn.prepareStatement(insertQueryStatement);
			prepareStat.setInt(5, id);
			prepareStat.setString(1, companyName);
			prepareStat.setString(2, address);
			prepareStat.setInt(3, totalEmployee);
			prepareStat.setString(4, webSite);

			// execute insert SQL statement
			prepareStat.executeUpdate();
			log(companyName + " added successfully");
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

	private static void getDataFromDB() {

		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT * FROM Employee";

			prepareStat = mysqlConn.prepareStatement(getQueryStatement);

			// Execute the Query, and get a java ResultSet
			ResultSet rs = prepareStat.executeQuery();

			// Let's iterate through the java ResultSet
			while (rs.next()) {

				String name = rs.getString("Name");
				String address = rs.getString("Address");
				int employeeCount = rs.getInt("EmployeeCount");
				String website = rs.getString("Website");
				int id = rs.getInt("id");

				// Simply Print the results
				System.out.format("%s, %s, %s, %s\n", name, address, employeeCount, website);
			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}

	}

	// Simple log utility
	private static void log(String string) {
		System.out.println(string);

	}

}
