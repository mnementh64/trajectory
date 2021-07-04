package com.minar.tactoetic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Responsible for screen painting.
 */
public class DisplayThread extends Thread {
    SurfaceHolder _surfaceHolder;
    Paint _backgroundPaint;

    long _sleepTime;

    //Delay amount between screen refreshes
    final long DELAY = 4;

    boolean _isOnRun;

    public DisplayThread(SurfaceHolder surfaceHolder) {
        _surfaceHolder = surfaceHolder;

        //black painter below to clear the screen before the game is rendered
        _backgroundPaint = new Paint();
        _backgroundPaint.setARGB(255, 0, 0, 0);
        _isOnRun = true;
    }

    /**
     * This is the main nucleus of our program.
     * From here will be called all the method that are associated with the display in GameEngine object
     */
    @Override
    public void run() {
        //Looping until the boolean is false
        while (_isOnRun) {

            //locking the canvas
            Canvas canvas = _surfaceHolder.lockCanvas(null);

            if (canvas != null) {
                //Clears the screen with black paint and draws object on the canvas
                synchronized (_surfaceHolder) {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), _backgroundPaint);

                    Paint color = new Paint();
                    color.setARGB(255, 255, 0, 0);
                    canvas.drawRect(100, 100, 50, 50, color);
                    canvas.drawRect(200, 200, 50, 50, color);
                    canvas.drawRect(300, 300, 50, 50, color);
//                    AppConstants.GetEngine().Draw(canvas);
                }

                //unlocking the Canvas
                _surfaceHolder.unlockCanvasAndPost(canvas);
            }

            //delay time
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException ex) {
                //TODO: Log
            }
        }
    }

    /**
     * @return whether the thread is running
     */
    public boolean IsRunning() {
        return _isOnRun;
    }

    /**
     * Sets the thread state, false = stoped, true = running
     **/
    public void SetIsRunning(boolean state) {
        _isOnRun = state;
    }
}