package com.projectge.ci346;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Ashley Scott
 * 
 * Handles transactions with a MySQL database.
 * Login values are currently hard coded in.
 * 
 * I was planning on writing a database manager with appropriate execute(), update(), delete() methods, but decided to
 * go with writing individual "Get/Delte/Edit/Add Employee" methods for ease to make following what is going on easier. 
 *  
 */
public class DatabaseManager {

	///
	/// Opens a connection to the database for the session
	///
	public DatabaseManager() throws Exception {
		try {  
			Class.forName("com.mysql.jdbc.Driver");

			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			
			Statement dbStatement = databaseConnection.createStatement();  
			ResultSet dbResults = dbStatement.executeQuery("SELECT * FROM employee_data WHERE 1 LIMIT 1");
			
			while(dbResults.next()) {
				System.out.println(dbResults.getInt(1) + "  " + dbResults.getString(2) + "  " + dbResults.getString(3) + "  " + dbResults.getString(4));  
			}
			
			databaseConnection.close();
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
	}

	///
	/// Gets all employees in the database
	///
	public ArrayList<EmployeeData> GetEmployees() throws Exception {
		try {  
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			
			Statement dbStatement = databaseConnection.createStatement();  
			ResultSet dbResults = dbStatement.executeQuery("SELECT * FROM employee_data WHERE em_deleted = 0");
			
			ArrayList<EmployeeData> finalResults = new ArrayList<EmployeeData>();
			while(dbResults.next()) {
				finalResults.add(new EmployeeData(dbResults.getInt(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4)));  
			}
			
			databaseConnection.close();

			return finalResults;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
	}

	///
	/// Gets a single employee from the database
	///
	public ArrayList<EmployeeData> GetEmployee(int id) throws Exception {
		try {
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			
			String queryString = "SELECT * FROM employee_data WHERE em_id = ? LIMIT 1";
			
			PreparedStatement preparedQuery = databaseConnection.prepareStatement(queryString);
			preparedQuery.setString(1, String.valueOf(id));
			
			ResultSet dbResults = preparedQuery.executeQuery();

			ArrayList<EmployeeData> finalResults = new ArrayList<EmployeeData>();
			while(dbResults.next()) {
				finalResults.add(new EmployeeData(dbResults.getInt(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4))); 
			}
			
			databaseConnection.close();
			
			return finalResults;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
	}

	///
	/// Deletes a single employee from the database
	///
	public boolean DelEmployee(int id) throws Exception {
		try {  
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");

			String queryString = "UPDATE employee_data SET em_deleted = 1 WHERE em_id = ? LIMIT 1";
			
			PreparedStatement preparedQuery = databaseConnection.prepareStatement(queryString);
			preparedQuery.setString(1, String.valueOf(id));
			
			boolean error = preparedQuery.execute();
			
			databaseConnection.close();
			
			return error;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
	}
	
	///
	/// Edits a single employee in the database
	///
	public boolean EditEmployee(int id, String fname, String lname, String shift) throws Exception {
		try {
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");

			String queryString = "UPDATE employee_data SET em_firstname = ?, em_lastname = ?, em_shift = ? WHERE em_id = ? LIMIT 1";
			
			PreparedStatement preparedQuery = databaseConnection.prepareStatement(queryString);
			preparedQuery.setString(1, fname);
			preparedQuery.setString(2, lname);
			preparedQuery.setString(3, shift);
			preparedQuery.setString(4, String.valueOf(id));
			
			boolean error = preparedQuery.execute();

			databaseConnection.close();
			
			return error;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
	}

	///
	/// Adds an employee to the database
	///
	public boolean AddEmployee(String fname, String lname, String shift) throws Exception {
		try {  
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");

			String queryString = "INSERT INTO employee_data(em_firstname, em_lastname, em_shift) VALUES(?, ?, ?)";
			
			PreparedStatement preparedQuery = databaseConnection.prepareStatement(queryString);
			preparedQuery.setString(1, fname);
			preparedQuery.setString(2, lname);
			preparedQuery.setString(3, shift);
			
			boolean error = preparedQuery.execute();
			
			databaseConnection.close();
			
			return error;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
	}

	///
	/// DEBUG METHOD
	/// Undeletes all employees
	///
	public boolean UndelAll() throws Exception {
		try {  
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			
			Statement dbStatement = databaseConnection.createStatement();
			
			boolean error = dbStatement.execute("UPDATE employee_data SET em_deleted = 0 WHERE 1");

			databaseConnection.close();
			
			return error;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
		
	}
	
	///
	/// DEBUG METHOD
	/// Deletes all employees
	///
	public boolean DelAll() throws Exception {
		try {  
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			
			Statement dbStatement = databaseConnection.createStatement();
			
			boolean error = dbStatement.execute("UPDATE employee_data SET em_deleted = 1 WHERE 1");

			databaseConnection.close();
			
			return error;
		}
		catch(Exception e) {
			// Pass the exception up for handling in the application.
			throw e;
		}
		
	}
}
