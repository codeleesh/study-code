package me.lovethefeel.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

public class CIRedisCache<V> implements MemoryCache<V> {

    private RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> valueOperations;
    private HashOperations<String, String, String> hashOperations;

    public CIRedisCache(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public V get(String index, String key, Type type) throws JsonProcessingException {

        final String restKey = getRestKey("view", index, key);
        return new Gson().fromJson(valueOperations.get(restKey), type);
    }

    @Override
    public String getRestKey(String modelType, String index, String key) {

        String restKey = "";

        if ("view".equals(modelType.toLowerCase())) {
            restKey = String.format("%s:%s", index, key);
        } else {
            restKey = String.format("%s:%s:%s", index, modelType.toLowerCase(), key);
        }

        return restKey;
    }

    @Override
    public void setRestKey(String key, String value) {

        valueOperations.set(key, value);
    }

    @Override
    public void setRestKey(String key, String value, long ttl, TimeUnit unit) {

        valueOperations.set(key, value, ttl, unit);
    }
}
