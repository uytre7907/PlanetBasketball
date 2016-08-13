package com.sportguys.planetbasketball;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uytre_000 on 8/10/2016.
 */
public class DBHelper extends SQLiteOpenHelper{
    public final static String DB_NAME = "players";
    public final static String PLAYER_ID = "id";
    public final static String PLAYER_NAME = "name";
    public final static String PLAYER_TEAM_NAME = "team_name";
    public final static String PLAYER_AGE = "age";
    public final static String PLAYER_HEIGHT = "height";
    public final static String PLAYER_POSITION_INT = "position_int";
    public final static String PLAYER_FACE = "face";

    private static String[] attributeNames = AttributeList.getAttributeNames();
    private static String attributeCode="";
    static {
        for(String s:attributeNames){
            attributeCode=attributeCode+s+" TEXT, ";
        }
    }
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE " + DB_NAME + " (" +
                    PLAYER_ID + " TEXT, " +
                    PLAYER_NAME + " TEXT, " +
                    PLAYER_TEAM_NAME + " TEXT, " +
                    PLAYER_AGE + " TEXT, " +
                    PLAYER_HEIGHT + " TEXT, " +
                    PLAYER_POSITION_INT + " TEXT, " +
                    attributeCode +
                    PLAYER_FACE + " ARRAY);";
    public final static int DATABASE_VERSION = 1;
    public final static String DB_ID = "_id";



    public DBHelper() {
        super(DataHolder.getContext(), DB_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DICTIONARY_TABLE_CREATE);
    }

    public void addPlayer(Player p){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_ID, p.getId()+"");
        values.put(PLAYER_NAME, p.getName());
        values.put(PLAYER_TEAM_NAME, p.getTeamName());
        values.put(PLAYER_AGE, p.getAge()+"");
        values.put(PLAYER_HEIGHT, p.getHeight()+"");
        values.put(PLAYER_POSITION_INT, p.getPositionInt()+"");
        for(String s:attributeNames){
            values.put(s, p.getAttributes().getAttributeForKey(s).getValue()+"");
        }
        values.put(PLAYER_FACE, p.getFace());
        database.insert(DB_NAME, null, values);
        database.close();
    }
    public ArrayList<Player> getPlayers(Team team){
        ArrayList<Player> playerList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(team.getName())) {
                    Player p = new Player();
                    int count=0;
                    p.setId(Integer.parseInt(cursor.getString(count++)));
                    p.setName(cursor.getString(count++));
                    p.setTeam(team);
                    p.setTeamName(team.getName());
                    count++;
                    p.setAge(Double.parseDouble(cursor.getString(count++)));
                    p.setHeight(Double.parseDouble(cursor.getString(count++)));
                    p.setPositionInt(Integer.parseInt(cursor.getString(count++)));
                    List<PlayerAttribute> playerAttributes = new ArrayList<>();
                    for(String s:attributeNames) {
                        playerAttributes.add(new PlayerAttribute(s, Double.parseDouble(cursor.getString(count++))));
                    }
                    p.setAttributeList(new AttributeList(playerAttributes, p.getPosition()));
                    p.setFace(cursor.getBlob(count));
                    playerList.add(p);
                }

            }while (cursor.moveToNext());

        }
        return playerList;

    }
    public int updatePlayer(Player p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PLAYER_ID, p.getId()+"");
        values.put(PLAYER_NAME, p.getName());
        values.put(PLAYER_TEAM_NAME, p.getTeamName());
        values.put(PLAYER_AGE, p.getAge()+"");
        values.put(PLAYER_HEIGHT, p.getHeight()+"");
        values.put(PLAYER_POSITION_INT, p.getPositionInt()+"");
        for(String s:attributeNames){
            values.put(s, p.getAttributes().getAttributeForKey(s).getValue()+"");
        }
        values.put(PLAYER_FACE, p.getFace());
// updating row
        return db.update(DB_NAME, values, PLAYER_ID + " = ?",
                new String[]{String.valueOf(p.getId()+"")});
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean exists(){
        String selectQuery = "SELECT * FROM " + DB_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor.moveToFirst();
    }

}
