package com.thedeveloperworldisyours.androidgame.object;

import android.graphics.Rect;

/**
 * Created by javiergonzalezcabezas on 14/5/17.
 */

public abstract class GameObject {
    protected int ejeX;
    protected int ejeY;
    protected int dy;
    protected int dx;
    protected int width;
    protected int height;

    GameObject(int x, int y, int w, int h){
        this.ejeX =x;
        this.ejeY =y;
        this.width=w;
        this.height=h;
    }


    public void setEjeX(int ejeX) {
        this.ejeX = ejeX;
    }


    public void setEjeY(int ejeY) {
        this.ejeY = ejeY;
    }


    public int getEjeX() {
        return ejeX;
    }


    public int getEjeY() {
        return ejeY;
    }


    public int getHeight() {
        return height;
    }


    public int getWidth() {
        return width;
    }


    public Rect getRectangle() {
        return new Rect(ejeX, ejeY, ejeX +width, ejeY +height);
    }
}
