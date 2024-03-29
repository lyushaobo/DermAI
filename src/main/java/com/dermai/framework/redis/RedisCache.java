package com.dermai.framework.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SuppressWarnings(value = { "unchecked", "rawtypes" })
@Component
public class RedisCache {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * Cache basic objects, Integer, String, entity classes, etc.
     *
     * @param key cache key
     * @param value cache value
     */
    public <T> void setCacheObject(final String key, final T value)
    {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * Cache basic objects, Integer, String, entity classes, etc.
     *
     * @param key Cache key
     * @param value Cache value
     * @param timeout Timeout
     * @param timeUnit Time Unit
     */
    public <T> void setCacheObject(final String key, final T value, final Integer timeout, final TimeUnit timeUnit)
    {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * Setting the valid time, default time unit is second
     *
     * @param key Redis cache key
     * @param timeout time out
     * @return true=config success；false=config failed
     */
    public boolean expire(final String key, final long timeout)
    {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * Setting the valid time, default time unit is second
     *
     * @param key Redis cache key
     * @param timeout time out
     * @param unit time unit
     * @return true=config success；false=config failed
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit)
    {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * get expire time
     *
     * @param key Redis键
     * @return valid time
     */
    public long getExpire(final String key)
    {
        return redisTemplate.getExpire(key);
    }

    /**
     * Does the key exists
     *
     * @param key key
     * @return true=exists false=not exists
     */
    public Boolean hasKey(String key)
    {
        return redisTemplate.hasKey(key);
    }

    /**
     * Get cache object
     *
     * @param key Cache key
     * @return Cache Object
     */
    public <T> T getCacheObject(final String key)
    {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * Delete single object
     *
     * @param key
     */
    public boolean deleteObject(final String key)
    {
        return redisTemplate.delete(key);
    }

    /**
     * Delete collection objects
     *
     * @param collection multiple objects
     * @return
     */
    public boolean deleteObject(final Collection collection)
    {
        return redisTemplate.delete(collection) > 0;
    }

    /**
     * List data cache
     *
     * @param key cache key
     * @param dataList List data
     * @return cache object
     */
    public <T> long setCacheList(final String key, final List<T> dataList)
    {
        Long count = redisTemplate.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * Get cache list
     *
     * @param key cache key
     * @return List object
     */
    public <T> List<T> getCacheList(final String key)
    {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    /**
     * Set cache
     *
     * @param key cache key
     * @param dataSet cache data
     * @return Cache set object
     */
    public <T> BoundSetOperations<String, T> setCacheSet(final String key, final Set<T> dataSet)
    {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext())
        {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    /**
     * Get cache set
     *
     * @param key key
     * @return set object
     */
    public <T> Set<T> getCacheSet(final String key)
    {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * Cache Map
     *
     * @param key key
     * @param dataMap Map data
     */
    public <T> void setCacheMap(final String key, final Map<String, T> dataMap)
    {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * Get cache Map
     *
     * @param key key
     * @return map object
     */
    public <T> Map<String, T> getCacheMap(final String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * Set Cache Hash value
     *
     * @param key key
     * @param hKey hash key
     * @param value hash value
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value)
    {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * Get cache hash value
     *
     * @param key key
     * @param hKey hash key
     * @return Hash key value
     */
    public <T> T getCacheMapValue(final String key, final String hKey)
    {
        HashOperations<String, String, T> opsForHash = redisTemplate.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * Get multiple hash key value
     *
     * @param key Redis key
     * @param hKeys Hash key collection
     * @return Hash value list
     */
    public <T> List<T> getMultiCacheMapValue(final String key, final Collection<Object> hKeys)
    {
        return redisTemplate.opsForHash().multiGet(key, hKeys);
    }

    /**
     * Delete hash key value
     *
     * @param key Redis key
     * @param hKey Hash key
     * @return delete status
     */
    public boolean deleteCacheMapValue(final String key, final String hKey)
    {
        return redisTemplate.opsForHash().delete(key, hKey) > 0;
    }

    /**
     * Finds the matching keys
     *
     * @param pattern prefix
     * @return key collection
     */
    public Collection<String> keys(final String pattern)
    {
        return redisTemplate.keys(pattern);
    }
}
