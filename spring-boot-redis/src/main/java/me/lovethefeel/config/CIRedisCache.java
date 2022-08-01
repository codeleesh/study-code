package me.lovethefeel.config;

import com.google.gson.Gson;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

@Component
public class CIRedisCache<V> implements MemoryCache<V> {

    private RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> valueOperations;
    private HashOperations<String, String, String> hashOperations;

    public CIRedisCache(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public V get(final String key, final Type type) {

        return new Gson().fromJson(valueOperations.get(key), type);
    }

    @Override
    public void set(final String key, final String value) {

        valueOperations.set(key, value);
    }

    @Override
    public void setRestKey(final String key, final String value, final long ttl, final TimeUnit unit) {

        valueOperations.set(key, value, ttl, unit);
    }

    @Override
    public void delete(final String key) {
        valueOperations.getAndDelete(key);
    }
}
