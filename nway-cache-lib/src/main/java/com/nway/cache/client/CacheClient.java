package com.nway.cache.client;

import com.nway.cache.Cache;

public class CacheClient<K,V> extends Cache<K,V> {

    private Cache clientCache;
    private K key;

    public CacheClient() {
        clientCache = new Cache(16, 0.75f);
    }

    /**
     * Method can be overriden to allow for custom eviction policy
     * @return
     */
    @Override
    public K evict() {
        return key;
    }
}
