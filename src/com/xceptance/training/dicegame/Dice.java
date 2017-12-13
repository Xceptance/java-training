package com.xceptance.training.dicegame;

import java.util.Random;

/**
 * Simulates a real dice with a state of a certain result
 * and the ability to be thrown aka we get a new state
 * 
 * @author rschwietzke
 */
public final class Dice
{
    private int result;
    
    /**
     * Constructor
     */
    public Dice()
    {
        roll();
    }
    
    /**
     * Returns the current state aka result of the dice
     * 
     * @return the "augen" count
     */
    public int getResult()
    {
        return result;
    }
    
    /**
     * Rolls the dice and returns a new result
     * 
     * @return the result of the roll operation
     */
    public int roll()
    {
        // random comes here
        final Random r = new Random();
        result = (int) (6 * r.nextDouble() + 1); 
        
        return result;
    }
}
