package me.lovethefeel.config;

import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

@Component
public interface MemoryCache<V> {

    V get(final String key, final Type type);
    void set(final String key, final String value);
    void setRestKey(final String key, final String value, final long ttl, final TimeUnit unit);
    void delete(final String key);
}