package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.manage.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/date")
	public ResponseEntity<String> testDate(){
		String queryCurrentDate = testService.queryCurrentDate();
		return ResponseEntity.ok(queryCurrentDate);
	}
}