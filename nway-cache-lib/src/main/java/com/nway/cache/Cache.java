package com.nway.cache;


import java.util.*;

public class Cache<K,V> extends LinkedHashMap<K,V> {

    private int MAX_ENTRIES;
    public static final Random generator = new Random();
    private Optional<String> evictionPolicy;
    public enum EvictionPolicyENUM {LRU, MRU}

    public Optional<String> getEvictionPolicy() {
        return evictionPolicy;
    }

    public void setEvictionPolicy(Optional<String> evictionPolicy) {
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
            remove(evictPolicy());
        }
        return false;
    }

    /**
     * return a key to evictPolicy from the cache
     * @return K
     */
    public K evictPolicy()  {
        K[] keys = (K[]) this.keySet().toArray();
        int pivot = keys.length/2;

        if(evictionPolicy.get().equals(EvictionPolicyENUM.LRU.name())) {
            shuffle(keys, 0, pivot-1);  //LRU
            return keys[0];
        }
        else if (evictionPolicy.get().equals(EvictionPolicyENUM.MRU.name())) {
            shuffle(keys,pivot,keys.length);    //MRU
            return keys[keys.length-1];
        }
        else {
            throw new IllegalStateException("Unsupported Operation");
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
    public V getEntry(K key) {
        return get(key);
    }

    /**
     * shuffle the array (uses knuth-yates fisher algo)
     * @param array
     * @param start
     * @param end
     */
    public void shuffle(Object[] array, int start, int end) {
        int n = end;
        while (n > start) {
            int k = generate(n,start);
            Object temp = array[n];
            array[n] = array[k];
            array[k] = temp;
            n--;
        }
    }

    public int getMAX_ENTRIES() {
        return MAX_ENTRIES;
    }

    public void setMAX_ENTRIES(int MAX_ENTRIES) {
        this.MAX_ENTRIES = MAX_ENTRIES;
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