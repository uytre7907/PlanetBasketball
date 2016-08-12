package com.sportguys.planetbasketball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class LeagueViewActivity extends AppCompatActivity {
    private League league;
    public static final String TEAM_ID_EXTRA = "com.sportguys.planetbasketball.Team Identifier";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_league_view);
        if(DataHolder.getLeague()==null){
            league=new League("NBA", DataHolder.getDatabaseHelper().exists());
            DataHolder.setLeague(league);
        }
        else{
            league=DataHolder.getLeague();
        }
        setTitle(league.getName());
    }
    public League getLeague(){
        return league;
    }
}
