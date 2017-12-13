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
