package com.sportguys.planetbasketball;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.OvalShape;

/**
 * Created by uytre_000 on 8/8/2016.
 */
public class Faces {
    private static int[] faceColors;

    public static LayerDrawable makeFace(int height, int width){
        ShapeDrawable head = new ShapeDrawable(new OvalShape());
        head.setIntrinsicHeight(height);
        head.setIntrinsicWidth(width);
        int darkestIndex = (PlayerAttribute.randInt(0, (faceColors.length-1)/2))*2+1;
        head.getPaint().setColor(PlayerAttribute.randInt(faceColors[darkestIndex], faceColors[darkestIndex-1]));

        int mult = PlayerAttribute.randInt(9,11);
        ShapeDrawable eye = new ShapeDrawable(new OvalShape());
        eye.getShape().resize(width/mult, height/mult);
        eye.setIntrinsicWidth(width/mult);
        eye.setIntrinsicHeight(height/mult);
        eye.getPaint().setColor(Color.WHITE);

        ShapeDrawable eye2 = new ShapeDrawable(new OvalShape());
        eye2.getShape().resize(width/mult, height/mult);
        eye2.setIntrinsicWidth(width/mult);
        eye2.setIntrinsicHeight(height/mult);
        eye2.getPaint().setColor(Color.WHITE);

        float angle;
        if(PlayerAttribute.randInt(0,1)==0)
            angle=180;
        else
            angle=360;
        ShapeDrawable mouth = new ShapeDrawable(new ArcShape(0, angle));
        mouth.setIntrinsicHeight(width*2/mult);
        mouth.setIntrinsicWidth(width*3/mult);
        mouth.getPaint().setColor(Color.WHITE);


        Drawable[] layers = {head, eye, eye2, mouth};
        LayerDrawable l = new LayerDrawable(layers);
        l.setLayerInset(1,width*2/mult,width*2/mult, width*8/mult, width*6/mult);
        l.setLayerInset(2,width*8/mult,width*2/mult, width*2/mult, width*6/mult);
        l.setLayerInset(3, width*7/(2*mult), width*6/mult, width*7/(2*mult), width*2/mult);
        return l;
    }
    public static void setFaceColors(int[] colors)
    {
        faceColors=colors;
    }
}