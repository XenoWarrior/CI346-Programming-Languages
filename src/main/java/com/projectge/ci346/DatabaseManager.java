package com.projectge.ci346;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * 
 * @author Ashley Scott
 * 
 * Handles transactions with a MySQL database.
 * Values are currently hard coded in the constructor.
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
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!

			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();
			ResultSet dbResults = dbStatement.executeQuery("SELECT * FROM employee_data WHERE em_id = " + id + " LIMIT 1");

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
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!
			
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();
			boolean error = dbStatement.execute("UPDATE employee_data SET em_deleted = 1 WHERE em_id = " + id + " LIMIT 1");

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
	public boolean EditEmployee(int id, String name, String shift) throws Exception {
		try {  
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!
			
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();
			boolean error = dbStatement.execute("UPDATE employee_data SET em_name = " + name + ", em_shift = " + shift + " WHERE em_id = " + id + " LIMIT 1");

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
	public boolean AddEmployee(String name, String shift) throws Exception {
		try {  
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!
			
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();
			boolean error = dbStatement.execute("INSERT INTO employee_data(em_name, em_shift) VALUES(" + name + "," + shift + ")");

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
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!
			
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
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!
			
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
