package com.taotao.rediseService;


public interface RedisService {
	
	public String set(String key, String value);

	public String setex(String key, Integer second, String value);

	public Long expire(String key, Integer second);
	
	public String get(String key);
	
	public Long del(String key);
	
	public Long incr(String key);
}
