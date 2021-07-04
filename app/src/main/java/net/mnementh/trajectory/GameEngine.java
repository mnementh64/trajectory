package net.mnementh.trajectory;

import android.graphics.Canvas;
import android.graphics.Paint;

public class GameEngine {

    // I don't like singletons ...
    private static GameEngine INSTANCE;

    public static GameEngine getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameEngine();
        }
        return INSTANCE;
    }

    /**
     * Draw all components of the game
     */
    public void draw(Canvas canvas) {
        Paint color = new Paint();
        color.setARGB(255, 255, 0, 0);
        canvas.drawRect(100, 100, 50, 50, color);
        canvas.drawRect(200, 200, 50, 50, color);

        color.setARGB(255, 255, 255, 255);
        color.setStrokeWidth(5f);
        canvas.drawLine(400f, 400f, 200f, 200f, color);

        color.setARGB(255, 25, 123, 0);
        canvas.drawRect(300, 300, 250, 250, color);
    }

    /**
     * Update the game components
     *
     * @param time
     */
    public void update(long time) {

    }
}
