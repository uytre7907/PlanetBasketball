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
    private String[] attributeNames =  {"speed", "layup","close", "midrange", "threes", "passing", "dribbling","defending", "steal", "block", "rebounding", "awareness",
                                        "strength","vertical", "stamina", "potential"};
    private List<PlayerAttribute> playerAttributes;
    private Position position;
    public AttributeList(Position position, int prestige, double age, LinkedHashMap<String, Double> boosts) {
        //could use some refining
        //add speed and vertical to boosts
        this.position=position;
        double multiplier = prestige/PlayerAttribute.randNorm(75, 10)*age/PlayerAttribute.randNorm(15, 1);
        playerAttributes = new ArrayList<PlayerAttribute>();
        for(String name: attributeNames) {
            playerAttributes.add(new PlayerAttribute(name));
        }
        boostStats(boosts);
        generateStats(multiplier);
        getAttributeForKey("potential").setValue(genPotential());

    }
    private void generateStats(double multiplier){
        for(PlayerAttribute p: playerAttributes)
        {
            if(!p.getName().equals("potential"))
                p.generateStat(multiplier);
        }
    }
    private double genPotential(){
        int overall=getOverall();
        double potential = PlayerAttribute.randNorm((99-overall)/4+overall, (99-overall)/5);
        if(potential>99)
            potential=99;
        else if(potential<overall)
            potential=overall;
        return potential;
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
    public int getOverall(){
        switch (position){
            case PointGuard:{
                return (int)(   .06*getAttributeForKey("speed").getValue()+.05*getAttributeForKey("layup").getValue()+
                        .05*getAttributeForKey("close").getValue()+.07*getAttributeForKey("midrange").getValue()+
                        .07*getAttributeForKey("threes").getValue()+.2*getAttributeForKey("passing").getValue()+
                        .2*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .07*getAttributeForKey("steal").getValue()+.02*getAttributeForKey("block").getValue()+
                        .02*getAttributeForKey("rebounding").getValue()+.06*getAttributeForKey("awareness").getValue()+
                        .02*getAttributeForKey("strength").getValue()+.02*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue());
            }
            case ShootingGuard:{
                return (int)(   .1*getAttributeForKey("speed").getValue()+.1*getAttributeForKey("layup").getValue()+
                        .1*getAttributeForKey("close").getValue()+.12*getAttributeForKey("midrange").getValue()+
                        .15*getAttributeForKey("threes").getValue()+.05*getAttributeForKey("passing").getValue()+
                        .05*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .05*getAttributeForKey("steal").getValue()+.02*getAttributeForKey("block").getValue()+
                        .04*getAttributeForKey("rebounding").getValue()+.04*getAttributeForKey("awareness").getValue()+
                        .03*getAttributeForKey("strength").getValue()+.06*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue());
            }
            case SmallForward:{
                return (int)(   .07*getAttributeForKey("speed").getValue()+.07*getAttributeForKey("layup").getValue()+
                        .07*getAttributeForKey("close").getValue()+.07*getAttributeForKey("midrange").getValue()+
                        .07*getAttributeForKey("threes").getValue()+.07*getAttributeForKey("passing").getValue()+
                        .07*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .07*getAttributeForKey("steal").getValue()+.07*getAttributeForKey("block").getValue()+
                        .07*getAttributeForKey("rebounding").getValue()+.07*getAttributeForKey("awareness").getValue()+
                        .07*getAttributeForKey("strength").getValue()+.07*getAttributeForKey("vertical").getValue()+
                        .07*getAttributeForKey("stamina").getValue());
            }
            case PowerForward:{
                return (int)(   .05*getAttributeForKey("speed").getValue()+.14*getAttributeForKey("layup").getValue()+
                        .12*getAttributeForKey("close").getValue()+.09*getAttributeForKey("midrange").getValue()+
                        .03*getAttributeForKey("threes").getValue()+.05*getAttributeForKey("passing").getValue()+
                        .02*getAttributeForKey("dribbling").getValue()+.07*getAttributeForKey("defending").getValue()+
                        .08*getAttributeForKey("steal").getValue()+.1*getAttributeForKey("block").getValue()+
                        .1*getAttributeForKey("rebounding").getValue()+.03*getAttributeForKey("awareness").getValue()+
                        .07*getAttributeForKey("strength").getValue()+.03*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue());
            }
            case Center:{
                return (int)(   .04*getAttributeForKey("speed").getValue()+.18*getAttributeForKey("layup").getValue()+
                        .08*getAttributeForKey("close").getValue()+.04*getAttributeForKey("midrange").getValue()+
                        .02*getAttributeForKey("threes").getValue()+.04*getAttributeForKey("passing").getValue()+
                        .02*getAttributeForKey("dribbling").getValue()+.1*getAttributeForKey("defending").getValue()+
                        .04*getAttributeForKey("steal").getValue()+.15*getAttributeForKey("block").getValue()+
                        .15*getAttributeForKey("rebounding").getValue()+.02*getAttributeForKey("awareness").getValue()+
                        .08*getAttributeForKey("strength").getValue()+.02*getAttributeForKey("vertical").getValue()+
                        .02*getAttributeForKey("stamina").getValue());
            }
        }
        return 60;
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