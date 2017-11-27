package com.taotao.sso.service;

import com.taotao.sso.pojo.User;

public interface UserService {
	
	Boolean check(String param,Integer type);
	
	String checkTicket(String ticket);
	
	void saveUser(User user);

	String login(User user) throws Exception;
}
