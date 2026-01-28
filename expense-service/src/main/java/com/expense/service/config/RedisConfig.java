package com.expense.service.config;

import java.time.Duration;
import java.util.Objects;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableCaching
@Slf4j
@ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis")
public class RedisConfig implements CachingConfigurer {

        @Bean
        public RedisCacheManager cacheManager(@NonNull RedisConnectionFactory connectionFactory) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                                ObjectMapper.DefaultTyping.NON_FINAL,
                                JsonTypeInfo.As.PROPERTY);

                Duration ttl = Objects.requireNonNull(Duration.ofMinutes(10));

                RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(ttl)
                                .serializeKeysWith(
                                                RedisSerializationContext.SerializationPair
                                                                .fromSerializer(new StringRedisSerializer()))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                                .fromSerializer(new GenericJackson2JsonRedisSerializer(mapper)))
                                .disableCachingNullValues();

                return RedisCacheManager.builder(connectionFactory)
                                .cacheDefaults(config)
                                .build();
        }

        @Override
        @NonNull
        public CacheErrorHandler errorHandler() {
                return new CacheErrorHandler() {
                        @Override
                        public void handleCacheGetError(@NonNull RuntimeException exception, @NonNull Cache cache,
                                        @NonNull Object key) {
                                log.warn("Redis unreachable - bypassing cache for GET. Key: {}", key);
                        }

                        @Override
                        public void handleCachePutError(@NonNull RuntimeException exception, @NonNull Cache cache,
                                        @NonNull Object key, @Nullable Object value) {
                                log.warn("Redis unreachable - bypassing cache for PUT. Key: {}", key);
                        }

                        @Override
                        public void handleCacheEvictError(@NonNull RuntimeException exception, @NonNull Cache cache,
                                        @NonNull Object key) {
                                log.warn("Redis unreachable - bypassing cache for EVICT. Key: {}", key);
                        }

                        @Override
                        public void handleCacheClearError(@NonNull RuntimeException exception, @NonNull Cache cache) {
                                log.warn("Redis unreachable - bypassing cache for CLEAR.");
                        }
                };
        }
}
