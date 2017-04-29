package com.projectge.ci346;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.google.gson.Gson;

/**
 * 
 * @author Ashley Scott
 * 
 * The API backend for the employee list web app.
 * Serves the front-end with all the required data. 
 *
 */

@RestController
public class BackEnd {

	Gson gson = new Gson();
	
	/**
	 * GET /api
	 * 
	 * URLParams: none
	 * DataParams: none 
	 * 
	 * API Entry point. Used to test server setup and response.
	 * 
	 * @return String, response JSON feed.
	 */
	@RequestMapping(value = "/api", method = RequestMethod.GET, produces = "plain/text")
	public String GetIndex() {

		HashMap<String, String> finalResults = new HashMap<String, String>();
		finalResults.put("error", "This is the entry route.");
		
		return gson.toJson(finalResults);
	}

	/**
	 * GET /api/employees
	 * 
	 * URLParams: none
	 * DataParams: none 
	 * 
	 * Sends the client a list of ALL (not deleted) employees in the database.
	 * 
	 * @return String, response JSON feed.
	 */
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
					items.put("first_name", e.getFirstName());
					items.put("last_name", e.getLastName());
					items.put("shift_start", e.getShiftStart());
					items.put("shift_end", e.getShiftEnd());
					
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

	/**
	 * DELETE /api/employees/{id}
	 * 
	 * URLParams: int {id}, the employee id
	 * DataParams: none 
	 * 
	 * Deletes a specified employee ID from the database.
	 * 
	 * @return String, response JSON feed.
	 */
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

	/**
	 * THIS IS SUPPOSED TO BE A PUT REQUEST, BUT I DON'T UNDERSTAND HOW TO GET THIS WORKING.
	 * POST WORKS PERFECTLY FINE, HOWEVER. (did try a string payload, but still throwing a 406)
	 * 
	 * POST /api/employees/{id}
	 * 
	 * URLParams: int {id}, the employee id
	 * 	
	 * DataParams:
	 * 	first_name: String, new employee first name
	 *  last_name: String, new employee last name
	 * 	shift_start: String, new employee shift time
	 * 	shift_end: String, new employee shift time
	 * 
	 * Edits a specified employee with new values.
	 * 
	 * @return String, response JSON feed.
	 */
	@RequestMapping(value = "/api/employee/{id}", method = RequestMethod.POST, produces = "plain/text")
	public String EditEmployee(@PathVariable("id") int id, @RequestBody() MultiValueMap<String, String> data) {
		HashMap<String, String> finalResults = new HashMap<String, String>();

		try {
			DatabaseManager db = new DatabaseManager();
			
			boolean dbResults = db.EditEmployee(id, data.getFirst("first_name"), data.getFirst("last_name"), data.getFirst("shift_start"), data.getFirst("shift_end"));

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

	/**
	 * POST /api/employees
	 * 
	 * URLParams: none
	 * 	
	 * DataParams:
	 * 	first_name: String, new employee first name
	 *  last_name: String, new employee last name
	 * 	shift_start: String, new employee shift time
	 * 	shift_end: String, new employee shift time
	 * 
	 * Adds a new employee to the database.
	 * 
	 * @return String, response JSON feed.
	 */
	@RequestMapping(value = "/api/employees", method = RequestMethod.POST, produces = "plain/text")
	public String AddEmployee(@RequestBody() MultiValueMap<String, String> data) {
		HashMap<String, String> finalResults = new HashMap<String, String>();
		
		try {
			DatabaseManager db = new DatabaseManager();

			boolean dbResults = db.AddEmployee(data.getFirst("first_name"), data.getFirst("last_name"), data.getFirst("shift_start"), data.getFirst("shift_end"));

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