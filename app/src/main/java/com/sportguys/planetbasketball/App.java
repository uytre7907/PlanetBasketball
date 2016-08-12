package com.sportguys.planetbasketball;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by uytre_000 on 8/8/2016.
 */
public class App extends Application
{

    private static ArrayList<String> firstNames = new ArrayList<>();
    private static ArrayList<String> lastNames = new ArrayList<>();
    private static ArrayList<String> nbaNames = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        DataHolder.setContext(getApplicationContext());
        DataHolder.setDatabaseHelper(new DBHelper());
    }
    public static void setupApp(){
        int[] skinColors={ContextCompat.getColor(DataHolder.getContext(), R.color.minBlack),ContextCompat.getColor(DataHolder.getContext(), R.color.medBlack),ContextCompat.getColor(DataHolder.getContext(), R.color.maxBlack),ContextCompat.getColor(DataHolder.getContext(), R.color.minAsian),
                ContextCompat.getColor(DataHolder.getContext(), R.color.medAsian),ContextCompat.getColor(DataHolder.getContext(), R.color.maxAsian), ContextCompat.getColor(DataHolder.getContext(), R.color.minWhite),ContextCompat.getColor(DataHolder.getContext(), R.color.medWhite), ContextCompat.getColor(DataHolder.getContext(), R.color.maxWhite)};
        Faces.setFaceColors(skinColors);
        int[] eyeColors = {ContextCompat.getColor(DataHolder.getContext(), R.color.blueEye), ContextCompat.getColor(DataHolder.getContext(), R.color.brownEye), ContextCompat.getColor(DataHolder.getContext(), R.color.greenEye),ContextCompat.getColor(DataHolder.getContext(), R.color.hazelEye)};
        Faces.setEyeColors(eyeColors);
        int[] hairColors={  ContextCompat.getColor(DataHolder.getContext(), R.color.blackHair),ContextCompat.getColor(DataHolder.getContext(), R.color.blackHair),ContextCompat.getColor(DataHolder.getContext(), R.color.blackHair),ContextCompat.getColor(DataHolder.getContext(), R.color.blackHair),ContextCompat.getColor(DataHolder.getContext(), R.color.blackHair),ContextCompat.getColor(DataHolder.getContext(), R.color.blackHair),
                ContextCompat.getColor(DataHolder.getContext(), R.color.lightBrownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.brownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.darkBrownHair),ContextCompat.getColor(DataHolder.getContext(), R.color.lightBrownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.brownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.darkBrownHair),
                ContextCompat.getColor(DataHolder.getContext(), R.color.lightBrownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.brownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.darkBrownHair),ContextCompat.getColor(DataHolder.getContext(), R.color.lightBrownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.brownHair), ContextCompat.getColor(DataHolder.getContext(), R.color.darkBrownHair),
                ContextCompat.getColor(DataHolder.getContext(), R.color.blondeHair), ContextCompat.getColor(DataHolder.getContext(), R.color.lightBlondeHair), ContextCompat.getColor(DataHolder.getContext(), R.color.dirtyBlondHair),
                ContextCompat.getColor(DataHolder.getContext(), R.color.dirtyDirtyBlondeHair),};
        Faces.setHairColors(hairColors);
        String result="";
        try {
            InputStream i = DataHolder.getContext().getResources().openRawResource(R.raw.male_first);
            byte[] bytes = new byte[i.available()];
            i.read(bytes);
            result=new String(bytes);
        }catch (Exception e) {}
        BufferedReader br=null;
        try{
            br = new BufferedReader(new StringReader(result));
            String temp;
            while ((temp = br.readLine()) != null) {
                String name = temp.substring(0, temp.indexOf(' '));
                name=name.substring(0,1)+name.substring(1).toLowerCase();
                firstNames.add(name);
            }
            br.close();
        }catch (Exception e){}
        try {
            InputStream i = DataHolder.getContext().getResources().openRawResource(R.raw.last_names);
            byte[] bytes = new byte[i.available()];
            i.read(bytes);
            result=new String(bytes);
        }catch (Exception e) {}
        try{
            br = new BufferedReader(new StringReader(result));
            String temp;
            while ((temp = br.readLine()) != null) {
                String name = temp.substring(0, temp.indexOf(' '));
                name=name.substring(0,1)+name.substring(1).toLowerCase();
                lastNames.add(name);
            }
            br.close();
        }catch (Exception e){}
        try {
            InputStream i = DataHolder.getContext().getResources().openRawResource(R.raw.nba_names);
            byte[] bytes = new byte[i.available()];
            i.read(bytes);
            result=new String(bytes);
        }catch (Exception e) {}
        try{
            br = new BufferedReader(new StringReader(result));
            String temp;
            while ((temp = br.readLine()) != null) {
                nbaNames.add(temp);
            }
            br.close();
        }catch (Exception e){}
    }
    public static ArrayList<String> getNBANames(){
        return nbaNames;
    }
    public static ArrayList<String> getFirstNames(){
        return firstNames;
    }
    public static ArrayList<String> getLastNames(){
        return lastNames;
    }
}
