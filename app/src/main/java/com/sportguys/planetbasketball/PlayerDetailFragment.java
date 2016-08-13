package com.sportguys.planetbasketball;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collections;


/*
 * A simple {@link Fragment} subclass.
 */
public class PlayerDetailFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    Player p;
    TextView attr;
    private static String[] positions={"Point Guard", "Shooting Guard", "Small Forward", "Power Forward", "Center"};
    public PlayerDetailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_player_detail, container, false);
        attr = (TextView)fragmentLayout.findViewById(R.id.attribute_detail_text);
        ImageView faceView = (ImageView)fragmentLayout.findViewById(R.id.detail_face);
        Spinner spinner = (Spinner)(fragmentLayout.findViewById(R.id.position_spinner));

        p=DataHolder.getPlayer();
        getActivity().setTitle(p.getName());
        attr.setText(p.toString());
        faceView.setImageDrawable(p.getFaceDrawble());


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.custom_spinner, positions);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(p.getPositionInt()-1);
        spinner.setOnItemSelectedListener(this);

        return fragmentLayout;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        p.setPositionInt(i+1);
        p.getAttributes().setPosition(p.getPosition());
        p.recalculateSize();
        attr.setText(p.toString());
        Collections.sort(p.getTeam().getPlayers());
        DataHolder.getDatabaseHelper().updatePlayer(p);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}
