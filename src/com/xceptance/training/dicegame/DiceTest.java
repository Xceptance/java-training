package com.xceptance.training.dicegame;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest
{
    @Test
    public void basic()
    {
        int[] counters = new int[6];
        
        for (int i = 0; i < 10000; i++)
        {
            Dice d = new Dice();
            
            int result = d.getResult();
            counters[result - 1]++;
        }

        // just continue here and see if we can evaluate the distribution nicely
        Assert.assertTrue(d.getResult() >= 1 || d.getResult() <= 6);
    }


}
