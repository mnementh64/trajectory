package net.mnementh.trajectory;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Responsible for screen painting.
 */
public class DisplayThread extends Thread {
    private final SurfaceHolder surfaceHolder;
    private final Paint backgroundPaint;

    long _sleepTime;

    //Delay amount between screen refreshes
    final long DELAY_IN_MILLIS = 20;

    boolean isRunning;

    public DisplayThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;

        //black painter below to clear the screen before the game is rendered
        backgroundPaint = new Paint();
        backgroundPaint.setARGB(255, 0, 0, 0);
        isRunning = true;
    }

    /**
     * This is the main nucleus of our program.
     * From here will be called all the method that are associated with the display in GameEngine object
     */
    @Override
    public void run() {
        //Looping until the boolean is false
        while (isRunning) {

            //Updates the game objects buisiness logic
            GameEngine.getInstance().update(System.currentTimeMillis());

            //locking the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);

            if (canvas != null) {
                synchronized (surfaceHolder) {
                    // clear background
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);

                    // render game components
                    GameEngine.getInstance().draw(canvas);
                }

                //unlocking the Canvas
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            //delay time
            try {
                Thread.sleep(DELAY_IN_MILLIS);
            } catch (InterruptedException ex) {
                //TODO: Log
            }
        }
    }

    /**
     * @return whether the thread is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Sets the thread state, false = stoped, true = running
     **/
    public void setIsRunning(boolean state) {
        isRunning = state;
    }
}