package com.projectge.ci346;

import com.projectge.*; // Some errors show up if this is not imported, but it works regardless...

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class BackEnd {

	Gson gson = new Gson();
	
	@RequestMapping(value = "/api", method = RequestMethod.GET, produces = "plain/text")
	public String GetIndex() {
		HashMap<String, String> finalResults = HashMap<String, String>();

		finalResults.put("error", "This is the entry route.");
		
		return gson.toJson(finalResults);
	}

	@RequestMapping(value = "/api/employees", method = RequestMethod.GET, produces = "plain/text")
	public String GetEmployeeList() {
		HashMap<String, String> finalResults = new HashMap<String, String>();
		
		try {
			DatabaseManager db = new DatabaseManager();
			
			ArrayList<EmployeeData> dbResults = db.GetEmployees();
			if(dbResults != null) {
				for (EmployeeData e : dbResults) {
					HashMap<String, String> items = new HashMap<String, String>();

					items.put("id", String.valueOf(e.getID()));
					items.put("fullname", e.getFullName());
					items.put("shift", e.getShift());
					
					finalResults.put(String.valueOf(finalResults.size()), gson.toJson(items));
				}
			}
			else {
				finalResults.put("error", "No employees found.");
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
		}
		
		return gson.toJson(finalResults);
	}

	@RequestMapping(value = "/api/employee/{id}", method = RequestMethod.DELETE, produces = "plain/text")
	public String DelEmployee(@PathVariable("id") int id) {
		HashMap<String, String> finalResults = new HashMap<String, String>();
		
		try {
			DatabaseManager db = new DatabaseManager();
			boolean dbResults = db.DelEmployee(id);

			if(!dbResults) {
				finalResults.put("message", "Actions completed successfully.");
			}
			else {
				finalResults.put("error", "No employee id [" + id + "] found.");
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
		}

		return gson.toJson(finalResults);
	}

	@RequestMapping(value = "/api/employee/{id}", method = RequestMethod.PUT, produces = "plain/text")
	public String EditEmployee(@PathVariable("id") int id, @RequestBody() String payload) {
		HashMap<String, String> finalResults = new HashMap<String, String>();

		HashMap<String, String> data = new HashMap<String, String>();
		data = (HashMap<String, String>) gson.fromJson(payload, data.getClass());
		
		try {
			DatabaseManager db = new DatabaseManager();
			boolean dbResults = db.EditEmployee(id, data['name'], data['shift']);

			if(!dbResults) {
				finalResults.put("message", "Actions completed successfully.");
			}
			else {
				finalResults.put("error", "No employee id [" + id + "] found.");
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
		}

		return gson.toJson(finalResults);
	}

	@RequestMapping(value = "/api/employee/", method = RequestMethod.POST, produces = "plain/text")
	public String AddEmployee(@RequestParam("name") String name, @RequestParam("shift") String shift) {
		HashMap<String, String> finalResults = new HashMap<String, String>();
		
		try {
			DatabaseManager db = new DatabaseManager();
			boolean dbResults = db.AddEmployee(name, shift);

			if(!dbResults) {
				finalResults.put("message", "Actions completed successfully.");
			}
			else {
				finalResults.put("error", "Unable to add employee.");
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
		}

		return gson.toJson(finalResults);
	}

	@RequestMapping(value = "/api/debug/undeleteall", method = RequestMethod.GET, produces = "plain/text")
	public String debugUndeleteAll() {
		HashMap<String, String> finalResults = new HashMap<String, String>();
		
		try {
			DatabaseManager db = new DatabaseManager();
			boolean dbResults = db.UndelAll();

			if(!dbResults) {
				finalResults.put("message", "Actions completed successfully.");
			}
			else {
				finalResults.put("error", "Unable to run this action.");
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
		}

		return gson.toJson(finalResults);
	}
	
	@RequestMapping(value = "/api/debug/deleteall", method = RequestMethod.DELETE, produces = "plain/text")
	public String debugDeleteAll() {
		HashMap<String, String> finalResults = new HashMap<String, String>();
		
		try {
			DatabaseManager db = new DatabaseManager();
			boolean dbResults = db.DelAll();

			if(!dbResults) {
				finalResults.put("message", "Actions completed successfully.");
			}
			else {
				finalResults.put("error", "Unable to run this action.");
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
		}

		return gson.toJson(finalResults);
	}
}