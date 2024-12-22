package com.example.config;

public interface CustomCacheManager {

    Object get(String key);

    void set(String key, Object value);
}
