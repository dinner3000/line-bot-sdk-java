package com.example.bot.spring.echo;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class TemporaryStorage implements DisposableBean {

    private final String cacheName = "newFollowerStorage";

    private CacheManager cacheManager;

    private Cache<Long, Object> cache;

    public TemporaryStorage() {

        cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache(cacheName,
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                    Long.class, Object.class, ResourcePoolsBuilder.heap(10)
                )
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(10)))
            ).build();
        cacheManager.init();

        cache = cacheManager.getCache(cacheName, Long.class, Object.class);

    }

    public Object get(Long key) {
        return cache.get(key);
    }

    public void put(Long key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void destroy() throws Exception {
        cacheManager.removeCache(cacheName);
        cacheManager.close();
    }
}
