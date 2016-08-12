package com.sportguys.planetbasketball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        if(DataHolder.getLeague()==null){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    League league=new League("NBA", DataHolder.getDatabaseHelper().exists());
                    DataHolder.setLeague(league);
                    startActivity(new Intent(DataHolder.getContext(), LeagueViewActivity.class));
                }
            });
            thread.start();
        }
        else{
            startActivity(new Intent(DataHolder.getContext(), LeagueViewActivity.class));
        }
    }
}
