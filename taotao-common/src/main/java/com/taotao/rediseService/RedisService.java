package com.taotao.rediseService;


import java.util.List;

public interface RedisService {
	
	public String set(String key, String value);

	public String setex(String key, Integer second, String value);

	public Long expire(String key, Integer second);
	
	public String get(String key);
	
	public Long del(String key);
	
	public Long incr(String key);

	public Long hset(String key, String field, String value);

	public String hget(String key, String field);

	public List<String> hvals(String key);

	public Long hdel(String key, String field);
}
