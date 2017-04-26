package com.projectge.ci346;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import gson

@RestController
public class BackEnd {

	private ArrayList<EmployeeData> employeeList;
	
	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String GetIndex() {
		return "{\"error\": 1, \"message\": \"Unknown route.\"}";
	}

	@RequestMapping(value = "/api/employees", method = RequestMethod.GET)
	public String GetEmployeeList() {
		DatabaseManager db = new DatabaseManager();
		List dbResults = db.getEmployees();
		
		if(dbResults != null) {
			return "{\"error\": 0, \"message\": \"" + dbResults.toString() + "\"}";
		}
		else {
			return "{\"error\": 1, \"message\": \"No results found.\"}";
		}
	}
	
	@RequestMapping(value = "/api/employee/{id}", method = RequestMethod.GET)
	public String GetEmployee(@RequestParam("id") int id) {
		DatabaseManager db = new DatabaseManager();
		List dbResults = db.getEmployee(id);

		if(dbResults != null) {
			return "{\"error\": 0, \"message\": \"" + dbResults.toString() + "\"}";
		}
		else {
			return "{\"error\": 1, \"message\": \"No results found, or an error occured.\"}";
		}
	}
	
}