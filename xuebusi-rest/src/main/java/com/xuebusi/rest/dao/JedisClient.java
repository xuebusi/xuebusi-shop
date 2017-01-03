package com.xuebusi.rest.dao;

/**
 * 定义一个redis的操作接口,底层使用jedis.
 * 它有两种实现:一种是单机版的jedis客户端,一种是集群版的jedis客户端.
 * <p>Title: JedisClient</p>
 * <p>Description: </p>
 * <p>Company: www.xuebusi.com</p> 
 * @author	学步思
 * @date	2017年1月2日下午4:32:23
 * @version 1.0
 */
public interface JedisClient {

	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long incr(String key);
	long expire(String key, int second);
	long ttl(String key);
	long del(String key);
	long hdel(String hkey, String key);
	
}
