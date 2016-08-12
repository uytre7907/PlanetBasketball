package com.sportguys.planetbasketball;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by uytre_000 on 7/14/2016.
 */
public class Team implements Comparable<Team>, Serializable
{
    private ArrayList<Player> players;
    private String name;
    public Team(String name, ArrayList<Player> players){
        this.players=players;
        this.name=name;
        Collections.sort(players);
    }
    public Team(String name, boolean exists)
    {
        this.name=name;
        if(!exists){
            players = new ArrayList<Player>();
            for(int i=0; i<14; i++)
            {
                players.add(new Player(this));
            }
            Collections.sort(players);
        }
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        Collections.sort(this.players);
    }

    public Team()
    {
        players = new ArrayList<Player>();
        for(int i=0; i<14; i++)
        {
            players.add(new Player(this));
        }
        Collections.sort(players);
    }
    public String toString()
    {
        String out = "";
        for(Player p:players)
        {
            out+=p.toString()+ "\n";
        }
        return out;
    }
    public ArrayList<Player> getPlayers()
    {
        return players;
    }
    public String getName(){
        return name;
    }
    public int compareTo(Team t)
    {
        return name.compareTo(t.getName());
    }
    public int getOverall(){
        int sum=0;
        for(Player p:players){
            sum+=p.getOverall();
        }
        return sum/players.size();
    }
    public String getOverallString(){
        return getOverall()+" OVR";
    }
}
