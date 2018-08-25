package com.example.alice.frogger;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

public class CarsLane extends ArrayList<Car> {

    // how to use speed?
    private float speed;

    CarsLane (int num, float v, int floor) { // floor with gridsize?
        speed = v;

        float gap = 1.0f / num;

        for(int i = 0; i < num; i ++) {
            this.add(new Car(i * gap, floor * Game.GRID_SIZE));
        }
    }

    public void draw(Canvas c, Paint p) {
        for(Car car : this) {
            car.draw(c, p);
        }
    }

    public void step() {

        if(speed >= 0) {
            // moving right
            for(Car car : this) {
                car.x += speed;
                if(car.x > Game.MAX_X + Game.GRID_SIZE)
                    car.x = 0 - Game.GRID_SIZE;
            }

        } else {
            // moving left
            for(Car car : this) {
                car.x -= speed;
                if(car.x + car.w < 0)
                    car.x = Game.MAX_X + Game.GRID_SIZE;
            }
        }
    }


}
