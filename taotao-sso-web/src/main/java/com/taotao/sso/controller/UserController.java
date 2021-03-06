package com.taotao.sso.controller;


import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 判断类型是否存在
	 * @param param
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/check/{param}/{type}",method = RequestMethod.GET)
	public ResponseEntity<String> check(@PathVariable("param")String param,@PathVariable("type")Integer type,
			@RequestParam(value="callback",required=false)String callback){
		
		String result = "false";
		try {
			if(type<5&&type>0) {
				Boolean check = userService.check(param, type);
				result = check.toString();
				if(StringUtils.isNotBlank(callback)) {
					result=callback+"("+result+")";
				}
				
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(400).body(null);
	}
	
	@RequestMapping(value = "/{ticket}",method = RequestMethod.GET)
	public ResponseEntity<String> checkTickcet(@PathVariable("ticket")String ticket,
			@RequestParam(value="callback",required=false)String callback){
		
		try {
			if (StringUtils.isNotEmpty(ticket)) {
				String result = userService.checkTicket(ticket);
				
				if(StringUtils.isNotEmpty(callback)) {
					result=callback+"("+result+")";
				}
				
				return ResponseEntity.ok(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
	
}
