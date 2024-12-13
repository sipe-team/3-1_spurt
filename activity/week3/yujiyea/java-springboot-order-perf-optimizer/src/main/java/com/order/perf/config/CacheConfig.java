package com.order.perf.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.DiskStoreConfiguration;
import net.sf.ehcache.config.PersistenceConfiguration;
//import org.ehcache.CacheManager;
//import org.ehcache.config.builders.CacheConfigurationBuilder;
//import org.ehcache.config.builders.CacheManagerBuilder;
//import org.ehcache.config.builders.ExpiryPolicyBuilder;
//import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
//    @Bean
//    public CaffeineCacheManager cacheManager() {
//        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
//        cacheManager.setCaffeine(Caffeine.newBuilder()
//                .initialCapacity(100) // 초기 용량
//                .maximumSize(500)     // 최대 캐시 크기
//                .expireAfterWrite(10, TimeUnit.MINUTES) // 캐시 만료 시간
//                .recordStats());      // 통계 활성화
//        return cacheManager;
//    }

//    @Bean
//    public CacheManager ehCacheCacheManager() {
//        org.ehcache.config.CacheConfiguration<Long, Object> cacheConfiguration =
//                CacheConfigurationBuilder.newCacheConfigurationBuilder(
//                                Long.class, Object.class,
//                                ResourcePoolsBuilder.heap(500))
//                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(10)))
//                        .build();
//
//        org.ehcache.CacheManager ehcacheManager = CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("orders", cacheConfiguration)
//                .build(true);
//
//        return ehcacheManager;
//    }
    @Bean
    public EhCacheCacheManager ehCacheCacheManager() {
        net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();

        net.sf.ehcache.CacheManager manager = net.sf.ehcache.CacheManager.create(configuration);

        CacheConfiguration getCacheConfig = new CacheConfiguration()
                .maxEntriesLocalHeap(500)
                .maxEntriesLocalDisk(500)
                .eternal(false)
                .timeToIdleSeconds(1800)
                .timeToLiveSeconds(1800)
                .memoryStoreEvictionPolicy("LRU")
                .transactionalMode(CacheConfiguration.TransactionalMode.OFF)
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.LOCALTEMPSWAP))
                .name("orders");
        Cache getAuthenticatedMenuByUriCache = new Cache(getCacheConfig);
        manager.addCache(getAuthenticatedMenuByUriCache);

        return new EhCacheCacheManager(manager);
    }
}
