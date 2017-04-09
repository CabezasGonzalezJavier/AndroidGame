package com.thedeveloperworldisyours.androidgame;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by javiergonzalezcabezas on 8/4/17.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread mMainThread;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        mMainThread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mMainThread.setmRunning(true);
        mMainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        boolean retry = true;
        while (retry) {
            try {
                mMainThread.setmRunning(retry);
                mMainThread.join();

            } catch (InterruptedException exception) {
                exception.fillInStackTrace();
            }

            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void update() {

    }
}
