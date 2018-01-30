package com.redisUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisPoolConfig;

public class JedisClusterFactory implements FactoryBean<JedisCluster>,InitializingBean {

	private Resource addressConfig;
	private String addressKeyPrefix;
	private JedisCluster jedisCluster;
	private Integer timeout;
	private Integer maxRedirections;
    private GenericObjectPoolConfig genericObjectPoolConfig;
    private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
    
	public Resource getAddressConfig() {
		return addressConfig;
	}
	
	public void setAddressConfig(Resource addressConfig) {
		this.addressConfig = addressConfig;
	}

	public String getAddressKeyPrefix() {
		return this.addressKeyPrefix;
	}

	public void setAddressKeyPrefix(String addressKeyPrefix) {
		this.addressKeyPrefix = addressKeyPrefix;
	}

	public JedisCluster getJedisCluster() {
		return this.jedisCluster;
	}
	

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}

	public Integer getMaxRedirections() {
		return maxRedirections;
	}

	public void setMaxRedirections(Integer maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public GenericObjectPoolConfig getGenericObjectPoolConfig() {
		return genericObjectPoolConfig;
	}

	public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return this.jedisCluster != null?this.jedisCluster.getClass():JedisCluster.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Set haps=this.parseHostAndPort();
		jedisCluster=new JedisCluster(haps,this.timeout.intValue(),this.maxRedirections.intValue(),
				this.genericObjectPoolConfig);
		
	}
	

	private Set<HostAndPort> parseHostAndPort() throws Exception{
		try{
			Properties pr=new Properties();
			pr.load(this.addressConfig.getInputStream());
			HashSet haps=new HashSet();
			Iterator it=pr.keySet().iterator();
			while(it.hasNext()){
				Object key = it.next();
                if(((String)key).startsWith(this.addressKeyPrefix)) {
                    String val = (String)pr.get(key);
                    boolean isIpPort = this.p.matcher(val).matches();
                    if(!isIpPort) {
                        throw new IllegalArgumentException("ip 或 port 不合法");
                    }

                    String[] ipAndPort = val.split(":");
                    HostAndPort hap = new HostAndPort(ipAndPort[0], Integer.parseInt(ipAndPort[1]));
                    haps.add(hap);
                }
			}
			return haps;
		}
		catch (IllegalArgumentException ex) {
            throw ex;
		}
		catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("解析 jedis 配置文件失败", ex);
		}
	}
	
	
}
