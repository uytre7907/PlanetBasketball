package com.sportguys.planetbasketball;

import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Created by uytre_000 on 8/8/2016.
 */
public class League implements Serializable
{
    private static ArrayList<String> nbaNames=App.getNBANames();
    private String name;
    private ArrayList<Team> teams;
    public League(){
        teams=new ArrayList<>();
        for(String name:nbaNames){
            teams.add(new Team(name, false));
        }
        Collections.sort(teams);
    }
    public League(String name, boolean exists){
        teams=new ArrayList<>();
        if(!exists) {
            for(String teamName:nbaNames){
                teams.add(new Team(teamName, exists));
            }
            Collections.sort(teams);
            DBHelper dbHelper=DataHolder.getDatabaseHelper();
            for(Team t:teams){
                for(Player p:t.getPlayers()){
                    dbHelper.addPlayer(p);
                }
            }
        }
        else{
            for(String teamName:nbaNames){
                Team tempTeam = new Team(teamName, exists);
                tempTeam.setPlayers(DataHolder.getDatabaseHelper().getPlayers(tempTeam));
                teams.add(tempTeam);
            }
        }
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
