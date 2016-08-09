package com.sportguys.planetbasketball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * Created by uytre_000 on 8/8/2016.
 */
public class PlayerAdapter extends ArrayAdapter<Player> {
    public static class ViewHolder{
        TextView nameText;
        TextView positionText;
        TextView overallText;
        ImageView faceView;
    }
    public PlayerAdapter(Context context, ArrayList<Player> players){
        super(context,0, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Player p = getItem(position);
        ViewHolder v;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);
            v=new ViewHolder();
            v.nameText = (TextView)(convertView.findViewById(R.id.name_text));
            v.positionText = (TextView)(convertView.findViewById(R.id.position_text));
            v.overallText = (TextView)(convertView.findViewById(R.id.overall_text));
            v.faceView = (ImageView)(convertView.findViewById(R.id.list_face));
            convertView.setTag(v);
        }else{
            v=(ViewHolder)convertView.getTag();
        }

        v.nameText.setText(p.getName());
        v.positionText.setText(p.getPositionString());
        v.overallText.setText(p.getOverallString());
        v.faceView.setImageDrawable(p.getFace());
        return convertView;
    }
}
