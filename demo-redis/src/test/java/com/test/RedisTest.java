package com.test;

import static org.junit.Assert.*;

import java.rmi.server.ExportException;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisTest {

	@Test
	public void test() {
		
		Jedis jedis = new Jedis("192.168.12.168",6379);
		jedis.set("test", "123321");
		jedis.expire("test", 10);
		String string = jedis.get("test");
		
		System.out.println(string);
		
	}
	
	@Test
	public void testPool() {
		JedisPool jedisPool = new JedisPool("192.168.12.168",6379);
		Jedis redis = jedisPool.getResource();
		redis.set("test111", "11111");
		redis.expire("test111", 15);
		System.out.println(redis.get("test111"));
	}

}
