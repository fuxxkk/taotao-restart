package com.taotao.sso.service.imp;

import java.util.Date;
import java.util.List;

import com.taotao.sso.mapper.UserMapper;
import com.taotao.sso.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.rediseService.RedisService;
import com.taotao.sso.pojo.User;

@Service
public class UserServiceImp implements UserService {
//	/111111
	@Autowired
	private UserMapper userMapper;
	
	@Value("${TICKET_PREFIX}")
	private String ticket_prefix;
	@Value("${expire}")
	private Integer expireTime;
	
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
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
			redisService.expire(key, expireTime);
		}
		
		return result;
	}

	@Override
	public void saveUser(User user) {	
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userMapper.insertSelective(user);
	}

	@Override
	public String login(User user) throws Exception {
		
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		List<User> result = userMapper.select(user);
		if(result!=null&&result.size()>0) {
			User tmp = result.get(0);
			String resultStr = MAPPER.writeValueAsString(tmp);
			String md5Hex = DigestUtils.md5Hex(user.getUsername()+System.currentTimeMillis());
			redisService.setex(ticket_prefix+md5Hex, expireTime, resultStr);
			return md5Hex;
		}
		return null;
	}
	
	
	
}
