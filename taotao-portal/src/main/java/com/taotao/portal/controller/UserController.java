package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.sso.pojo.User;

@RequestMapping("/user")
@Controller
public class UserController {

	@RequestMapping(value="doRegister",method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doRegister(User user){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 500);
		
		return ResponseEntity.ok(map);
	}
	
}
