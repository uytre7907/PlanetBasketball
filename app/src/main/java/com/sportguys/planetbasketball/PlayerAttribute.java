package com.sportguys.planetbasketball;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by uytre_000 on 7/2/2016.
 */
public class PlayerAttribute implements Serializable
{
    private String name;
    private double value;
    private double boostOnGeneration;
    public PlayerAttribute(String name) {
        this.name = name;
        this.boostOnGeneration = 0.0;
        this.value = 0.0;
    }

    @Override
    public String toString()
    {
        return name.substring(0,1).toUpperCase() +name.substring(1) + ": " + (int)(Math.round(value));
    }

    public void generateStat(double multiplier)
    {
        double temp = multiplier*randNorm(25, 5) + boostOnGeneration;
        if (temp>99)
        {
            value = 99;
        }
        else
        {
            value = temp;
        }
    }

    public static double randNorm(double mean, double stdDev)
    {
        Random random = new Random();
        return random.nextGaussian()*stdDev + mean;
    }

    public static int randInt(int low, int high)
    {
        return (int)(Math.random()*(high-low+1) + low);
    }
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double boostOnGeneration() {
        return boostOnGeneration;
    }

    public void setBoostOnGeneration(double boostOnGeneration) {
        this.boostOnGeneration = boostOnGeneration;
    }
    public double getBoostOnGeneration(){ return boostOnGeneration;}
    public String getName() {
        return name;
    }
    //test 2
}
