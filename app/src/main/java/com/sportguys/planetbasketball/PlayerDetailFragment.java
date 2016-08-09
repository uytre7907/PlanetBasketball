package com.sportguys.planetbasketball;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/*
 * A simple {@link Fragment} subclass.
 */
public class PlayerDetailFragment extends Fragment {
    Player p;
    public PlayerDetailFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_player_detail, container, false);
        TextView attr = (TextView)fragmentLayout.findViewById(R.id.attribute_detail_text);
        ImageView faceView = (ImageView)fragmentLayout.findViewById(R.id.detail_face);

        p=DataHolder.getPlayer();
        getActivity().setTitle(p.getName());
        attr.setText(p.toString());
        faceView.setImageDrawable(p.getFace());
        return fragmentLayout;
    }
}
