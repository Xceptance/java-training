package test.com.xceptance.training.rschwietzke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.xceptance.training.map.BasicHashMap;


public class BasicHashMapTest
{
    /**
     * Create a default map that should be empty
     */
    @Test 
    public void createEmptyMap()
    {
        BasicHashMap<?, ?> map = new BasicHashMap<>();
        
        Assert.assertEquals("Map wasn't empty", 0, map.size());
        Assert.assertTrue("Map was not empty", map.isEmpty());
        
        Set<?> entries = map.keySet();
        Assert.assertTrue("Found entries in an expected empty map", entries.isEmpty());
    }
    
    /**
     * Basic happy path for a hashmap
     */
    @Test
    public void happyPath()
    {
        BasicHashMap<Integer, Integer> map = new BasicHashMap<>();
        final Integer result = map.put(1, 2); // important
        
        Assert.assertNull(result);
        Assert.assertEquals(1, map.size());
        
        final Integer finalResult = map.get(1); // important
        
        Assert.assertEquals(new Integer(2), finalResult);
    }
    
    /**
     * A key already exists with a value
     */
    @Test
    public void keyExists()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        map.put("key", "value");
        final String oldValue = map.put("key", "newValue");
        
        Assert.assertEquals("value", oldValue);
        Assert.assertEquals(1, map.size());
        
        final String newValue = map.get("key");
        
        Assert.assertEquals("newValue", newValue);
    }
    
    /**
     * Key does not exist
     */
    @Test
    public void keyDoesNotExist()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        final String value1 = map.get("foo");
        map.put("foo2", "fff");

        final String value2 = map.get("foo");

        Assert.assertNull(value1);
        Assert.assertNull(value2);
    }
    
    /**
     * Null key for get
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullKeyGet()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        map.get(null);
    }
 
    /**
     * Null key for put
     */
    @Test
    public void nullKeyPut()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        
        try
        {
            map.put(null, "kdhgd");
            Assert.fail();
        }
        catch(IllegalArgumentException e)
        {
        }
    }
    
    /**
     * Verify that we can store objects with same hashCode
     * but being different.
     */
    @Test
    public void identicalHashCodeButDifferentKey()
    {
        final BasicHashMap<HashCheater, String> map = new BasicHashMap<>();
        final HashCheater c1 = new HashCheater(1);
        final HashCheater c2 = new HashCheater(1);
        
        map.put(c1, "c1");
        map.put(c2, "c2");
        
        Assert.assertEquals("c1", map.get(c1));
        Assert.assertEquals("c2", map.get(c2));
        
        Assert.assertEquals(2, map.size());
    }

    /**
     *  "put" on larger single hashcode list with existing key
     */
    @Test
    public void largeListOfSameHashCodeButDifferentKey()
    {
        final BasicHashMap<HashCheater, String> map = new BasicHashMap<>();
        
        int count = 11;
        final List<HashCheater> list = new ArrayList<>();
        
        // put
        for (int i = 0; i < count; i++)
        {
            final HashCheater c = new HashCheater(42, "c" + i);
            list.add(c);
            map.put(c, "c" + i);
        }
        Assert.assertEquals(count, map.size());

        // order of add
        for (HashCheater c : list)
        {
            Assert.assertEquals(c.value, map.get(c));    
        }
        
        // reversed order
        Collections.reverse(list);
        for (HashCheater c : list)
        {
            Assert.assertEquals(c.value, map.get(c));    
        }

        // random order
        Collections.shuffle(list);
        for (HashCheater c : list)
        {
            Assert.assertEquals(c.value, map.get(c));    
        }

        Assert.assertEquals(count, map.size());
    }
    
    /**
     * Get for value with entry at this position but not matching key
     */
    @Test
    public void positionAkaHashCodeExistsButNotKey()
    {
        final BasicHashMap<HashCheater, String> map = new BasicHashMap<>();
        
        final HashCheater c1 = new HashCheater(1);
        final HashCheater c2 = new HashCheater(1);
        final HashCheater c3 = new HashCheater(1);
        
        map.put(c1, "c1");
        map.put(c2, "c2");  
        
        Assert.assertEquals(2, map.size());
        Assert.assertNull(map.get(c3));
    }

    
    /**
     * Verify that we can store objects with same hashCode
     * but being different - String
     */
    @Test
    public void identicalHashCodeButDifferentKeyButSameKeyValue()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        map.put(new String("a"), "c1");
        
        Assert.assertEquals("c1", map.get(new String("a")));
    }
    
    /**
     * 
     */
    @Test
    public void differentHashCodeButSamePosition()
    {
        final BasicHashMap<HashCheater, String> map = new BasicHashMap<>();
        final HashCheater c1 = new HashCheater(0 * 11 + 1);
        final HashCheater c2 = new HashCheater(1 * 11 + 1);
        
        map.put(c1, "c1");
        map.put(c2, "c2");
        
        Assert.assertEquals("c1", map.get(c1));
        Assert.assertEquals("c2", map.get(c2));
    }

    /**
     * Negative hash code
     */
    @Test
    public void negativeHashCodes()
    {
        final BasicHashMap<HashCheater, String> map = new BasicHashMap<>();
        final HashCheater c1 = new HashCheater(-1);
        
        map.put(c1, "c1");
        
        Assert.assertEquals("c1", map.get(c1));
    }

    /**
     * Containskey - happy path
     * 
     * @author rschwietzke
     */
    @Test
    public void containsKeyHappyPath() 
    {
        final BasicHashMap<HashCheater, String> map = new BasicHashMap<>();
        HashCheater c1 = new HashCheater(1);
        HashCheater c2 = new HashCheater(1);
        HashCheater c3 = new HashCheater(2);

        Assert.assertFalse(map.containsKey(c1));

        map.put(c1, "value1");
        
        Assert.assertTrue(map.containsKey(c1));
        Assert.assertFalse(map.containsKey(c2));
        
        map.put(c2, "vaulue2");
        Assert.assertTrue(map.containsKey(c1));
        Assert.assertTrue(map.containsKey(c2));
        Assert.assertFalse(map.containsKey(c3));
    }
    
    /**
     * Contains key, null value
     * @author rschwietzke
     */
    @Test
    public void containsKeyNullValue()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        map.put("key", null);

        Assert.assertTrue(map.containsKey("key"));
    }
    
    /**
     * Contains key, null value
     * @author rschwietzke
     */
    @Test
    public void containsKeyNullKey()
    {
        final BasicHashMap<String, String> map = new BasicHashMap<>();
        try
        {
            map.containsKey(null);
            Assert.fail("Null is valid, cannot be");
        }
        catch(IllegalArgumentException e)
        {
        }
    }
    
    
    class HashCheater
    {
        private final int hashCode;
        private final String value;
        
        public HashCheater(int hashCode)
        {
            this.hashCode = hashCode;
            this.value = "";
        }
        
        public HashCheater(int hashCode, String value)
        {
            this.hashCode = hashCode;
            this.value = value;
        }
        
        @Override
        public int hashCode()
        {
            return hashCode;
        }
    }
}