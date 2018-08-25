package com.example.alice.frogger;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Game {

    public static final float GRID_SIZE = 0.1f;
    public static final float MAX_X = 1.0f;
    public static final float DEFAULT_SPEED = 1.0f / 12.0f;

    // entities in the game
    private Frog frog;
    private CarsLane carsLane1;
    private CarsLane carsLane2;
    private CarsLane carsLane3;
    private LogsLane logsLane1;
    private LogsLane logsLane2;
    private LogsLane logsLane3;

    private boolean frogDie;
    public boolean succeed;

    //frog position
    public static final float frogX = 0.5f;
    public static final float frogY = 0.9f;


    public Game() {
        frog = new Frog(frogX, frogY);
        carsLane1 = new CarsLane(2, -DEFAULT_SPEED * 0.5f, 5);
        carsLane2 = new CarsLane(3, DEFAULT_SPEED * 0.7f, 6);
        carsLane3 = new CarsLane(4, -DEFAULT_SPEED * 0.3f, 7);
        logsLane1 = new LogsLane(3, DEFAULT_SPEED * 0.4f, 1);
        logsLane2 = new LogsLane(2, -DEFAULT_SPEED * 0.8f, 2);
        logsLane3 = new LogsLane(4, DEFAULT_SPEED * 0.5f, 3);

        frogDie = false;
        succeed = false;
    }

    // draw all the entities
    public void draw(Canvas c, Paint p) {
        frog.draw(c, p);
        System.out.println(":) draw frog again lah" + frog.x);
        carsLane1.draw(c, p);
        carsLane2.draw(c, p);
        carsLane3.draw(c, p);
        logsLane1.draw(c, p);
        logsLane2.draw(c, p);
        logsLane3.draw(c, p);
    }

    public void step() {
        carsLane1.step();
        carsLane2.step();
        carsLane3.step();

        logsLane1.step();
        logsLane2.step();
        logsLane3.step();

        if(frog.y >= carsLane3.get(0).y) {
            //check if frog is hitted by any cars
            for (Car c : carsLane1) {
                if (frog.hit(c)) frogDie = true;
            }
            for (Car c : carsLane1) {
                if (frog.hit(c)) frogDie = true;
            }
            for (Car c : carsLane1) {
                if (frog.hit(c)) frogDie = true;
            }
        } else {

            //check if frog is jump on any logs
            for (Log l : logsLane1) {
                if (!frog.hit(l)) frogDie = true;
            }
            for (Log l : logsLane1) {
                if (!frog.hit(l)) frogDie = true;
            }
            for (Log l : logsLane1) {
                if (!frog.hit(l)) frogDie = true;
            }
        }
    }

    public boolean hasWon() {
        return succeed;
    }

    // enhance -> has multi lives
    public boolean gameOver() {
        return frogDie;
    }


    // reset the position of frog according to key press
    public void move(float stepX, float stepY) {
        frog.x = stepX;
        frog.y = stepY;
//        frog.x += stepX;
//        frog.y += stepY;
    }
}
