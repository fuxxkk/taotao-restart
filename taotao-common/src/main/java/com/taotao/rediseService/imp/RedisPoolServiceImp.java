package com.taotao.rediseService.imp;


import com.taotao.rediseService.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

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

	@Override
	public Long hset(String key, String field, String value) {
		Jedis jedis = getJedis();
		Long string = jedis.hset(key,field,value);
		jedis.close();
		return string;
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = getJedis();
		String string = jedis.hget(key,field);
		jedis.close();
		return string;
	}

	@Override
	public List<String> hvals(String key) {
		Jedis jedis = getJedis();
		List<String> hvals = jedis.hvals(key);
		jedis.close();
		return hvals;
	}

	@Override
	public Long hdel(String key, String field) {
		Jedis jedis = getJedis();
		Long hdel = jedis.hdel(key, field);
		jedis.close();
		return hdel;
	}

}
