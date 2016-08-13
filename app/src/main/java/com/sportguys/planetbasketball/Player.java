package com.sportguys.planetbasketball;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.util.TypedValue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by uytre_000 on 7/2/2016.
 */
enum Position
{
    PointGuard, ShootingGuard, SmallForward, PowerForward, Center
}


public class Player implements Comparable<Player>, Serializable
{

    private static LinkedHashMap<Position, Double> averageHeights = AttributeList.getAverageHeights();
    private static LinkedHashMap<Position, Double> devHeights = AttributeList.getDevHeights();
    private Position position;
    private int positionInt;
    private AttributeList attributeList;
    private double age;
    private String name;
    private byte[] face;
    private double height;
    private Team team;
    private int id;
    private String teamName;
    //IN INCHES
    private static ArrayList<String> firstNames=App.getFirstNames();
    private static ArrayList<String> lastNames=App.getLastNames();
    private static LinkedHashMap<String, Double> pointBoosts = new LinkedHashMap<>();
    static {
        pointBoosts.put("layup", 30.0);
        pointBoosts.put("close", 25.0);
        pointBoosts.put("midrange", 25.0);
        pointBoosts.put("threes", 25.0);
        pointBoosts.put("passing", 45.0);
        pointBoosts.put("dribbling", 45.0);
        pointBoosts.put("defending", 25.0);
        pointBoosts.put("steal", 20.0);
        pointBoosts.put("awareness", 30.0);
        pointBoosts.put("stamina", 35.0);
    }
    private static LinkedHashMap<String, Double> shootingBoosts = new LinkedHashMap<>();
    static {
        shootingBoosts.put("layup", 30.0);
        shootingBoosts.put("close", 30.0);
        shootingBoosts.put("midrange", 40.0);
        shootingBoosts.put("threes", 40.0);
        shootingBoosts.put("passing", 25.0);
        shootingBoosts.put("dribbling", 25.0);
        shootingBoosts.put("defending", 30.0);
        shootingBoosts.put("steal", 25.0);
        shootingBoosts.put("block", 10.0);
        shootingBoosts.put("rebounding", 15.0);
        shootingBoosts.put("awareness", 25.0);
        shootingBoosts.put("strength", 15.0);
        shootingBoosts.put("stamina", 35.0);
    }
    private static LinkedHashMap<String, Double> smallBoosts = new LinkedHashMap<>();
    static {
        smallBoosts.put("layup", 35.0);
        smallBoosts.put("close", 35.0);
        smallBoosts.put("midrange", 35.0);
        smallBoosts.put("threes", 25.0);
        smallBoosts.put("speed", 25.0);
        smallBoosts.put("passing", 25.0);
        smallBoosts.put("dribbling", 25.0);
        smallBoosts.put("defending", 30.0);
        smallBoosts.put("steal", 25.0);
        smallBoosts.put("block", 25.0);
        smallBoosts.put("rebounding", 25.0);
        smallBoosts.put("awareness", 25.0);
        smallBoosts.put("strength", 25.0);
        smallBoosts.put("stamina", 35.0);
    }
    private static LinkedHashMap<String, Double> powerBoosts = new LinkedHashMap<>();
    static {
        powerBoosts.put("layup", 45.0);
        powerBoosts.put("close", 40.0);
        powerBoosts.put("midrange", 25.0);
        powerBoosts.put("threes", 5.0);
        powerBoosts.put("speed", 20.0);
        powerBoosts.put("passing", 15.0);
        powerBoosts.put("dribbling", 15.0);
        powerBoosts.put("defending", 25.0);
        powerBoosts.put("steal", 15.0);
        powerBoosts.put("block", 30.0);
        powerBoosts.put("rebounding", 40.0);
        powerBoosts.put("awareness", 20.0);
        powerBoosts.put("strength", 35.0);
        powerBoosts.put("stamina", 25.0);
    }
    private static LinkedHashMap<String, Double> centerBoosts = new LinkedHashMap<>();
    static {
        centerBoosts.put("layup", 45.0);
        centerBoosts.put("close", 30.0);
        centerBoosts.put("midrange", 15.0);
        centerBoosts.put("speed", 15.0);
        centerBoosts.put("passing", 10.0);
        centerBoosts.put("dribbling", 10.0);
        centerBoosts.put("defending", 25.0);
        centerBoosts.put("steal", 30.0);
        centerBoosts.put("block", 45.0);
        centerBoosts.put("rebounding", 50.0);
        centerBoosts.put("awareness", 15.0);
        centerBoosts.put("strength", 45.0);
        centerBoosts.put("stamina", 20.0);
    }
    public Player(){

    }

    public Player(Team team)
    {
        id = App.getCurrId();
        App.setCurrId(App.getCurrId()+1);
        this.team=team;
        teamName=team.getName();

        int dim = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, DataHolder.getContext().getResources().getDisplayMetrics());
        face=Faces.makeFace(dim, dim);
        age=PlayerAttribute.randNorm(20, 2);
        int temp = PlayerAttribute.randInt(0,4);
        name=firstNames.get(PlayerAttribute.randInt(0, firstNames.size()-1)) + " " + lastNames.get(PlayerAttribute.randInt(0, lastNames
                .size()-1));
        LinkedHashMap<String, Double> boosts;
        switch (temp){
            case (0): {
                position = Position.PointGuard;
                positionInt=1;
                boosts=pointBoosts;
                break;
            }
            case (1):{
                position=Position.ShootingGuard;
                positionInt=2;
                boosts=shootingBoosts;
                break;
            }
            case (2):{
                position=Position.SmallForward;
                positionInt=3;
                boosts=smallBoosts;
                break;
            }
            case (3):{
                position=Position.PowerForward;
                positionInt=4;
                boosts=powerBoosts;
                break;
            }
            case (4):{
                position=Position.Center;
                positionInt=5;
                boosts=centerBoosts;
                break;
            }
            default:{
                position=Position.SmallForward;
                boosts=smallBoosts;
                break;
            }
        }
        height = PlayerAttribute.randNorm(averageHeights.get(position), devHeights.get(position));
        DataHolder.setPlayer(this);
        attributeList = new AttributeList(position, (int) PlayerAttribute.randNorm(50, 15), age, height, boosts);
    }

    @Override
    public String toString() {
        return "Age: " + (int)age + "\nHeight: " + getHeightString()+"\nOverall: " +getOverall() + "\n" + attributeList.toString();
    }
    public AttributeList getAttributes(){
        return attributeList;
    }
    public int getPositionInt(){
        return positionInt;
    }
    public int compareTo(Player p)
    {
        int ret=positionInt-p.getPositionInt();
        if(ret==0){
            return p.getOverall()-getOverall();
        }
        else{
            return ret;
        }
    }
    public String getName()
    {
        return name;
    }
    public Drawable getFaceDrawble(){
        return Faces.bitmapToDrawable(Faces.byteArrayToBitmap(face));
    }
    public String getPositionString(){
        switch (positionInt){
            case(1):{
                return "Point Guard";
            }
            case(2):{
                return "Shooting Guard";
            }
            case(3):{
                return "Small Forward";
            }
            case(4):{
                return "Power Forward";
            }
            case(5):{
                return "Center";
            }


        }
        return "Small Forward";
    }
    public Position getPosition(){
        return position;
    }
    public int getOverall(){
        return attributeList.getOverall(position);
    }
    public String getOverallString(){
        return getOverall()+" OVR";
    }
    public String getHeightString(){
        int currHeight=(int)height;
        return currHeight/12+"' " + currHeight%12 + '"';
    }

    public Team getTeam() {
        return team;
    }
    public int getAgeInt(){
        return (int)age;
    }
    public double getAge() { return age; }
    public double getHeight() { return height; }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPositionInt(int positionInt) {
        this.positionInt = positionInt;

        switch (positionInt){
            case 1:{
                position=Position.PointGuard;
                break;
            }
            case 2:{
                position=Position.ShootingGuard;
                break;
            }
            case 3:{
                position=Position.SmallForward;
                break;
            }
            case 4:{
                position=Position.PowerForward;
                break;
            }
            case 5:{
                position=Position.Center;
                break;
            }
            default:{
                position=Position.SmallForward;
                break;
            }

        }
    }
    public void recalculateSize(){
        attributeList.genSize(attributeList.getAttributeForKey("size"), height);
    }
    public void setAttributeList(AttributeList attributeList) {
        this.attributeList = attributeList;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFace(byte[] face) {
        this.face = face;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public byte[] getFace() { return face; }
    public String getAgeString(){
        return getAgeInt() + " years old";
    }

    public String getTeamName() {
        return teamName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if(id>App.getCurrId())
            App.setCurrId(id+1);
    }

}
