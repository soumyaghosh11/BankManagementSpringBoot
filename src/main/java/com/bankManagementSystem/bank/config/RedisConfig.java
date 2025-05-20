package com.bankManagementSystem.bank.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import com.bankManagementSystem.bank.model.User;

@Configuration
public class RedisConfig {
	
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
		.entryTtl(Duration.ofMinutes(10))
		.disableCachingNullValues()
		.serializeValuesWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new Jackson2JsonRedisSerializer<>(User.class)));
		
		return RedisCacheManager.builder(connectionFactory)
				.cacheDefaults(redisCacheConfiguration)
				.build();
	}

}
