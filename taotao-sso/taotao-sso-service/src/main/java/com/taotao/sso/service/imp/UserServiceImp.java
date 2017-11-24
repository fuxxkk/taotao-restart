package com.taotao.sso.service.imp;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.rediseService.RedisService;
import com.taotao.sso.mapper.UserMapper;
import com.taotao.sso.pojo.User;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Value("${TICKET_PREFIX}")
	private String ticket_prefix;
	
	@Autowired
	private RedisService redisService;

	@Override
	public Boolean check(String param, Integer type) {
		
		User user = new User();
		switch (type) {
		case 1:
			user.setUsername(param);
			break;
		case 2:
			user.setPhone(param);
			break;
		default:
			user.setEmail(param);
			break;
		}
		
		int count = userMapper.selectCount(user);
		
		return count==0;
	}

	@Override
	public String checkTicket(String ticket) {
		
		String key = ticket_prefix+ticket;
		String result = redisService.get(key);
		if(StringUtils.isNotEmpty(result)) {
			redisService.expire(key, 3600);
		}
		
		return result;
	}
	
	
	
}
