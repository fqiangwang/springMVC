package com.redisUtil;

import org.springframework.beans.factory.InitializingBean;

import com.dev.utils.ApplicationConfig;

import redis.clients.jedis.Jedis;

public class RedisSingleNodeUtil implements InitializingBean {
	
    private  String redisAddress="192.168.2.96";
    
    private  String port="8000";
    
    private  String pass="123";
	
	private   Jedis jedis=null;
	
	
	public  Object getRedisValue(String key){
		return jedis.get(key);
	}
	
	public  void setRedisValue(String key,String obj){
		jedis.del(key);
		jedis.set(key, obj);
	}

	public  String getPort() {
		return port;
	}

	public  void setPort(String port) {
		this.port = port;
	}

	public  String getPass() {
		return pass;
	}

	public  void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		jedis=new Jedis(redisAddress,Integer.parseInt(port));
		jedis.auth(pass);
		// TODO Auto-generated method stub
		
	}

	public String getRedisAddress() {
		return redisAddress;
	}

	public void setRedisAddress(String redisAddress) {
		this.redisAddress = redisAddress;
	}
	
}
