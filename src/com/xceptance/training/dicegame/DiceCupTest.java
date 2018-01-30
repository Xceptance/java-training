package com.xceptance.training.dicegame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DiceCupTest
{
    /**
     * Verify that we can build a dice cup
     */
    @Test
    public void initADiceCup()
    {
        final List<Dice> dice = new ArrayList<>();
        dice.add(new Dice());
        dice.add(new Dice());
        
        new DiceCup(dice);
    }

    /**
     * Use a dice cup
     */
    @Test
    public void useADiceCup()
    {
        final List<Dice> dice = new ArrayList<>();
        dice.add(new Dice());
        dice.add(new Dice());
        
        final DiceCup dc = new DiceCup(dice);
        
        final List<Dice> result = dc.roll();
        
        Assert.assertEquals(2, result.size());
        for (Dice d : dice)
        {
            Assert.assertTrue(result.contains(d));
        }
    }

    /**
     * Shake
     */
    @Test
    public void shakeIt()
    {
        final List<Dice> dice = new LinkedList<>();
        
        dice.add(new FakeDice());
        dice.add(new FakeDice());
        dice.add(new FakeDice());

        final DiceCup dc = new DiceCup(dice);
        dc.shake();
        
        for (Dice d : dice)
        {
            final FakeDice f = (FakeDice) d;
            Assert.assertEquals(2, f.counter); 
        }
    }
    
    /**
     * Reset
     */
    @Test
    public void testReset()
    {
        final List<Dice> dice = new ArrayList<>();
        dice.add(new Dice());
        dice.add(new Dice());
        
        final DiceCup dc = new DiceCup(dice);
        dc.roll();
        
        // back into the cup
        dc.reset(dice);
        
        final List<Dice> result = dc.roll();
        Assert.assertEquals(dice.size(), result.size());
        
        // verify that the returned dice are the ones we
        // put into the cup in the first place
        for (Dice d : result)
        {
            Assert.assertTrue(dice.contains(d));
        }
    }
    
    @Test
    public void testResetNoRoll()
    {
        final List<Dice> diceSet1 = new ArrayList<>();
        diceSet1.add(new Dice());
        diceSet1.add(new Dice());

        final List<Dice> diceSet2 = new ArrayList<>();
        diceSet2.add(new Dice());
        diceSet2.add(new Dice());
        diceSet2.add(new Dice());

        final DiceCup dc = new DiceCup(diceSet1);
        dc.reset(diceSet2);
        
        final List<Dice> result = dc.roll();
        Assert.assertTrue(diceSet2 != result);
        Assert.assertEquals(diceSet2.size(), result.size());
        
        // verify that the returned dice are the ones we
        // put into the cup in the first place
        for (final Dice d : result)
        {
            Assert.assertTrue(diceSet2.contains(d));
        }
    }
    
    static class FakeDice extends Dice
    {
        public int counter;
        
        @Override
        public int roll()
        {
            counter++;
            
            return super.roll();
        }
    }
}
