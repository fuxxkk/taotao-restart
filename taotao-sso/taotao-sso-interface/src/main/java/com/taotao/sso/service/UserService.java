package com.taotao.sso.service;

public interface UserService {
	
	Boolean check(String param,Integer type);
	
	String checkTicket(String ticket);
}
