package com.projectge.ci346;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackEnd {
	
	@RequestMapping("/")
	public String Index(){
		return "Index";
	}
	
	@RequestMapping("/api")
	public String IndexAPI() {
		return "{}";
	}
	
}