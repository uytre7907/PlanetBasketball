package com.sportguys.planetbasketball;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Created by uytre_000 on 8/8/2016.
 */
public class League
{
    private static ArrayList<String> nbaNames=App.getNBANames();
    private String name;
    private ArrayList<Team> teams;
    public League(){
        teams=new ArrayList<>();
        for(String name:nbaNames){
            teams.add(new Team(name));
        }
        Collections.sort(teams);
    }
    public League(String name){
        this();
        this.name=name;
    }
    public String toString()
    {
        String out = "";
        for(Team t:teams)
        {
            out+=t.toString()+ "\n";
        }
        return out;
    }
    public ArrayList<Team> getTeams(){
        return teams;
    }
    public String getName(){
        return name;
    }
}
