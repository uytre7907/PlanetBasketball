package com.sportguys.planetbasketball;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by uytre_000 on 7/2/2016.
 */
public class AttributeList implements Serializable{
    private static String[] attributeNames =  {"speed", "layup","close", "midrange", "threes", "passing", "dribbling","defending", "steal", "block", "rebounding", "awareness",
                                        "strength","vertical", "size", "stamina", "potential"};

    public List<PlayerAttribute> getPlayerAttributes() {
        return playerAttributes;
    }

    private List<PlayerAttribute> playerAttributes;
    private Position position;
    private static LinkedHashMap<Position, Double> averageHeights = new LinkedHashMap<>();
    static {
        averageHeights.put(Position.PointGuard, 74.0);
        averageHeights.put(Position.ShootingGuard, 77.0);
        averageHeights.put(Position.SmallForward, 79.5);
        averageHeights.put(Position.PowerForward, 82.0);
        averageHeights.put(Position.Center, 83.5);
    }
    private static LinkedHashMap<Position, Double> devHeights = new LinkedHashMap<>();
    static {
        devHeights.put(Position.PointGuard, 1.5);
        devHeights.put(Position.ShootingGuard, 1.5);
        devHeights.put(Position.SmallForward, 1.5);
        devHeights.put(Position.PowerForward, 1.0);
        devHeights.put(Position.Center, .75);
    }
    public AttributeList(List<PlayerAttribute> playerAttributes, Position position)
    {
        this.playerAttributes=playerAttributes;
    }
    public AttributeList(Position position, int prestige, double age, double height, LinkedHashMap<String, Double> boosts) {
        //could use some refining
        //add speed and vertical to boosts
        this.position=position;
        double multiplier = prestige/PlayerAttribute.randNorm(75, 10)*age/PlayerAttribute.randNorm(15, 1);
        playerAttributes = new ArrayList<PlayerAttribute>();
        for(String name: attributeNames) {
            playerAttributes.add(new PlayerAttribute(name));
        }
        boostStats(boosts);
        generateStats(multiplier, position, height);
        getAttributeForKey("potential").setValue(genPotential());

    }
    private void generateStats(double multiplier, Position position, double height){
        for(PlayerAttribute p: playerAttributes)
        {
            if(!p.getName().equals("potential")) {
                if(!p.getName().equals("size"))
                    p.generateStat(multiplier);
                else {
                    double z=(height-averageHeights.get(position))/devHeights.get(position);
                    p.generateStatWithValue(100*PlayerAttribute.normCDF(z));
                }
            }
        }
    }
    private double genPotential(){
        int overall=getOverall(position);
        double potential = PlayerAttribute.randNorm((99-overall)/4+overall, (99-overall)/5);
        if(potential>99)
            potential=99;
        else if(potential<overall)
            potential=overall;

        return potential;
    }

    public static LinkedHashMap<Position, Double> getAverageHeights() {
        return averageHeights;
    }

    public static void setAverageHeights(LinkedHashMap<Position, Double> averageHeights) {
        AttributeList.averageHeights = averageHeights;
    }

    public static LinkedHashMap<Position, Double> getDevHeights() {
        return devHeights;
    }

    public static void setDevHeights(LinkedHashMap<Position, Double> devHeights) {
        AttributeList.devHeights = devHeights;
    }

    private void boostStats(LinkedHashMap<String, Double> lhm)
    {
        boolean athletic=PlayerAttribute.randInt(1, 100)<34;
        if(athletic)
        {
            lhm.put("speed", PlayerAttribute.randNorm(PlayerAttribute.randInt(38, 45), PlayerAttribute.randInt(2,6)));
            lhm.put("vertical", PlayerAttribute.randNorm(PlayerAttribute.randInt(38, 45), PlayerAttribute.randInt(2,6)));
        }
        else{
            lhm.put("speed", 21.0);
            lhm.put("vertical", 21.0);
        }
        for (Map.Entry<String, Double> entry : lhm.entrySet()) {
            getAttributeForKey(entry.getKey()).setBoostOnGeneration(entry.getValue());
        }
    }
    @Override
    public String toString() {
        String out = "";
        for(PlayerAttribute p: playerAttributes)
        {
            out+=p.toString() + "\n";
        }
        return out;
    }
    public PlayerAttribute getAttributeForKey(String key)
    {
        for(PlayerAttribute p: playerAttributes)
        {
            if(p.getName().equals(key))
            {
                return p;
            }
        }
        return null;
    }
    public int getOverall(Position position){
        switch (position){
            case PointGuard:{
                return (int)(   .06*getAttributeForKey("speed").getValue()+.05*getAttributeForKey("layup").getValue()+
                        .05*getAttributeForKey("close").getValue()+.07*getAttributeForKey("midrange").getValue()+
                        .07*getAttributeForKey("threes").getValue()+.19*getAttributeForKey("passing").getValue()+
                        .19*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .07*getAttributeForKey("steal").getValue()+.02*getAttributeForKey("block").getValue()+
                        .02*getAttributeForKey("rebounding").getValue()+.06*getAttributeForKey("awareness").getValue()+
                        .02*getAttributeForKey("strength").getValue()+.02*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue() + .02*getAttributeForKey("size").getValue());
            }
            case ShootingGuard:{
                return (int)(   .08*getAttributeForKey("speed").getValue()+.09*getAttributeForKey("layup").getValue()+
                        .1*getAttributeForKey("close").getValue()+.11*getAttributeForKey("midrange").getValue()+
                        .15*getAttributeForKey("threes").getValue()+.05*getAttributeForKey("passing").getValue()+
                        .05*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .05*getAttributeForKey("steal").getValue()+.02*getAttributeForKey("block").getValue()+
                        .04*getAttributeForKey("rebounding").getValue()+.04*getAttributeForKey("awareness").getValue()+
                        .03*getAttributeForKey("strength").getValue()+.06*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue()+ .04*getAttributeForKey("size").getValue());
            }
            case SmallForward:{
                return (int)(   .06*getAttributeForKey("speed").getValue()+.07*getAttributeForKey("layup").getValue()+
                        .06*getAttributeForKey("close").getValue()+.06*getAttributeForKey("midrange").getValue()+
                        .06*getAttributeForKey("threes").getValue()+.06*getAttributeForKey("passing").getValue()+
                        .06*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .06*getAttributeForKey("steal").getValue()+.06*getAttributeForKey("block").getValue()+
                        .07*getAttributeForKey("rebounding").getValue()+.06*getAttributeForKey("awareness").getValue()+
                        .06*getAttributeForKey("strength").getValue()+.06*getAttributeForKey("vertical").getValue()+
                        .06*getAttributeForKey("stamina").getValue()+ .07*getAttributeForKey("size").getValue());
            }
            case PowerForward:{
                return (int)(   .05*getAttributeForKey("speed").getValue()+.12*getAttributeForKey("layup").getValue()+
                        .1*getAttributeForKey("close").getValue()+.06*getAttributeForKey("midrange").getValue()+
                        .02*getAttributeForKey("threes").getValue()+.05*getAttributeForKey("passing").getValue()+
                        .02*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .08*getAttributeForKey("steal").getValue()+.08*getAttributeForKey("block").getValue()+
                        .1*getAttributeForKey("rebounding").getValue()+.03*getAttributeForKey("awareness").getValue()+
                        .07*getAttributeForKey("strength").getValue()+.03*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue()+ .1*getAttributeForKey("size").getValue());
            }
            case Center:{
                return (int)(   .02*getAttributeForKey("speed").getValue()+.15*getAttributeForKey("layup").getValue()+
                        .05*getAttributeForKey("close").getValue()+.02*getAttributeForKey("midrange").getValue()+
                        .01*getAttributeForKey("threes").getValue()+.02*getAttributeForKey("passing").getValue()+
                        .01*getAttributeForKey("dribbling").getValue()+.1*getAttributeForKey("defending").getValue()+
                        .04*getAttributeForKey("steal").getValue()+.15*getAttributeForKey("block").getValue()+
                        .15*getAttributeForKey("rebounding").getValue()+.02*getAttributeForKey("awareness").getValue()+
                        .08*getAttributeForKey("strength").getValue()+.02*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue()+ .14*getAttributeForKey("size").getValue());
            }
        }
        return 60;
    }
    public static String[] getAttributeNames(){
        return attributeNames;
    }
    /*
    public double getPotential() {
        return potential;
    }

    public double getSpeed() {
        return speed;
    }

    public double getShooting() {
        return shooting;
    }

    public double getPassing() {
        return passing;
    }

    public double getDribbling() {
        return dribbling;
    }

    public double getDefending() {
        return defending;
    }

    public double getHeading() {
        return heading;
    }

    public double getAwareness() {
        return awareness;
    }

    public double getInstinct() {
        return instinct;
    }

    public double getStrength() {
        return strength;
    }

    public double getSkillMoves() {
        return skillMoves;
    }
*/
}