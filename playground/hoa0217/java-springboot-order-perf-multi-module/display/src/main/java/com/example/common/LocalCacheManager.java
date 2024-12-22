package com.example.common;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalCacheManager implements CustomCacheManager {

    private static final String CACHE_NAME = "productsCache";
    private final CacheManager cacheManager;


    public Object get(String key) {
        return getCache().get(key, Object.class);
    }

    public void set(String key, Object value) {
        getCache().put(key, value);
    }

    private Cache getCache() {
        return Objects.requireNonNull(cacheManager.getCache(CACHE_NAME));
    }
}
