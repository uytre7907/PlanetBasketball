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
    public PlayerAttribute(String name, double value) {
        this.name=name;
        this.value=value;
    }

    @Override
    public String toString()
    {
        return name.substring(0,1).toUpperCase() +name.substring(1) + ": " + (int)(Math.round(value));
    }
    public void generateStat(double mean, double stdDev)
    {
        double temp = randNorm(mean, stdDev) + boostOnGeneration;
        if (temp>100)
        {
            value = 100;
        }
        else
        {
            value = temp;
        }
    }

    public void generateStatWithValue(double value){
        if(value>100){
            this.value=100;
        }
        else{
            this.value=value;
        }
    }
    public void generateStat(double multiplier)
    {
        double temp = multiplier*randNorm(25, 5) + boostOnGeneration;
        if (temp>100)
        {
            value = 100;
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

    public void setBoostOnGeneration(double boostOnGeneration) {
        this.boostOnGeneration = boostOnGeneration;
    }
    private static double erf(double x)
    {
        //A&S formula 7.1.26
        double a1 = 0.254829592;
        double a2 = -0.284496736;
        double a3 = 1.421413741;
        double a4 = -1.453152027;
        double a5 = 1.061405429;
        double p = 0.3275911;
        x = Math.abs(x);
        double t = 1 / (1 + p * x);
        //Direct calculation using formula 7.1.26 is absolutely correct
        //But calculation of nth order polynomial takes O(n^2) operations
        //return 1 - (a1 * t + a2 * t * t + a3 * t * t * t + a4 * t * t * t * t + a5 * t * t * t * t * t) * Math.Exp(-1 * x * x);

        //Horner's method, takes O(n) operations for nth order polynomial
        return 1 - ((((((a5 * t + a4) * t) + a3) * t + a2) * t) + a1) * t * Math.exp(-1 * x * x);
    }
    public static double normCDF(double z)
    {
        double sign = 1;
        if (z < 0) sign = -1;

        double result=0.5 * (1.0 + sign * erf(Math.abs(z)/Math.sqrt(2)));
        return result;
    }
    public double getBoostOnGeneration(){ return boostOnGeneration;}
    public String getName() {
        return name;
    }
    //test 2
}
