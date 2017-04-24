package com.thedeveloperworldisyours.androidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import static com.thedeveloperworldisyours.androidgame.GamePanel.WIDTH;

/**
 * Created by javiergonzalezcabezas on 23/4/17.
 */

public class Background {
    private Bitmap mImage;
    private int x, y, mDx;

    public Background(Bitmap res) {
        mImage = res;
    }


    public void update() {
        x+= mDx;
        if(x<-WIDTH){
            x=0;
        }
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(mImage, x, y,null);
        // If part of the background is now off screen, path with second mImage displaced
        if(x<0)
            canvas.drawBitmap(mImage, x+ WIDTH, y, null);
    }


    public void setVector(int dx) {
        this.mDx = dx;
    }

}
