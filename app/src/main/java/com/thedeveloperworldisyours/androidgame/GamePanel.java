package com.thedeveloperworldisyours.androidgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.thedeveloperworldisyours.androidgame.object.Helicopter;

/**
 * Created by javiergonzalezcabezas on 8/4/17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread mMainThread;
    private static final String TAG = "GamePanel";
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -5;
    public Background mBackground;
    public Helicopter mHelicopter;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        mMainThread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mBackground = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.grassbg1));
        mHelicopter = new Helicopter(BitmapFactory.decodeResource(getResources(), R.drawable.helicopter), 65, 25, 3);

        mBackground.setVector(MOVESPEED);

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        boolean retry = true;
        while (retry) {
            try {
                mMainThread.setRunning(retry);
                mMainThread.join();

            } catch (InterruptedException exception) {
                exception.fillInStackTrace();
            }

            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (!mHelicopter.getmPlaying()) {
                mHelicopter.setmPlaying(true);
            } else {
                mHelicopter.setmUp(true);
            }
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            mHelicopter.setmUp(false);
        }

        return true;
    }

    public void update() {
        if (mHelicopter.getmPlaying()) {
            mBackground.update();
            mHelicopter.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {

        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);

        if (canvas != null) {

            final int savedStated = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            mBackground.draw(canvas);
            mHelicopter.draw(canvas);

            canvas.restoreToCount(savedStated);
        }
    }

    public void onResume() {

        mMainThread.setRunning(true);
        if (mMainThread.getState() == Thread.State.NEW) {
            mMainThread.start();
        } else {
           mMainThread.onResume();

        }
    }

    public void onPause() {
        mMainThread.onPause();
//        mMainThread.setRunning(false);
    }
}
