/**
 * 
 */
package com.xceptance.training.map;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;

/**
 * The basic hashmap implementation of the interface SimpleMap. This
 * is not a compatible HashMap or Map implementation, just something
 * for us that is simpler.
 * 
 * @author rschwietzke
 *
 */
public class BasicHashMap<K, V> implements SimpleMap<K, V>
{
    /**
     * Initial size of the hashmap array, should be prime
     */
    private static final int INITIAL_SIZE = 11;
    
    /**
     * Array that holds our data
     */
    private Entry<K, V>[] data;
    
    /**
     * Holds the current data amount aka size
     */
    private int size;
     
    /**
     * Constructor of a default size map
     */
    @SuppressWarnings("unchecked")
    public BasicHashMap()
    {
        data = (BasicHashMap.Entry<K, V>[]) Array.newInstance(Entry.class, INITIAL_SIZE);
    }
    
    @Override
    public V get(K key) throws IllegalArgumentException
    {
        // prevent null from being used
        if (key == null)
        {
            throw new IllegalArgumentException("Get doesn't support null keys");
        }
        
        // where is our entry located
        final int pos = calculatePosition(key);
        
        // get the entry
        Entry<K, V> entry = getEntry(key, pos);
        if (entry == null)
        {
            // does not exist
            return null;
        }
        else
        {
            // we know it, return value
            return entry.value;
        }
    }

    @Override
    public V put(K key, V value) throws IllegalArgumentException
    {
        // prevent null from being used
        if (key == null)
        {
            throw new IllegalArgumentException("Put doesn't support null keys");
        }

        return put(new Entry<K, V>(key, value));
    }
        
    /**
     * Private put for easy reuse.
     * 
     * @param newEntry the new entry data to set
     * @return
     * @throws IllegalArgumentException
     */
    private V put(Entry<K, V> newEntry)
    {
        // check if we have to rehash
        if (data.length / 2 < size)
        {
            rehash();
        }
            
        // where is our entry located
        final int pos = calculatePosition(newEntry.key);
        
        // get the entry
        final Entry<K, V> entry = getEntry(newEntry.key, pos);
        if (entry == null)
        {
            size++;
            
            final Entry<K, V> headEntry = data[pos];
            if (headEntry != null)
            {
                newEntry.next = headEntry;
            }
            data[pos] = newEntry;

            return null;
        }
        else
        {
            final V oldValue = entry.value;
            entry.value = newEntry.value;
            
            return oldValue;
        }
    }

    /**
     * Rehash our current data into a new array
     * that is approx. 2 * oldLength + 1
     */
    private void rehash()
    {
        final Entry<K,V>[] oldData = data;
        data = (BasicHashMap.Entry<K, V>[]) Array.newInstance(Entry.class, 2 * data.length + 1);
    
        size = 0;
        
        for (int i = 0; i < oldData.length; i++)
        {
            Entry<K, V> entry = oldData[i];
            
            while (entry != null)
            {
                final Entry<K, V> old = entry;
                entry = entry.next;
                old.next = null;
                
                put(old);
            }
        }
    }
    
    @Override
    public void putAll(SimpleMap<K, V> map)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public V remove(K key) throws IllegalArgumentException
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public void clear()
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsKey(K key) throws IllegalArgumentException
    {
        // prevent null from being used
        if (key == null)
        {
            throw new IllegalArgumentException("ContainsKey doesn't support null keys");
        }
        
        // where is our entry located
        final int pos = calculatePosition(key);
        
        // get the entry
        return getEntry(key, pos) != null;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public Set<K> keySet()
    {
        // TODO Auto-generated method stub
        return Collections.emptySet();
    }

    @Override
    public int hashCode()
    {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return super.toString();
    }

    /**
     * Determine position
     * 
     * @param key
     * @return the position
     */
    private int calculatePosition(final K key)
    {
        // get hash of the key
        final int hashcode = Math.abs(key.hashCode());
        
        // where is our entry located
        return hashcode % data.length;
    }
    
    /**
     * Determines if we already have an entry for that key,
     * null otherwise
     * 
     * @param key the key to look for
     * @param its position in the array
     * @return
     */
    private Entry<K, V> getEntry(final K key, final int position)
    {
        // get the entry
        Entry<K, V> entry = data[position];
        
        while (entry != null)
        {
            if (entry.key.equals(key))
            {
                return entry;
            }

            entry = entry.next;
        }

        return null;
    }

    
    /**
     * Our container to hold a key-value pair, the
     * key can never be changed again, but the value
     * can be updated.
     */
    private static class Entry<K, V>
    {
        private final K key;
        private V value;
        
        private Entry<K, V> next;
        
        /**
         * Constructor
         * 
         * @param key key to keep
         * @param value value to keep
         */
        public Entry(K key, V value)
        {
            this.key = key;
            this.value = value;
        }
    }
}

