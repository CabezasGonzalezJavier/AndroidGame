package com.thedeveloperworldisyours.androidgame.object;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.thedeveloperworldisyours.androidgame.GamePanel;

/**
 * Created by javiergonzalezcabezas on 14/5/17.
 */

public class Helicopter extends GameObject {
    private int score;
    private boolean mUp;
    private boolean mPlaying;
    private Animation animation = new Animation();
    private long startTime;

    private final static int MAXIMUM = 10;

    public Helicopter(Bitmap verticalSprites, int width, int height, int numFrames) {

        super(100, GamePanel.HEIGHT / 2, width, height);

        dy = 0;
        score = 0;

        Bitmap[] bitmaps = new Bitmap[numFrames];
        for (int i = 0; i < numFrames; i++) {
            bitmaps[i] = Bitmap.createBitmap(verticalSprites, i * width, 0, width, height);
        }

        animation.setFrames(bitmaps);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }


    public void update() {
        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();

        if (mUp) {
            dy -= 1;
        } else {
            dy += 1;
        }

        if (dy > MAXIMUM) {
            dy = MAXIMUM;
        }
        if (dy < -MAXIMUM) {
            dy = -MAXIMUM;
        }

        ejeY += dy;
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), ejeX, ejeY, null);
    }


    public void setmUp(boolean booleanUp) {
        mUp = booleanUp;
    }

    public void resetDY() {
        dy = 0;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public boolean getmPlaying() {
        return mPlaying;
    }

    public void setmPlaying(boolean playing) {
        this.mPlaying = playing;
    }
}