/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fs.maintenance.common;

/**
 *
 * @author Fatih
 * @param <K> key
 * @param <V> value
 */
public class Pair<K, V> {
        
    private final K key;
    
    private final V value;
    
    /**
     * 
     * @param k
     * @param v 
     */
    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    /**
     * 
     * @return 
     */
    public K getKey() {
        return key;
    }

    /**
     * 
     * @return 
     */
    public V getValue() {
        return value;
    }   
}
