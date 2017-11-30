package com.xceptance.training.dicegame;

import java.util.ArrayList;
import java.util.List;

public class DiceCup
{
    // list of dice to use
    private final List<Dice> dice = new ArrayList<>();
    
    
    /**
     * Constructor, takes a list of dice to be used
     */
    public DiceCup(final List<Dice> dice)
    {
        // create our own list of dice to make sure
        // we do not have "null" dice in our hands
        for (final Dice d : dice)
        {
            // check that the dice is an existing dice
            if (d != null)
            {
                // add the dice to the internal list
                this.dice.add(d);
            }
        }
    }
    
    /**
     * Rolls all dice
     */
    public void roll()
    {
//        for (int i = 0; i < dice.size(); i++)
//        {
//            dice.get(i).roll();
//        }
//
//        for (int i = 0; i < dice.size(); i++)
//        {
//            Dice d = dice.get(i);
//            d.roll();
//        }
        
        for (Dice d : dice)
        {
            d.roll();
        }        
    }
    
    /**
     * Shake it
     */
    public void shake()
    {
        
    }
    
    /**
     * Fill the cup again with dice
     * @param dice a list of dice
     */
    public void reset(List<Dice> dice)
    {
        
    }
}
