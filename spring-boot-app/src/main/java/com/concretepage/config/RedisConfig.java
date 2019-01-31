package com.concretepage.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
//@ComponentScan("com.concretepage")
public class RedisConfig {

	 @Bean
	    public JedisConnectionFactory redisConnectionFactory() {
	        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
	        jedisConnectionFactory.setUsePool(true);
	        jedisConnectionFactory.setHostName("localhost");
	        jedisConnectionFactory.setPort(6379);
	        return jedisConnectionFactory;
	    }
	    @Bean
	    public RedisSerializer redisStringSerializer() {
	        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
	        return stringRedisSerializer;
	    }
	    @Bean(name="redisTemplate")
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf,RedisSerializer redisSerializer) {
	        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
	        redisTemplate.setConnectionFactory(cf);
	        redisTemplate.setDefaultSerializer(redisSerializer);
	        return redisTemplate;
	    }
//	    @Bean
//	    public CacheManager cacheManager() {
//	        return new RedisCacheManager(redisTemplate(redisConnectionFactory(),redisStringSerializer()));
//	    }
}