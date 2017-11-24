package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="doRegister",method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doRegister(User user){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 500);
		
		try {
			userService.saveUser(user);
			map.put("status", 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(map);
	}
	
}
