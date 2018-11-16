package com.example.alice.frogger;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


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
    ArrayList<GameOver> observer;
    // ?
    Handler repaintHandler;

    //arrow button
    Button UP;
    Button DOWN;
    Button LEFT;
    Button RIGHT;

    //bitmap???
    public static Bitmap myfrog;
    public static Bitmap mycar1;
    public static Bitmap mycar2;
    public static Bitmap mycar3;
    public static Bitmap mylog1;
    public static Bitmap mylog2;
    public static Bitmap mylog3;

    public FroggerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        p = new Paint();
        p.setColor(DEFAULT_COLOR);
        p.setStrokeWidth(DEFAULT_PENWIDTH);
        //set up bitmap
        myfrog = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
        mycar1 = BitmapFactory.decodeResource(getResources(), R.drawable.car1_left);
        mycar2 = BitmapFactory.decodeResource(getResources(), R.drawable.car2_right);
        mycar3 = BitmapFactory.decodeResource(getResources(), R.drawable.car3_left);
        mylog1 = BitmapFactory.decodeResource(getResources(), R.drawable.log1);
        mylog2 = BitmapFactory.decodeResource(getResources(), R.drawable.log2);
        mylog3 = BitmapFactory.decodeResource(getResources(), R.drawable.log3);

        this.setOnTouchListener(this);
        observer = new ArrayList<>();
        game = new Game();

        //set up buttons
        UP = findViewById(R.id.UP);
        DOWN = findViewById(R.id.DOWN);
//        LEFT = findViewById(R.id.UP);
//        RIGHT = findViewById(R.id.UP);

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
        if(game.frogDie()) {
            showGameOver();
            return false;
        } else if(game.hasWon()) {
//            System.out.println("won!!!!!!!!!!!!");
            repaintHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showGameOver();
                }
            }, 2000);


        }
        this.invalidate();
        return true;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            float w = view.getWidth();
            float h = view.getHeight();
            game.move(motionEvent.getX() / w, motionEvent.getY() / h);
        }

//        switch(motionEvent.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                startTouch();
//            case MotionEvent.ACTION_MOVE:
//                moveTouch();
//            case MotionEvent.ACTION_UP:
//                endTouch();
//
//        }
        return true;
    }


    private void showGameOver() {
        for(GameOver o : observer) {
            o.gameOver();
        }
    }

    public void registerGameOver(GameOver gameOver) {
        observer.add(gameOver);
    }

    @Override
    public void run() {
        if(step()) {
            repaintHandler.postDelayed(this, DELAY_TIME);
        }
    }

}
