package com.example.alice.frogger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
* Playing GameActivity
* */

public class GameActivity extends AppCompatActivity implements GameOver {

    FroggerView froggerView;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        froggerView = findViewById(R.id.froggerview);
        game = froggerView.game;
        froggerView.registerGameOver(this);
    }


    @Override
    public void gameOver() {
        setResult(RESULT_OK);
        finish();
    }

    public void moveUp(View view) {
        game.frog.y -= game.GRID_SIZE;
    }

    public void moveDown(View view) {
        game.frog.y += game.GRID_SIZE;
    }

    public void moveRight(View view) {
        game.frog.x += game.GRID_SIZE;
    }

    public void moveLeft(View view) {
        game.frog.x -= game.GRID_SIZE;
    }
}
