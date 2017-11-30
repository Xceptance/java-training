package com.xceptance.training.dicegame;

import java.util.ArrayList;
import java.util.List;

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
}
