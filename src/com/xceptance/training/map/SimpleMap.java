package com.xceptance.training.map;

import java.util.Set;

/**
 * Interface for map-like data structures that behave similarly
 * to known implementations such as HashMap.
 * <p>
 * This interface is not to be confused with java.util.Map despite being
 * similar it is not fully comparable due to the lack of method coverage.
 *
 * @author René Schwietzke
 */
public interface SimpleMap<K, V>
{
    /**
     * Initial size of the hashmap array, should be prime
     */
    public static final int INITIAL_SIZE = 11;
    
    /**
     * Rehashing threshold in percent
     */
    public static final double REHASHING_THRESHOLD = 0.5;
    
    /**
     * Returns the stored value of a key if it exists, null otherwise. 
     * Null-keys are not permitted, an exception is thrown when a null
     * key is passed.
     *
     * @param key the key to look for, null not permitted
     * @returns the found value or null if not found
     * 
     * @exception IllegalArgumentException key was null
     */
    public V get(K key) throws IllegalArgumentException;
    
    /**
     * Puts the value under this key into the map. Null keys are not 
     * permitted, null values are permitted. 
     * 
     * @param key the key to use, not null
     * @param value the value to store, null permitted
     * @return returns null of the key was not yet in the map, 
     *         otherwise the old value
     * 
     * @exception IllegalArgumentException key was null
     */
    public V put(K key, V value) throws IllegalArgumentException;
    
    /**
     * Adds all content of map to this map. If the key already exists,
     * it will be overwritten like put(K, V). Null as map is permitted.
     * 
     * @param map a map with key and values, can be null and empty of course
     */
    public void putAll(SimpleMap<K, V> map);
    
    /**
     * Removes the key from the map and returns the previously stored value.
     * If the key does not exist, null is returned. 
     * 
     * @param key the key to remove
     * @return the stored value or null if the key did not exit
     *
     * @exception IllegalArgumentException key was null
     */
    public V remove(K key) throws IllegalArgumentException;

    /**
     * Returns the numbers of keys in the map, 0 if the map is empty
     * 
     * @return the number of keys in the map
     */
    public int size();
    
    /**
     * Clears the map, the new size is 0
     */
    public void clear();
    
    /**
     * Checks if the key is in the map, false if the key does not 
     * exists. Null keys are not permitted.
     * 
     * @param key the key to look up
     * @return true if the key was in the map, false otherwise
     * 
     * @exception IllegalArgumentException key was null
     */
    public boolean containsKey(K key) throws IllegalArgumentException;

    /**
     * Returns true if the map is empty, false otherwise.
     * 
     * @return true if the map is empty aka size 0, false otherwise
     */
    public boolean isEmpty();
    
    /**
     * Returns a set of all keys in the map. For an empty 
     * map, we return an empty set.
     * 
     * @return a set of all keys
     */
    public Set<K> keySet();
}
