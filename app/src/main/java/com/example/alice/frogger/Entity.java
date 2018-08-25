package com.example.alice.frogger;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Entity {

    // about whether adding v
    public float x, y, w, h, v;
    public static final float DEFAULT_SPEED = 1.0f;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Canvas c, Paint p);
}
