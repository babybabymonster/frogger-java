package com.example.alice.frogger;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Frog extends Entity {

    public Frog(float x, float y) {
        super(x, y);
        w = Game.GRID_SIZE;
        h = Game.GRID_SIZE / 2;
        v = 0;
    }

    public boolean hit(Entity e) {
        return !(x + w - Game.TOLERANCE <= e.x ||
                (x + Game.TOLERANCE >= e.x + w) ||
                (y + h - Game.TOLERANCE <= e.y) ||
                (y + Game.TOLERANCE >= e.y + e.h));
    }

    public void step() {
        if(v != 0) {
//            System.out.println("prev frog x = " + x);
            this.x += v;
//            System.out.println("now frog x = " + x);
        }
    }

    @Override
    public void draw(Canvas c, Paint p, Bitmap frogp) {

        System.out.println("frog.x = " + x);
//        System.out.println(y < Game.GRID_SIZE * 4);

        float cw = c.getWidth();
        float ch = c.getHeight();

        frogp = Bitmap.createScaledBitmap(frogp, (int) (w * cw), (int) (h * ch), true);
        c.drawBitmap(frogp, x * cw, y * ch, p);
        // temp
//        p.setColor(Color.GREEN);
//        p.setAlpha(120);
//        c.drawRect(x * cw, y * ch, (x + w) * cw , (y + h) * ch, p);
    }
}
