package com.sportguys.planetbasketball;

import android.content.Context;

/**
 * Created by uytre_000 on 8/8/2016.
 */
public class DataHolder
{
    private static League league;
    private static Team team;
    private static Player player;
    private static DBHelper databaseHelper;

    public static DBHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public static void setDatabaseHelper(DBHelper databaseHelper) {
        DataHolder.databaseHelper = databaseHelper;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        DataHolder.player = player;
    }

    private static Context context;

    public static League getLeague() {
        return league;
    }
    public static void setContext(Context c){
        context=c;
    }
    public static Context getContext(){
        return context;
    }
    public static void setLeague(League league) {
        DataHolder.league = league;
    }

    public static Team getTeam() {
        return team;
    }

    public static void setTeam(Team team) {
        DataHolder.team = team;
    }
}
