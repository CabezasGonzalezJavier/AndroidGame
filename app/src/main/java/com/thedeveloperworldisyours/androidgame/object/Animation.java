package com.thedeveloperworldisyours.androidgame.object;

import android.graphics.Bitmap;

/**
 * Created by javiergonzalezcabezas on 14/5/17.
 */

public class Animation {
    private Bitmap[] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean animatedOnce=false;


    public void setFrames(Bitmap[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }


    public void setDelay(long delay){
        delay = delay;
    }

    public void update() {
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if(elapsed>delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }

        if(currentFrame == frames.length){
            currentFrame = 0;
            animatedOnce = true;
        }
    }


    public Bitmap getImage(){return frames[currentFrame];}
    public int getFrame(){return currentFrame;}
    public boolean isAnimatedAlready(){return animatedOnce;}
}
