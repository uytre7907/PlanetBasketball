package com.sportguys.planetbasketball;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uytre_000 on 8/8/2016.
 */
public class TeamAdapter extends ArrayAdapter<Team>{
    public static class ViewHolder{
        TextView nameText;
        TextView overallText;
    }
    public TeamAdapter(Context context, ArrayList<Team> teams){
        super(context,0, teams);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Team t = getItem(position);
        ViewHolder v;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.team_row, parent, false);
            v=new ViewHolder();
            v.nameText = (TextView)(convertView.findViewById(R.id.team_name_text));
            v.overallText = (TextView)(convertView.findViewById(R.id.team_overall_text));
            convertView.setTag(v);
        }else{
            v=(ViewHolder)convertView.getTag();
        }

        v.nameText.setText(t.getName());
        v.overallText.setText(t.getOverallString());

        return convertView;
    }
}
