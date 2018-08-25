package com.example.alice.frogger;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;


/**
 * This class is for showing the game. Add this view to the game activity
 * */
public class FroggerView extends View implements View.OnTouchListener, Runnable{

    public static final int DELAY_TIME = 100;
    public static final int DEFAULT_COLOR = Color.DKGRAY;
    public static final long DEFAULT_PENWIDTH = (long) 3.0; // neccesary ?
    private static final float DEFAULT_FROG_STEP = 1.0f / 12.0f;

    Paint p;
    Game game;
    // ?
    Handler repaintHandler;

    public FroggerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setColor(DEFAULT_COLOR);
        p.setStrokeWidth(DEFAULT_PENWIDTH);
        this.setOnTouchListener(this);
        game = new Game();

        repaintHandler = new Handler();
        repaintHandler.postDelayed(this, DELAY_TIME);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        game.draw(canvas, p);
    }

    public boolean step() {
        game.step();
        if(game.hasWon() || game.gameOver()) {
            return false;
        }
        this.invalidate();
        return true;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float w = view.getWidth();
        float h = view.getHeight();
//        game.move(motionEvent.getX() / w, motionEvent.getY() / h);
        game.move(1.0f/ 2.0f, 1.0f/2.0f);
        return true;
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        switch(keyCode) {
//            case KeyEvent.KEYCODE_D:
//                game.move(0, -DEFAULT_FROG_STEP);
//                return true;
//            case KeyEvent.KEYCODE_DPAD_DOWN:
//                game.move(0, DEFAULT_FROG_STEP);
//                return true;
//            case KeyEvent.KEYCODE_DPAD_LEFT:
//                game.move(-DEFAULT_FROG_STEP, 0);
//                return true;
//            case KeyEvent.KEYCODE_DPAD_RIGHT:
//                game.move(+DEFAULT_FROG_STEP, 0);
//                return true;
//            default:
//                return super.onKeyUp(keyCode, event);
//        }
//
//
//    }

    @Override
    public void run() {
        if(step()) {
            repaintHandler.postDelayed(this, DELAY_TIME);
        }
    }

}
