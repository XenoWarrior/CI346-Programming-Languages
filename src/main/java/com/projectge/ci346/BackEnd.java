package com.projectge.ci346;

import com.projectge.*; // Some errors show up if this is not imported, but it works regardless...

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.google.gson.Gson;

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
			
			ArrayList<EmployeeData> dbResults = db.getEmployees();
			if(dbResults != null) {
				for (EmployeeData e : dbResults) {
					HashMap<String, String> items = new HashMap<String, String>();

					items.put("id", String.valueOf(e.getID()));
					items.put("fullname", e.getFullName());
					items.put("shift", e.getShift());
					
					finalResults.put(String.valueOf(finalResults.size()), gson.toJson(items));
				}
				
				return gson.toJson(finalResults);
			}
			else {
				finalResults.put("error", "No results found.");
				
				return gson.toJson(finalResults);
			}
		}
		catch(Exception e) {
			finalResults.put("error", "Exception Caught: " + e.getLocalizedMessage());
			
			return gson.toJson(finalResults);
		}
	}
	
	@RequestMapping(value = "/api/employee/{id}", method = RequestMethod.GET, produces = "plain/text")
	public String GetEmployee(@PathVariable("id") int id) {
		DatabaseManager db = new DatabaseManager();
		
		ArrayList<EmployeeData> dbResults = db.getEmployee(id);
        Map<String, String> finalResults = new HashMap<String, String>();

		if(dbResults != null) {
			finalResults.put("error", "No results found.");
			
			return gson.toJson(finalResults);
		}
		else {
			finalResults.put("message", "No results found.");
			
			return gson.toJson(finalResults);
		}
	}
}