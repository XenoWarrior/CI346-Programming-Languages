package com.projectge.ci346;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

	///
	/// Opens a connection to the database for the session
	///
	public DatabaseManager() {
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
			System.out.println(e);
		}
	}

	///
	/// Gets all employees in the database
	///
	public List getEmployees() {
		try {  
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();  
			ResultSet dbResults = dbStatement.executeQuery("SELECT * FROM employee_data WHERE 1");
			
			List finalResults = new ArrayList();
			while(dbResults.next()) {
				finalResults.add(new EmployeeData(dbResults.getInt(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4)));  
			}
			
			databaseConnection.close();

			return finalResults;
		}
		catch(Exception e) {
			return null;
		}
	}

	///
	/// Gets a single employee from the database
	///
	public List getEmployee(int id) {
		try {  
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!

			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();
			ResultSet dbResults = dbStatement.executeQuery("SELECT * FROM employee_data WHERE em_id = " + id + " LIMIT 1");

			List finalResults = new ArrayList();
			while(dbResults.next()) {
				finalResults.add(new EmployeeData(dbResults.getInt(1), dbResults.getString(2), dbResults.getString(3), dbResults.getString(4))); 
			}
			
			databaseConnection.close();
			
			return finalResults;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	///
	/// Deletes a single employee from the database
	///
	public boolean delEmployee(int id) {
		try {  
			//TODO: MUST UPDATE TO PREPARED STATEMENT TO PREVENT POSSIBLE INJECTION!
			//TODO: FLAGGED DELETION INSTEAD OF DELETE COMPLETELY (add flag "em_deleted" which can be toggled)
			
			Connection databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ci346_employees","root","usbw");
			Statement dbStatement = databaseConnection.createStatement();
			ResultSet dbResults = dbStatement.executeQuery("DELETE FROM employee_data WHERE em_id = " + id + " LIMIT 1");

			databaseConnection.close();
			
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
