package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.pojo.User;

@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@Value("${cookiename}")
	public  String COOKIE_NAME;
	@Value("${cookiemaxage}")
	public  Integer COOKIE_MAX_AGE;
	
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
	
	
	@RequestMapping(value="doLogin",method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> doLogin(User user,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 500);
		
		try {
			String result = userService.login(user);
			if(StringUtils.isNotEmpty(result)) {
				CookieUtils.setCookie(request, response, COOKIE_NAME, result, COOKIE_MAX_AGE,true);
				map.put("status", 200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(map);
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		
		CookieUtils.deleteCookie(request, response, COOKIE_NAME);
		
		return "redirect:/index.html";
	}
}
