package com.nway.cache.client;

import com.nway.cache.Cache;

public class CacheClient<K,V> extends Cache<K,V> {

    /**
     * Client custom eviction policy
     * @return
     */
    @Override
    public  K evictPolicy() {
        return null;
    }
}
