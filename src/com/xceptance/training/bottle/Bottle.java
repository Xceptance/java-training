package com.xceptance.training.bottle;

public class Bottle
{
    // limit the max size
    // require minimum size
    
    /**
     * Standard bottle volume
     */
    public final static int DEFAULT_MAXIMUM_BOTTLE_VOLUME = 750;
    
    /**
     * Open state: True if the bottle is open
     */
    private boolean open = true;
    
    /**
     * Represents the fill level aka volume in ml
     */
    private int currentVolume = 0;
    
    /**
     * Maximum volume possible
     */
    private final int maximumVolume;
    
    /**
     * Default Constructor and it assumes a standard
     * size bottle
     */
    public Bottle()
    {
        this.maximumVolume = DEFAULT_MAXIMUM_BOTTLE_VOLUME;
    }
    
    /**
     * Constructor
     * 
     * @param maximumVolume define maximum volume possible
     */
    public Bottle(final int maximumVolume)
    {
        this.maximumVolume = maximumVolume;
    }
    
    /**
     * Tells us if this bottle us usable
     * 
     * @return true if the bottle is defective, false otherwise
     */
    public boolean isDefective()
    {
        // max volume is less or equal 0 --> defective
        if (maximumVolume < 0)
        {
            return true;
        }
        else if (maximumVolume == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Is the bottle open?
     * 
     * @return true when open, false otherwise
     */
    public boolean isOpen()
    {
        return open;
    }
    
    /**
     * Is the bottle closed?
     * 
     * @return true if closed, false otherwise
     */
    public boolean isClosed()
    {
        return !open;
    }

    /**
     * Closes a bottle, when the bottle is closed,
     * it will be closed again.
     */
    public void close()
    {
        open = false;
    }
    
    /**
     * Opens a bottle, when the bottle is open,
     * it will be opened again.
     */
    public void open()
    {
        open = true;
    }
    
    /**
     * Returns the current volume of the bottle
     * 
     * @return the current volume of the bottle
     */
    public int getCurrentVolume()
    {
        return currentVolume;
    }
    
    /**
     * Return the maximum volume possible
     * 
     * @return the maximum volume of the bottle
     */
    public int getMaximumVolume()
    {
        return maximumVolume;
    }
    
    /**
     * Fills up the bottle to its max volume.
     * 
     * @return true if the fill up took place, false otherwise
     */
    public boolean fillUp()
    { 
        // check if the bottle is defective
        if (!isDefective() && isOpen())
        {
            currentVolume = maximumVolume;
            return true;
        }
        return false;
    }

    /**
     * Pour some content out
     * 
     * @param volumeToPour what volume to pour 
     * @return true if we could pour anything or false otherwise
     */
    public boolean pour(final int volumeToPour)
    {
        if (isDefective() || isClosed() || isEmpty() || volumeToPour > currentVolume)
        {
            return false;
        }
        else if (volumeToPour > 0)
        {
            currentVolume = currentVolume - volumeToPour;
            
            return true;
        }
        else
        {  
            return false;
        }
    }
    
    /**
     * Is the bottle empty?
     * 
     * @return returns true of the bottle is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return getCurrentVolume() == 0;
    }
}
