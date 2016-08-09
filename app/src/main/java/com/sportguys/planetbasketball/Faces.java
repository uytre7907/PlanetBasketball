package com.sportguys.planetbasketball;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;

/*
 * Created by uytre_000 on 8/8/2016.
 */
public class Faces {
    private static int[] faceColors;

    public static LayerDrawable makeFace(int height, int width){

        int mult = PlayerAttribute.randInt(10,10);
        ShapeDrawable hair = new ShapeDrawable(new OvalShape());
        hair.setIntrinsicWidth(width*PlayerAttribute.randInt(5,8)/10);
        hair.setIntrinsicHeight(width*PlayerAttribute.randInt(10,16)/20);
        hair.getPaint().setColor(Color.BLACK);
        int hairTemp=PlayerAttribute.randInt(1,1);
        int hairEdges=0;
        switch (hairTemp){
            case 0:{
                hairEdges=width/mult;
            }
            case 1:{
                hairEdges=PlayerAttribute.randInt(0, width/mult);

            }
        }


        ShapeDrawable head = new ShapeDrawable(new OvalShape());
        head.setIntrinsicHeight(height);
        head.setIntrinsicWidth(width);
        int darkestIndex = (PlayerAttribute.randInt(0, (faceColors.length-1)/2))*2+1;
        head.getPaint().setColor(PlayerAttribute.randInt(faceColors[darkestIndex], faceColors[darkestIndex-1]));

        ShapeDrawable eye = new ShapeDrawable(new OvalShape());
        eye.setIntrinsicWidth(width/mult);
        eye.setIntrinsicHeight(height/mult);
        eye.getPaint().setColor(Color.WHITE);

        ShapeDrawable eye2 = new ShapeDrawable(new OvalShape());
        eye2.setIntrinsicWidth(width/mult);
        eye2.setIntrinsicHeight(height/mult);
        eye2.getPaint().setColor(Color.WHITE);

        float angle;
        if(PlayerAttribute.randInt(0,1)==0)
            angle=180;
        else
            angle=360;
        ShapeDrawable mouth = new ShapeDrawable(new ArcShape(0, angle));
        mouth.setIntrinsicHeight(width*3/2/mult);
        mouth.setIntrinsicWidth(width*7/2/mult);
        mouth.getPaint().setColor(Color.WHITE);



        //MUST BE LAST
        ShapeDrawable mustache=null;
        boolean hasMustache=false;
        if(PlayerAttribute.randInt(0,3)==0) {
            hasMustache=true;
            Shape shape=null;
            shape=new ArcShape(-360, -180);
            mustache = new ShapeDrawable(shape);
            mustache.setIntrinsicHeight(width/(10*mult/19));
            mustache.setIntrinsicWidth(width/(10*mult/19));
            mustache.getPaint().setColor(hair.getPaint().getColor());
        }
        Drawable[] layers;
        if(hasMustache){
            Drawable[] temp = {hair, head, eye, eye2, mouth, mustache};
            layers=temp;
        }
        else{
            Drawable[] temp={hair, head, eye, eye2, mouth};
            layers=temp;
        }

        LayerDrawable l = new LayerDrawable(layers);
        l.setLayerInset(0, hairEdges, 0, hairEdges, width/(mult/5));
        l.setLayerInset(1, 0, width/mult, 0, 0);
        l.setLayerInset(2,width*2/mult,width*10/3/mult, width*8/mult, width*15/2/mult);
        l.setLayerInset(3,width*8/mult,width*10/3/mult, width*2/mult, width*15/2/mult);
        l.setLayerInset(4, width*6/(2*mult), width*7/mult, width*6/(2*mult), width*3/mult);
        if(hasMustache)
            l.setLayerInset(5, width/(mult), width*7/mult - width/10, width/(mult), width-width*7/mult);
        Log.d("Face Status", mult+"");
        return l;
    }
    public static void setFaceColors(int[] colors)
    {
        faceColors=colors;
    }
}
