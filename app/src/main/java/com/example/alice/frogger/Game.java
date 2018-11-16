package com.example.alice.frogger;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Game {

    public static final float GRID_SIZE = 0.1f;
    public static final float MAX_X = 1.0f;
    public static final float DEFAULT_SPEED = 1.0f / 20.0f;
    public static final float TOLERANCE = 0.001f;

    // entities in the game
    public Frog frog;
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
    public static final float frogY = 1.0f - GRID_SIZE;


    public Game() {
        frog = new Frog(frogX, frogY);
        carsLane1 = new CarsLane(2, -DEFAULT_SPEED * 0.3f, 7);
        carsLane2 = new CarsLane(3, DEFAULT_SPEED * 0.2f, 6);
        carsLane3 = new CarsLane(4, -DEFAULT_SPEED * 0.1f, 5);
        logsLane1 = new LogsLane(3, DEFAULT_SPEED * 0.2f, 1);
        logsLane2 = new LogsLane(2, -DEFAULT_SPEED * 0.1f, 2);
        logsLane3 = new LogsLane(4, DEFAULT_SPEED * 0.1f, 3);

        frogDie = false;
        succeed = false;
    }

    // draw all the entities
    public void draw(Canvas c, Paint p) {
        p.setColor(Color.BLUE);
        c.drawRect(0, GRID_SIZE * c.getHeight() * 4, c.getWidth(), GRID_SIZE * c.getHeight() * 5, p);
        c.drawRect(0, 0, c.getWidth(), GRID_SIZE * c.getHeight(), p);
        c.drawRect(0, GRID_SIZE * c.getHeight() * 8, c.getWidth(), GRID_SIZE * c.getHeight() * 10, p);
//        p.setColor(Color.RED);
//        p.setStrokeWidth(10f);
//        c.drawLine(0,GRID_SIZE * c.getHeight() * 4,c.getWidth(),GRID_SIZE * c.getHeight() * 4,p);
        carsLane1.draw(c, p, FroggerView.mycar1);
        carsLane2.draw(c, p, FroggerView.mycar2);
        carsLane3.draw(c, p, FroggerView.mycar3);
        logsLane1.draw(c, p, FroggerView.mylog1);
        logsLane2.draw(c, p, FroggerView.mylog2);
        logsLane3.draw(c, p, FroggerView.mylog3);
        frog.draw(c, p, FroggerView.myfrog);
    }

    public void step() {

        carsLane1.step();
        carsLane2.step();
        carsLane3.step();

        logsLane1.step();
        logsLane2.step();
        logsLane3.step();

        if(frog.y < GRID_SIZE) {
            frog.step();
            succeed = true;
        }

        if(frog.y >= GRID_SIZE * 4 && frog.y < GRID_SIZE * 8) {
            //check if frog is hitted by any cars
            for (Car c : carsLane1) {
                if (frog.hit(c)) frogDie = true;
            }
            for (Car c : carsLane2) {
                if (frog.hit(c)) frogDie = true;
            }
            for (Car c : carsLane3) {
                if (frog.hit(c)) frogDie = true;
            }
        } else if(frog.y + TOLERANCE < GRID_SIZE * 4 && frog.y + TOLERANCE > GRID_SIZE) {
            frogDie = true;
            //check if frog is jump on any logs
            for (Log l : logsLane1) {
                if (frog.hit(l)) {
                    frog.v = logsLane1.speed;
                    frogDie = false;
                }
            }
            for (Log l : logsLane2) {
                if (frog.hit(l)) {
                    frog.v = logsLane2.speed;
                    frogDie = false;
                }
            }
            for (Log l : logsLane3) {
                if (frog.hit(l)) {
                    frog.v = logsLane3.speed;
                    frogDie = false;
                }
            }
            frog.step();
        } else {
            frog.v = 0;
        }



    }

    public boolean hasWon() {
        return succeed;
    }

    // enhance -> has multi lives
    public boolean frogDie() {
        return frogDie;
    }


    // reset the position of frog according to key press
    public void move(float stepX, float stepY) {
        if(Math.abs(frog.y - stepY) > Math.abs(frog.x - stepX)) { //move up or down
            if((frog.y - stepY) > 0) {
                //up
                frog.y -= GRID_SIZE;
            } else {
                //down
                frog.y += GRID_SIZE;
            }
        } else {
            // move left or right
            if((frog.x - stepX) > 0) {
                //left
                frog.x -= GRID_SIZE;
            } else {
                //right
                frog.x += GRID_SIZE;
            }

        }
//        frog.x += stepX;
//        frog.y += stepY;
    }
}
