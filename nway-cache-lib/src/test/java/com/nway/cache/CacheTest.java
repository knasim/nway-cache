package com.nway.cache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CacheTest<K,V> {

    private Cache<K,V> cache;
    private CacheEntry cacheEntry1;
    private CacheEntry cacheEntry2;
    private CacheEntry cacheEntry3;
    private CacheEntry cacheEntry4;
    private CacheEntry cacheEntry5;
    private CacheEntry cacheEntry6;

    @Before
    public void setUp() {
        cache = new Cache(5,75f);
        cache.setEvictionPolicy(Cache.EvictionPolicyENUM.LRU.name());

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
        cacheEntry2.value="Alexandria"; cacheEntry3.value="Chantilly";
        cacheEntry4.value="Fairfax"; cacheEntry5.value="Tysons";
        cacheEntry6.value="Queens";
    }

    @After
    public void tearDown() {
        cache=null;
        cacheEntry1=null;
        cacheEntry2=null;
        cacheEntry3=null;
        cacheEntry4=null;
        cacheEntry5=null;
        cacheEntry6=null;
    }

    @Test
    public void removeEldestEntry() {

    }

    @Test
    public void evict() {

    }

    @Test
    public void putEntry() {
        K key  = (K) cacheEntry1.key; V value  = (V) cacheEntry1.value;
        K key2 = (K) cacheEntry2.key; V value2 = (V) cacheEntry2.value;
        K key3 = (K) cacheEntry3.key; V value3 = (V) cacheEntry3.value;
        K key4 = (K) cacheEntry4.key; V value4 = (V) cacheEntry4.value;
        K key5 = (K) cacheEntry5.key; V value5 = (V) cacheEntry5.value;
        K key6 = (K) cacheEntry6.key; V value6 = (V) cacheEntry6.value;
        cache.put(key,value);
        cache.put(key2,value2);
        cache.put(key3, value3);
        cache.put(key4,value4);
        cache.put(key5,value5);
        cache.put(key6,value6);
        Assert.assertNotEquals(cache.size(),0);
    }

    @Test
    public void getItem() {
        //Assert.assertNotSame(cache.getItem(cacheEntry1.key),null);
    }

    @Test
    public void inPlaceShuffle() {
    }

    @Test
    public void generate() {
    }
}