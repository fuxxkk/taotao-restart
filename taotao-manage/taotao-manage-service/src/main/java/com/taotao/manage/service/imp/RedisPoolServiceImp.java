package com.taotao.manage.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.manage.service.RedisService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
/**
 * 单机版redis
 * @author Administrator
 *
 */
public class RedisPoolServiceImp implements RedisService{
	
	@Autowired(required=false)
	private JedisPool jedisPool;
	
	private Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	
	@Override
	public String set(String key, String value) {
		
		Jedis jedis = getJedis();
		String result = jedis.set(key,value);
		jedis.close();
		return result;
	}

	@Override
	public String setex(String key, Integer second, String value) {
		Jedis jedis = getJedis();
		String result = jedis.setex(key, second, value);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, Integer second) {
		
		Jedis jedis = getJedis();
		Long expire = jedis.expire(key, second);
		jedis.close();
		return expire;
	}

	@Override
	public String get(String key) {
		
		Jedis jedis = getJedis();
		String string = jedis.get(key);
		jedis.close();
		return string;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = getJedis();
		Long del = jedis.del(key);
		jedis.close();
		return del;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = getJedis();
		Long incr = jedis.incr(key);
		jedis.close();
		return incr;
	}

}
