package me.lovethefeel.config;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public interface MemoryCache<V> {

    String getRestKey(String modelType, String index, String key);
    void setRestKey(String key, String value);
    void setRestKey(String key, String value, long ttl, TimeUnit unit);
    V get(String index, String key, Type type) throws JsonProcessingException;
}