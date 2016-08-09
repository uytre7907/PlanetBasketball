package com.sportguys.planetbasketball;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
 * Created by uytre_000 on 8/7/2016.
 */
public class PlayerListFragment extends ListFragment {
    private Team team;
    private PlayerAdapter adapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        team=DataHolder.getTeam();
        adapter=new PlayerAdapter(getActivity(), team.getPlayers());

        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchPlayerDetailActivity(position);
    }

    private void launchPlayerDetailActivity(int position)
    {
        Player p = (Player) getListAdapter().getItem(position);
        Intent intent = new Intent(getActivity(), PlayerDetailActivity.class);
        DataHolder.setPlayer(p);

        startActivity(intent);
    }
}
