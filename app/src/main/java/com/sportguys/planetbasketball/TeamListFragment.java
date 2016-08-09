package com.sportguys.planetbasketball;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/**
 * Created by uytre_000 on 8/8/2016.
 */
public class TeamListFragment extends ListFragment {
    private League league;
    private TeamAdapter adapter;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LeagueViewActivity a = (LeagueViewActivity)(getActivity());
        league=a.getLeague();
        adapter=new TeamAdapter(getActivity(), league.getTeams());

        setListAdapter(adapter);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        launchPlayerDetailActivity(position);
    }

    private void launchPlayerDetailActivity(int position)
    {
        Team t = (Team) getListAdapter().getItem(position);
        DataHolder.setTeam(t);
        Intent intent = new Intent(getActivity(), TeamViewActivity.class);

        startActivity(intent);
    }
}
