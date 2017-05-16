package com.thedeveloperworldisyours.androidgame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by javiergonzalezcabezas on 9/4/17.
 */

public class MainThread extends Thread {

    private static final String TAG = "MainThread";
    private int FPS = 30;
    private double mAverageFPS;
    private SurfaceHolder mSurfaceHolder;
    private GamePanel mGamePanel;
    private boolean mRunning;
    public static Canvas mCanvas;
    public boolean suspended;


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.mSurfaceHolder = surfaceHolder;
        this.mGamePanel = gamePanel;
    }


    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        int frameCount =0;
        long targetTime = 1000/FPS;

        while(mRunning) {
            startTime = System.nanoTime();
            mCanvas = null;

            //try locking the mCanvas for pixel editing
            try {
                mCanvas = this.mSurfaceHolder.lockCanvas();
                synchronized (mSurfaceHolder) {
                    mGamePanel.update();
                    mGamePanel.draw(mCanvas);
                }
            } catch (Exception e) {}
            finally{
                if(mCanvas !=null) {
                    try {
                        mSurfaceHolder.unlockCanvasAndPost(mCanvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime-timeMillis;

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;
            if(frameCount == FPS)
            {
                mAverageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                Log.i(TAG, "run: mAverageFPS = "+ mAverageFPS);
            }

            synchronized(this) {
                while(suspended) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    void onPause() {
        suspended = true;
    }

    synchronized void onResume() {
        suspended = false;
        notify();
    }

    public void setRunning(boolean myBoolean) {
        mRunning =myBoolean;
    }
}
