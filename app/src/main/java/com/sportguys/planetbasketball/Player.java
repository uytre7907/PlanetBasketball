package com.sportguys.planetbasketball;


import android.content.Context;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by uytre_000 on 7/2/2016.
 */
enum Position
{
    PointGuard, ShootingGuard, SmallForward, PowerForward, Center
}


public class Player implements Comparable<Player>
{
    private Position position;
    private int positionInt;
    private AttributeList attributeList;
    private double age;
    private String name;
    private LayerDrawable face;
    private static ArrayList<String> firstNames=App.getFirstNames();
    private static ArrayList<String> lastNames=App.getLastNames();
    private static LinkedHashMap<String, Double> pointBoosts = new LinkedHashMap<>();
    static {
        pointBoosts.put("layup", 30.0);
        pointBoosts.put("close", 25.0);
        pointBoosts.put("midrange", 25.0);
        pointBoosts.put("threes", 25.0);
        pointBoosts.put("passing", 45.0);
        pointBoosts.put("dribbling", 45.0);
        pointBoosts.put("defending", 25.0);
        pointBoosts.put("steal", 20.0);
        pointBoosts.put("awareness", 30.0);
        pointBoosts.put("stamina", 35.0);
    }
    private static LinkedHashMap<String, Double> shootingBoosts = new LinkedHashMap<>();
    static {
        shootingBoosts.put("layup", 30.0);
        shootingBoosts.put("close", 30.0);
        shootingBoosts.put("midrange", 40.0);
        shootingBoosts.put("threes", 40.0);
        shootingBoosts.put("passing", 25.0);
        shootingBoosts.put("dribbling", 25.0);
        shootingBoosts.put("defending", 30.0);
        shootingBoosts.put("steal", 25.0);
        shootingBoosts.put("block", 10.0);
        shootingBoosts.put("rebounding", 15.0);
        shootingBoosts.put("awareness", 25.0);
        shootingBoosts.put("strength", 15.0);
        shootingBoosts.put("stamina", 35.0);
    }
    private static LinkedHashMap<String, Double> smallBoosts = new LinkedHashMap<>();
    static {
        smallBoosts.put("layup", 35.0);
        smallBoosts.put("close", 35.0);
        smallBoosts.put("midrange", 35.0);
        smallBoosts.put("threes", 25.0);
        smallBoosts.put("speed", 25.0);
        smallBoosts.put("passing", 25.0);
        smallBoosts.put("dribbling", 25.0);
        smallBoosts.put("defending", 30.0);
        smallBoosts.put("steal", 25.0);
        smallBoosts.put("block", 25.0);
        smallBoosts.put("rebounding", 25.0);
        smallBoosts.put("awareness", 25.0);
        smallBoosts.put("strength", 25.0);
        smallBoosts.put("stamina", 35.0);
    }
    private static LinkedHashMap<String, Double> powerBoosts = new LinkedHashMap<>();
    static {
        powerBoosts.put("layup", 45.0);
        powerBoosts.put("close", 40.0);
        powerBoosts.put("midrange", 25.0);
        powerBoosts.put("threes", 5.0);
        powerBoosts.put("speed", 20.0);
        powerBoosts.put("passing", 15.0);
        powerBoosts.put("dribbling", 15.0);
        powerBoosts.put("defending", 25.0);
        powerBoosts.put("steal", 15.0);
        powerBoosts.put("block", 30.0);
        powerBoosts.put("rebounding", 40.0);
        powerBoosts.put("awareness", 20.0);
        powerBoosts.put("strength", 35.0);
        powerBoosts.put("stamina", 25.0);
    }
    private static LinkedHashMap<String, Double> centerBoosts = new LinkedHashMap<>();
    static {
        centerBoosts.put("layup", 45.0);
        centerBoosts.put("close", 30.0);
        centerBoosts.put("midrange", 15.0);
        centerBoosts.put("speed", 15.0);
        centerBoosts.put("passing", 10.0);
        centerBoosts.put("dribbling", 10.0);
        centerBoosts.put("defending", 25.0);
        centerBoosts.put("steal", 30.0);
        centerBoosts.put("block", 45.0);
        centerBoosts.put("rebounding", 50.0);
        centerBoosts.put("awareness", 15.0);
        centerBoosts.put("strength", 45.0);
        centerBoosts.put("stamina", 20.0);
    }
    public Player()
    {
        int dim = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, DataHolder.getContext().getResources().getDisplayMetrics());
        face=Faces.makeFace(dim, dim);
        age=PlayerAttribute.randNorm(20, 2);
        int temp = PlayerAttribute.randInt(0,4);
        name=firstNames.get(PlayerAttribute.randInt(0, firstNames.size()-1)) + " " + lastNames.get(PlayerAttribute.randInt(0, lastNames
                .size()-1));
        LinkedHashMap<String, Double> boosts;
        switch (temp){
            case (0): {
                position = Position.PointGuard;
                positionInt=1;
                boosts=pointBoosts;
                break;
            }
            case (1):{
                position=Position.ShootingGuard;
                positionInt=2;
                boosts=shootingBoosts;
                break;
            }
            case (2):{
                position=Position.SmallForward;
                positionInt=3;
                boosts=smallBoosts;
                break;
            }
            case (3):{
                position=Position.PowerForward;
                positionInt=4;
                boosts=powerBoosts;
                break;
            }
            case (4):{
                position=Position.Center;
                positionInt=5;
                boosts=centerBoosts;
                break;
            }
            default:{
                position=Position.SmallForward;
                boosts=smallBoosts;
                break;
            }
        }

        attributeList = new AttributeList(position, (int) PlayerAttribute.randNorm(50, 15), age, boosts);
    }

    @Override
    public String toString() {
        return getPositionString()+ "\nAge: " + (int)age + "\nOverall: " +getOverall() + "\n" + attributeList.toString();
    }
    public AttributeList getAttributes(){
        return attributeList;
    }
    public int getPositionInt(){
        return positionInt;
    }
    public int compareTo(Player p)
    {
        return positionInt-p.getPositionInt();
    }
    public void save(ObjectOutputStream objectOutputStream)
    {
        try {
            objectOutputStream.writeObject(this);
        }
        catch (IOException e)
        {}

    }
    public String getName()
    {
        return name;
    }
    public LayerDrawable getFace(){
        return face;
    }
    public String getPositionString(){
        switch (positionInt){
            case(1):{
                return "Point Guard";
            }
            case(2):{
                return "Shooting Guard";
            }
            case(3):{
                return "Small Forward";
            }
            case(4):{
                return "Power Forward";
            }
            case(5):{
                return "Center";
            }


        }
        return "Small Forward";
    }
    public Position getPosition(){
        return position;
    }
    public int getOverall(){
        switch (position){
            case PointGuard:{
                return (int)(   .06*attributeList.getAttributeForKey("speed").getValue()+.05*attributeList.getAttributeForKey("layup").getValue()+
                                .05*attributeList.getAttributeForKey("close").getValue()+.07*attributeList.getAttributeForKey("midrange").getValue()+
                                .07*attributeList.getAttributeForKey("threes").getValue()+.2*attributeList.getAttributeForKey("passing").getValue()+
                                .2*attributeList.getAttributeForKey("dribbling").getValue()+.07*attributeList.getAttributeForKey("defending").getValue()+
                                .07*attributeList.getAttributeForKey("steal").getValue()+.02*attributeList.getAttributeForKey("block").getValue()+
                                .02*attributeList.getAttributeForKey("rebounding").getValue()+.06*attributeList.getAttributeForKey("awareness").getValue()+
                                .02*attributeList.getAttributeForKey("strength").getValue()+.02*attributeList.getAttributeForKey("vertical").getValue()+
                                .02*attributeList.getAttributeForKey("stamina").getValue());
            }
            case ShootingGuard:{
                return (int)(   .1*attributeList.getAttributeForKey("speed").getValue()+.1*attributeList.getAttributeForKey("layup").getValue()+
                        .1*attributeList.getAttributeForKey("close").getValue()+.12*attributeList.getAttributeForKey("midrange").getValue()+
                        .15*attributeList.getAttributeForKey("threes").getValue()+.05*attributeList.getAttributeForKey("passing").getValue()+
                        .05*attributeList.getAttributeForKey("dribbling").getValue()+.07*attributeList.getAttributeForKey("defending").getValue()+
                        .05*attributeList.getAttributeForKey("steal").getValue()+.02*attributeList.getAttributeForKey("block").getValue()+
                        .04*attributeList.getAttributeForKey("rebounding").getValue()+.04*attributeList.getAttributeForKey("awareness").getValue()+
                        .03*attributeList.getAttributeForKey("strength").getValue()+.06*attributeList.getAttributeForKey("vertical").getValue()+
                        .02*attributeList.getAttributeForKey("stamina").getValue());
            }
            case SmallForward:{
                return (int)(   .07*attributeList.getAttributeForKey("speed").getValue()+.07*attributeList.getAttributeForKey("layup").getValue()+
                        .07*attributeList.getAttributeForKey("close").getValue()+.07*attributeList.getAttributeForKey("midrange").getValue()+
                        .07*attributeList.getAttributeForKey("threes").getValue()+.07*attributeList.getAttributeForKey("passing").getValue()+
                        .07*attributeList.getAttributeForKey("dribbling").getValue()+.07*attributeList.getAttributeForKey("defending").getValue()+
                        .07*attributeList.getAttributeForKey("steal").getValue()+.07*attributeList.getAttributeForKey("block").getValue()+
                        .07*attributeList.getAttributeForKey("rebounding").getValue()+.07*attributeList.getAttributeForKey("awareness").getValue()+
                        .07*attributeList.getAttributeForKey("strength").getValue()+.07*attributeList.getAttributeForKey("vertical").getValue()+
                        .07*attributeList.getAttributeForKey("stamina").getValue());
            }
            case PowerForward:{
                return (int)(   .05*attributeList.getAttributeForKey("speed").getValue()+.14*attributeList.getAttributeForKey("layup").getValue()+
                        .12*attributeList.getAttributeForKey("close").getValue()+.09*attributeList.getAttributeForKey("midrange").getValue()+
                        .03*attributeList.getAttributeForKey("threes").getValue()+.05*attributeList.getAttributeForKey("passing").getValue()+
                        .02*attributeList.getAttributeForKey("dribbling").getValue()+.07*attributeList.getAttributeForKey("defending").getValue()+
                        .08*attributeList.getAttributeForKey("steal").getValue()+.1*attributeList.getAttributeForKey("block").getValue()+
                        .1*attributeList.getAttributeForKey("rebounding").getValue()+.03*attributeList.getAttributeForKey("awareness").getValue()+
                        .07*attributeList.getAttributeForKey("strength").getValue()+.03*attributeList.getAttributeForKey("vertical").getValue()+
                        .02*attributeList.getAttributeForKey("stamina").getValue());
            }
            case Center:{
                return (int)(   .04*attributeList.getAttributeForKey("speed").getValue()+.18*attributeList.getAttributeForKey("layup").getValue()+
                        .08*attributeList.getAttributeForKey("close").getValue()+.04*attributeList.getAttributeForKey("midrange").getValue()+
                        .02*attributeList.getAttributeForKey("threes").getValue()+.04*attributeList.getAttributeForKey("passing").getValue()+
                        .02*attributeList.getAttributeForKey("dribbling").getValue()+.1*attributeList.getAttributeForKey("defending").getValue()+
                        .04*attributeList.getAttributeForKey("steal").getValue()+.15*attributeList.getAttributeForKey("block").getValue()+
                        .15*attributeList.getAttributeForKey("rebounding").getValue()+.02*attributeList.getAttributeForKey("awareness").getValue()+
                        .08*attributeList.getAttributeForKey("strength").getValue()+.02*attributeList.getAttributeForKey("vertical").getValue()+
                        .02*attributeList.getAttributeForKey("stamina").getValue());
            }
        }
        return 60;
    }
    public String getOverallString(){
        return getOverall()+" OVR";
    }
}
