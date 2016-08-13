package com.sportguys.planetbasketball;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class PlayerDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player_detail);
        createAndAddFragment();

    }

    private void createAndAddFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PlayerDetailFragment playerDetailFragment= new PlayerDetailFragment();
        fragmentTransaction.add(R.id.player_container, playerDetailFragment, "PLAYER_DETAIL_FRAGMENT");

        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {

    }
}
