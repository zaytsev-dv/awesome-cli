package com.awesome.cli.application.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CacheStore<T> {
    private final Cache<CacheKey, T> cache;

    public CacheStore() {
        cache = CacheBuilder.newBuilder()
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .build();
    }

    public T get(CacheKey key) {
        return cache.getIfPresent(key);
    }

    public void add(CacheKey key, T value) {
        if (key != null && value != null) {
            cache.put(key, value);
            log.info("Record stored in "
                    + value.getClass().getSimpleName()
                    + " Cache with Key = " + key);
        }
    }
}
