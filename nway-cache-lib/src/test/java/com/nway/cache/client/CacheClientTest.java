package com.nway.cache.client;

import com.nway.cache.CacheEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class CacheClientTest<K,V> {
    private CacheClient<K,V> client;
    private CacheEntry cacheEntry1;
    private CacheEntry cacheEntry2;
    private CacheEntry cacheEntry3;
    private CacheEntry cacheEntry4;
    private CacheEntry cacheEntry5;
    private CacheEntry cacheEntry6;

    @Before
    public void setUp() {
        client = new CacheClient<>();
        //client.setEvictionPolicy(Cache.EvictionPolicyENUM.LRU.name());

        cacheEntry1 = new CacheEntry();
        cacheEntry2 = new CacheEntry();
        cacheEntry3 = new CacheEntry();
        cacheEntry4 = new CacheEntry();
        cacheEntry5 = new CacheEntry();
        cacheEntry6 = new CacheEntry();

        cacheEntry1.key=112;
        cacheEntry2.key=114;
        cacheEntry3.key=115;
        cacheEntry4.key=116;
        cacheEntry5.key=117;
        cacheEntry6.key=118;
        cacheEntry1.value="Brooklyn";
        cacheEntry2.value="Alexandria";
        cacheEntry3.value="Chantilly";
        cacheEntry4.value="Fairfax";
        cacheEntry5.value="Tysons";
        cacheEntry6.value="Queens";
        client.put((K) cacheEntry1.key, (V)cacheEntry1.value);
        client.put((K) cacheEntry2.key, (V)cacheEntry2.value);
        client.put((K) cacheEntry3.key, (V)cacheEntry3.value);
        client.put((K) cacheEntry4.key, (V)cacheEntry4.value);
        client.put((K) cacheEntry5.key, (V)cacheEntry5.value);
        client.put((K) cacheEntry6.key, (V)cacheEntry6.value);
    }

    @After
    public void tearDown() {
        client =null;
        cacheEntry1=null;
        cacheEntry2=null;
        cacheEntry3=null;
        cacheEntry4=null;
        cacheEntry5=null;
        cacheEntry6=null;
    }

    @Test
    public void evict() {
        client.evictPolicy();
    }
}