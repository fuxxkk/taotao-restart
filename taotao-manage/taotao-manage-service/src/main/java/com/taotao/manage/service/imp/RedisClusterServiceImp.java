package com.taotao.manage.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.manage.service.RedisService;

import redis.clients.jedis.JedisCluster;

public class RedisClusterServiceImp implements RedisService {
	
	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setex(String key, Integer second, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long expire(String key, Integer second) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
