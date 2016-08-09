package com.sportguys.planetbasketball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

public class TeamViewActivity extends AppCompatActivity {
    private Team team;
    public static final String PLAYER_ID_EXTRA = "com.sportguys.planetbasketball.Player Identifier";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_team_view);
        team=DataHolder.getTeam();
        setTitle(team.getName());
    }
    public Team getTeam(){
        return team;
    }
}
