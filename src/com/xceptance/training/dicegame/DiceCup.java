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
        reset(dice);
    }
   
    /**
     * Rolls all dice
     */
    public List<Dice> roll()
    {
        for (final Dice d : dice)
        {
            d.roll();
        } 
       
        // keep a copy for returning
        final List<Dice> result = new ArrayList<>(dice);
        
        // empty out cup
        dice.clear();
       
        // return result
        return result;
    }
    
    /**
     * Shake it
     */
    public void shake()
    {
        for (Dice d : dice)
        {
            d.roll();
        }         
    }
    
    /**
     * Fill the cup again with dice
     * @param dice a list of dice
     */
    public void reset(final List<Dice> dice)
    {
        // empty our cup
        this.dice.clear();
        
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
}
