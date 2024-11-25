package com.order.perf.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CaffeineCacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .initialCapacity(100) // 초기 용량
                .maximumSize(500)     // 최대 캐시 크기
                .expireAfterWrite(1, TimeUnit.HOURS) // 캐시 만료 시간
                .recordStats());      // 통계 활성화
        return cacheManager;
    }
}
