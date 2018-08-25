package com.example.alice.frogger;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Frog extends Entity {

    public Frog(float x, float y) {
        super(x, y);
        w = 1.0f / 10.0f;
        h = 1.0f / 10.0f;
        v = DEFAULT_SPEED * 1;
    }

    public boolean hit(Entity e) {
        return !(x + w < e.x ||
                (x > e.x + w) ||
                (y + h < e.y) ||
                (y > e.y + e.h));
    }

    @Override
    public void draw(Canvas c, Paint p) {

        float cw = c.getWidth();
        float ch = c.getHeight();
        System.out.println("??? :)");

        // temp
        p.setColor(Color.GREEN);
        c.drawRect(x * cw, y * ch, (x + w) * cw , (y + h) * ch, p);
    }
}
