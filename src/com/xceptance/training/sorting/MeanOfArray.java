package com.xceptance.training.sorting;

public class MeanOfArray
{
    /**
     * Calculates the mean of all integers of our array
     * 
     * @param numbers a list of integers
     * @return the mean of all integers
     */
    public double mean(int[] numbers) 
    {
        int sum = 0;

        for (int i = 0; i < numbers.length; i++)
        {
            sum = sum + numbers[i];
        }
        
        if (numbers.length > 0)
        {
            return sum / numbers.length;    
        }
        else
        {
            return sum;
        }
    }

    public double mean2(int[] numbers) 
    {
        if (numbers.length > 0)
        {
            int sum = 0;

            for (int i = 0; i < numbers.length; i++)
            {
                sum = sum + numbers[i];
            }
            
            return sum / numbers.length;
        }

        return 0.0;
    }
    
    public double mean3(int[] numbers) 
    {
        if (numbers == null || numbers.length == 0)
        {
            return 0.0;
        }
            
        int sum = 0;

        for (int i = 0; i < numbers.length; i++)
        {
            sum = sum + numbers[i];
        }
        
        return sum / numbers.length;
    }

    public double mean4(int[] numbers) 
    {
        int sum = 0;

        for (int i = 0; i < numbers.length; i++)
        {
            sum = sum + numbers[i]; // sum += numbers[i];
        }
        
        return numbers.length == 0 ? 0.0 : sum / numbers.length;
    }

}
