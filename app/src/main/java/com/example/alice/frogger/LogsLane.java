package com.example.alice.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class LogsLane extends ArrayList<Log> {

    public float speed;

    LogsLane(int num, float v, int floor) {
        speed = v;

        float gap = 1.0f / num;

        for(int i = 0; i < num; i ++) {
            this.add(new Log(gap * i, floor * Game.GRID_SIZE));
        }

    }

    public void draw(Canvas c, Paint p, Bitmap logp) {
        for(Log l : this) {
            l.draw(c, p, logp);
        }
    }

    public void step() {

        if(speed > 0) {
            // moving right
            for(Log l : this) {
                l.x += speed;
                if(l.x > Game.MAX_X + Game.GRID_SIZE)
                    l.x = 0 - l.w;
            }

        } else {
            // moving left
            for(Log l : this) {
                l.x += speed;
                if(l.x + l.w < 0)
                    l.x = Game.MAX_X + Game.GRID_SIZE;
            }
        }
    }
}
