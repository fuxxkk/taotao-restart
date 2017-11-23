package com.test;

import static org.junit.Assert.*;

import java.rmi.server.ExportException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
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
	
	@Test
	public void testCluster() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.12.168", 7001));
		nodes.add(new HostAndPort("192.168.12.168", 7002));
		nodes.add(new HostAndPort("192.168.12.168", 7003));
		nodes.add(new HostAndPort("192.168.12.168", 7004));
		nodes.add(new HostAndPort("192.168.12.168", 7005));
		nodes.add(new HostAndPort("192.168.12.168", 7006));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		jedisCluster.set("test", "123321");
		jedisCluster.expire("test", 60);
		
		System.out.println(jedisCluster.get("test"));
	}
}
