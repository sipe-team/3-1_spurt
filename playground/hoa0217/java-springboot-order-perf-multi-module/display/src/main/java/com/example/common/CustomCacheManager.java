package com.example.common;

public interface CustomCacheManager {

    Object get(String key);

    void set(String key, Object value);
}
