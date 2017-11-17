package com.xceptance.training.bottle;

import org.junit.Assert;
import org.junit.Test;

public class BottleTest
{    
    @Test
    public void newBottleState()
    {
        Bottle f = new Bottle();
        
        Assert.assertFalse(f.isDefective());
        
        Assert.assertTrue(f.isOpen());
        Assert.assertFalse(f.isClosed());
        
        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getMaximumVolume());
    }
    
    @Test
    public void create500mlBottle()
    {
        Bottle f = new Bottle(500);
        
        Assert.assertFalse(f.isDefective());
        
        Assert.assertTrue(f.isOpen());
        Assert.assertFalse(f.isClosed());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(500, f.getMaximumVolume());
    }

    @Test
    public void createWeirdZeroBottle()
    {
        Bottle f = new Bottle(0);
        
        Assert.assertTrue(f.isDefective());

        Assert.assertTrue(f.isOpen());
        Assert.assertFalse(f.isClosed());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(0, f.getMaximumVolume());
    }

    @Test
    public void createWeirdNegativeBottle()
    {
        Bottle f = new Bottle(-42);
        
        Assert.assertTrue(f.isDefective());
        
        Assert.assertTrue(f.isOpen());
        Assert.assertFalse(f.isClosed());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(-42, f.getMaximumVolume());
    }
    
    @Test
    public void closeAnOpenBottle()
    {
        Bottle f = new Bottle();
        f.close();
        
        Assert.assertFalse(f.isDefective());

        Assert.assertTrue(f.isClosed());
        Assert.assertFalse(f.isOpen());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getMaximumVolume());
    }

    @Test
    public void closeAnClosedBottle()
    {
        Bottle f = new Bottle();
        f.close();
        f.close();
        
        Assert.assertFalse(f.isDefective());
        
        Assert.assertTrue(f.isClosed());
        Assert.assertFalse(f.isOpen());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getMaximumVolume());
    }
    
    @Test
    public void openAClosedBottle()
    {
        Bottle f = new Bottle(); 
        f.close();
        f.open();
        
        Assert.assertFalse(f.isDefective());

        Assert.assertFalse(f.isClosed());
        Assert.assertTrue(f.isOpen());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getMaximumVolume());
    }

    @Test
    public void openAnOpenBottle()
    {
        Bottle f = new Bottle();
        f.open();
        
        Assert.assertFalse(f.isDefective());

        Assert.assertFalse(f.isClosed());
        Assert.assertTrue(f.isOpen());

        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getMaximumVolume());
    }

    @Test
    public void fillUpOkBottle()
    {
        {
            Bottle f = new Bottle();
            boolean result = f.fillUp();

            Assert.assertTrue(result);
            Assert.assertFalse(f.isDefective());
            Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getCurrentVolume());
            Assert.assertEquals(Bottle.DEFAULT_MAXIMUM_BOTTLE_VOLUME, f.getMaximumVolume());

            Assert.assertFalse(f.isClosed());
            Assert.assertTrue(f.isOpen());
        }
        {
            Bottle f = new Bottle(200);
            boolean result = f.fillUp();

            Assert.assertTrue(result);
            Assert.assertFalse(f.isDefective());
            Assert.assertEquals(200, f.getCurrentVolume());
            Assert.assertEquals(200, f.getMaximumVolume());

            Assert.assertFalse(f.isClosed());
            Assert.assertTrue(f.isOpen());
        }
    }
    
    @Test 
    public void tryToFillUpDefectiveBottle()
    {
        Bottle f = new Bottle(-9864);
        boolean result = f.fillUp();

        Assert.assertFalse(result);
        Assert.assertTrue(f.isDefective());
        
        Assert.assertEquals(0, f.getCurrentVolume());
        Assert.assertEquals(-9864, f.getMaximumVolume());

        Assert.assertFalse(f.isClosed());
        Assert.assertTrue(f.isOpen());
    }

    /**
     * We assume that we cannot fill up a closed bottle
     * and that this operation does not compromise the bottle,
     * hence it stays ok and closed.
     */
    @Test 
    public void tryToFillUpClosedBottle()
    {
        Bottle f = new Bottle();
        f.close();
        
        boolean result = f.fillUp();
        Assert.assertFalse(result); // could not fill up
        Assert.assertFalse(f.isDefective()); // bottle still ok
        
        Assert.assertTrue(f.isClosed()); // bottle stays closed
        Assert.assertFalse(f.isOpen()); // bottle stays not open
    }
    
    /**
     * Verify than an ok bottle that is open and has content can be poured out.
     * - pour a little bit out (330-200)
     * - pour more out than in the bottle (200-250)
     * - pour nothing out (200-0)
     * - pour negative value out (200-42)
     */
    @Test
    public void pourOkBottle()
    {
        {
            // pour a little bit out
            final Bottle f = new Bottle(330);
            f.open();
            f.fillUp();
            
            final boolean result = f.pour(200);
            
            Assert.assertTrue(result); //we could pour out
            Assert.assertEquals(130,f.getCurrentVolume()); //our new volume afterwards
            Assert.assertFalse(f.isEmpty());
            Assert.assertTrue(f.isOpen());
            Assert.assertFalse(f.isDefective());
        }
        {
            //pour more out than in the bottle
            final Bottle f = new Bottle(200);
            f.open();
            f.fillUp();
            
            final boolean result = f.pour(250);
            
            Assert.assertFalse(result); //we couldn't pour out
            Assert.assertEquals(200,f.getCurrentVolume()); //volume not changed
            Assert.assertFalse(f.isEmpty());
            Assert.assertTrue(f.isOpen());
            Assert.assertFalse(f.isDefective());
        }
        {
            //pour nothing out
            final Bottle f = new Bottle(200);
            f.open();
            f.fillUp();
            
            final boolean result = f.pour(0);
            
            Assert.assertFalse(result); //we couldn't pour out
            Assert.assertEquals(200,f.getCurrentVolume()); //volume not changed
            Assert.assertFalse(f.isEmpty());
            Assert.assertTrue(f.isOpen());
            Assert.assertFalse(f.isDefective());
        }
        {
            //pure negative value out
            final Bottle f = new Bottle(200);
            f.open();
            f.fillUp();
            
            final boolean result = f.pour(-42);
            
            Assert.assertFalse(result); //we couldn't pour out
            Assert.assertEquals(200,f.getCurrentVolume()); //volume not changed
            Assert.assertFalse(f.isEmpty());
            Assert.assertTrue(f.isOpen());
            Assert.assertFalse(f.isDefective());
        }
    }

    /**
     * Verify than an bottle that is closed can't poured out.
     * 
     */
    @Test
    public void pourClosedBottle()
    {
        final Bottle f = new Bottle(200);
        f.open();
        f.fillUp();
        f.close();
        
        final boolean result = f.pour(200);
        
        Assert.assertFalse(result); //we couldn't pour out
        Assert.assertEquals(200,f.getCurrentVolume()); //volume not changed
        Assert.assertFalse(f.isEmpty());
        Assert.assertFalse(f.isOpen());
        Assert.assertTrue(f.isClosed()); //added after sending to RS
        Assert.assertFalse(f.isDefective());
    }
    
    /**
     * Verify than an bottle that is defective can't poured out.
     * 
     */
    @Test
    public void pourDefectiveBottle()
    {
        final Bottle f = new Bottle(0);
        f.open();
        f.fillUp();
        
        final boolean result = f.pour(0);
        
        Assert.assertFalse(result); //we couldn't pour out
        Assert.assertEquals(0,f.getCurrentVolume()); //volume not changed
        Assert.assertTrue(f.isEmpty());
        Assert.assertTrue(f.isOpen());
        Assert.assertTrue(f.isDefective());
    }

    // test case pour closed bottle
    
    // test case pour defective bottle
    
    /**
     * Test that the bottle is empty by default
     */
    @Test
    public void isEmptyInitially()
    {
        final Bottle f = new Bottle();
        Assert.assertTrue(f.isEmpty());
    }
    
    /**
     * Test that the bottle is empty after fill up and full drain
     */
    @Test
    public void isEmptyAfterUsageCycle()
    {
        final Bottle f = new Bottle(900);
        f.fillUp();
        f.pour(900);
        
        Assert.assertTrue(f.isEmpty());
    }    

    /**
     * Test that the bottle is empty by default
     */
    @Test
    public void isEmptyOfAFullBottle()
    {
        final Bottle f = new Bottle();
        f.fillUp();
        Assert.assertFalse(f.isEmpty());
    }
}

