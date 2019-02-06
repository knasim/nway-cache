package com.nway.cache;


import java.util.*;

public class Cache<K,V> extends LinkedHashMap<K,V> {

    private boolean isEvict = false;
    private int MAX_ENTRIES = 5;
    public static final Random generator = new Random();
    private String evictionPolicy;

    enum EvictionPolicyENUM {
        LRU, MRU
    }

    public String getEvictionPolicy() {
        return evictionPolicy;
    }

    public void setEvictionPolicy(String evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    public Cache() {}

    public  Cache(int initialCapacity, float loadFactor) {
        super(initialCapacity,loadFactor,true);
        Collections.synchronizedMap(this);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        int cap = this.size();
        if(size() > MAX_ENTRIES) {
            remove(evict());
        }
        return false;
    }

    /**
     * return a key to evict from the cache
     * @return K
     */
    public K evict() {
        K[] keys = (K[]) this.keySet().toArray();
        int offset = keys.length/2;
        if(evictionPolicy.equals(EvictionPolicyENUM.LRU.name())) {
            shuffle(keys, 0, offset-1);  //LRU
            return keys[0];
        }
        else {
            shuffle(keys,offset,keys.length);    //MRU
            return keys[keys.length-1];
        }
    }

    /**
     * put a key into the cache
     * @param node
     */
    public void putEntry(CacheEntry node) {
        K key = (K) node.key;
        V value = (V) node.value;
        put(key,value);
    }

    /**
     * get a value from cache
     * @param key
     * @return
     */
    public synchronized V getItem (K key) {
        return get(key);
    }


    /**
     * shuffle the array
     * @param array
     * @param start
     * @param end
     */
    public void shuffle(Object[] array, int start, int end) {
        int n = end;
        while (n > start) {
            //int k = generator.nextInt(n--); //decrements after using the value
            int k = generate(n,start);
            Object temp = array[n];
            array[n] = array[k];
            array[k] = temp;
            n--;
        }
    }

    /**
     * RGN
     * @param n
     * @param start
     * @return
     */
    public int generate(int n, int start) {
        int k = 0;
        int temp = n;
        if(start == 0) {
            k = generator.nextInt(n--);
        }
        else {
            while(k < start) {
                k = generator.nextInt(n--); //decrements after using the value
                if(n == 0)
                    n=temp;
            }
        }

        return k;
    }
}

