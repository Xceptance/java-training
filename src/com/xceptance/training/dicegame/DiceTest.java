package com.xceptance.training.dicegame;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest
{
    @Test
    public void newDice()
    {
        int[] counters = new int[6];
        
        for (int i = 0; i < 10000; i++)
        {
            final Dice d = new Dice(); 
            
            int result = d.getResult();
            counters[result - 1]++;
        } 

        for (int i = 0; i < 6; i++)
        {
            Assert.assertTrue(counters[i] > 1300);
        }
    }

    @Test
    public void diceIsRandom()
    {
        final Dice d = new Dice(); 
        
        int[] counters = new int[6];
        for (int i = 0; i < 10000; i++)
        {
            counters[d.roll() - 1]++;
        }
        
        for (int i = 0; i < 6; i++)
        {
            Assert.assertTrue(counters[i] > 1300);
        }    
    }

    /**
     * Verify that the state of the dice has not changed when 
     * we try to capture it again or to be nerdy: We don't have
     * a Heisenberg problem aka checking the state changes the state.
     */
    @Test
    public void resultPersistence()
    {
        final Dice d = new Dice(); 
        
        // set a new scope with {} to be able to use the name
        // result twice
        {
            final int result = d.getResult();
            Assert.assertEquals(result, d.getResult());
        }
        
        for (int i = 0; i < 10000; i++)
        {
            final int result = d.roll();
            final int state = d.getResult();

            Assert.assertEquals(result, state);
            Assert.assertEquals(state, d.getResult());
        }
    }
    
    
}
