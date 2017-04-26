package com.projectge.ci346;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackEnd {

	@RequestMapping(value = "/api", method = RequestMethod.GET)
	public String GetIndex() {
		return "{\"error\": 0, \"message\": \"Welcome to the server, you issued a GET request.\"}";
	}

	@RequestMapping(value = "/api", method = RequestMethod.POST)
	public String PostIndex() {
		return "{\"error\": 0, \"message\": \"Welcome to the server, you issued a POST request.\"}";
	}

	@RequestMapping(value = "/api", method = RequestMethod.PUT)
	public String PutIndex() {
		return "{\"error\": 0, \"message\": \"Welcome to the server, you issued a PUT request.\"}";
	}
	
	@RequestMapping(value = "/api", method = RequestMethod.DELETE)
	public String DeleteIndex() {
		return "{\"error\": 0, \"message\": \"Welcome to the server, you issued a DELETE request.\"}";
	}
	
}