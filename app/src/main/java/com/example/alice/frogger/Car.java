package com.example.alice.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Car extends Entity {

    public Car(float x, float y) {
        super(x, y);
        w = 1.0f / 10.0f;
        h = 1.0f / 10.0f;
        v = DEFAULT_SPEED * 1;
    }

    @Override
    public void draw(Canvas c, Paint p) {
        float cw = c.getWidth();
        float ch = c.getHeight();

        p.setColor(Color.GRAY);
        c.drawRect(x * cw, y * ch, (x + w) * cw, (y + h) * ch, p);
    }
}
