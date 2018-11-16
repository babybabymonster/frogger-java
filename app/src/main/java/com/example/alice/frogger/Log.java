package com.example.alice.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Log extends Entity {

    public Log(float x, float y) {
        super(x, y);
        w = 1.0f / 6.0f;
        h = 1.0f / 20.0f;
        v = DEFAULT_SPEED * 0.6f; //???
    }

    @Override
    public void draw(Canvas c, Paint p, Bitmap logp) {

        float cw = c.getWidth();
        float ch = c.getHeight();

        logp = Bitmap.createScaledBitmap(logp, (int) (w * cw), (int) (h * ch), true);
        c.drawBitmap(logp, x * cw, y * ch, p);

        // temp
//        p.setColor(Color.LTGRAY);
//        c.drawRect(x * cw, y * ch, (x + w) * cw, (y + h) * ch, p);
    }
}
