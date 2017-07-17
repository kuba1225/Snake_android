/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.kuba.snake.snakeLogic;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

import com.example.kuba.snake.snakeActivity.GameOverActivity;
import com.example.kuba.snake.snakeActivity.GraphicsTools;

import java.util.Random;

import static java.lang.Math.abs;
import static com.example.kuba.snake.snakeActivity.GameOverActivity.*;

/**
 * @author Kuba
 */
public class SnakeLogic extends SurfaceView implements SurfaceHolder.Callback {

    private Snake snake;
    private int points = 0;
    private static int move = 2;
    private boolean ate = true;
    private boolean gameOver = false;
    private static int level = 1;
    Paint paint = new Paint();


    private Position fruitPosition = null;
    private Random rand = new Random();


    public SnakeLogic(Context context) {
        super(context);
    }

    public void initSnake() {
        snake = new Snake();
        Position head = new Position(3, 6);
        snake.getPosition().add(head);
        move = 2;
        snake.length++;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    public Snake getSnake() {
        return snake;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isAte() {
        return ate;
    }

    public void setAte(boolean ate) {
        this.ate = ate;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Position getFruitPosition() {
        return fruitPosition;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public void generateFruit() {
        int r = rand.nextInt(20);
        int c = rand.nextInt(12);

        Position tmp = new Position(r, c);

        for (int i = 0; i < snake.getLength(); i++) {
            if (tmp.equals(snake.getPosition().get(i))) {
                i = 0;
                r = rand.nextInt(20);
                c = rand.nextInt(12);
                tmp.setR(r);
                tmp.setC(c);
            }
        }

        fruitPosition = tmp;
    }

    public void updatePosition() {
        int hr = snake.getPosition().get(0).getR();
        int hc = snake.getPosition().get(0).getC();

        int tr = snake.getPosition().get(snake.length - 1).getR();
        int tc = snake.getPosition().get(snake.length - 1).getC();

        switch (move) {
            case 0:
                hr--;
                break;
            case 1:
                hc++;
                break;
            case 2:
                hr++;
                break;
            case 3:
                hc--;
                break;
        }

        for (int j = 0; j < snake.length; j++) {
            if ((snake.getPosition().get(j).getR() == hr) && (snake.getPosition().get(j).getC() == hc)) {
                gameOver = true;
            }
        }

        if (hr >= 0 && hr < 20 && hc >= 0 && hc < 12 && !gameOver) {

            for (int i = snake.length - 1; i > 0; i--) {
                snake.getPosition().get(i).setR(snake.getPosition().get(i - 1).getR());
                snake.getPosition().get(i).setC(snake.getPosition().get(i - 1).getC());
            }

            snake.getPosition().get(0).setR(hr);
            snake.getPosition().get(0).setC(hc);

            Position tmp2 = new Position(hr, hc);

            if (tmp2.equals(fruitPosition)) {
                Position tmp = new Position(tr, tc);
                snake.getPosition().add(tmp);
                points += 10 * level;
                snake.length++;
                ate = true;

            }
        } else {
            gameOver = true;

        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x1 = 0, y1 = 0;
        int x2 = 0, y2 = 0;
        int dx, dy;
        Log.v("snaketest", "on touch event");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = (int) event.getRawX();
                y1 = (int) event.getRawY();
                Log.v("snaketest", "x1 = " + x1 + " y1 = " + y1);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                x2 = (int) event.getRawX();
                y2 = (int) event.getRawX();
                Log.v("snaketest", "x1 = " + x2 + " y1 = " + y2);
                break;
        }

        dx = x2 - x1;
        dy = y2 - y1;

        if (dx > 0 && abs(dx) > abs(dy)) {
            move = 1; //RIGHT
            Log.v("snaketest", "move right");
        } else if (dx < 0 && abs(dx) > abs(dy)) {
            move = 3; //LEFT
            Log.v("snaketest", "move left");
        } else if (dy > 0 && abs(dy) > abs(dx)) {
            move = 0; //UP
            Log.v("snaketest", "move up");
        } else if (dy < 0 && abs(dy) > abs(dx)) {
            move = 2; //DOWN
            Log.v("snaketest", "move down");
        }


        return true;
    }
    static int x1 = 0, y1 = 0;
    static int x2 = 0, y2 = 0;
    static int x3 = 0, y3 = 0;
    public View.OnTouchListener handleTouch = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            int dx, dy;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = (int) event.getRawX();
                    y1 = (int) event.getRawY();
                    Log.v("snakeposition", "x1 = " + x1 + " y1 = " + y1);
                    break;
                case MotionEvent.ACTION_MOVE:
                    x3 = (int) event.getRawX();
                    y3 = (int) event.getRawY();
                    Log.v("snakeposition", "x3 = " + x3 + " y3 = " + y3);

                    dx = x3 - x1;
                    dy = y3 - y1;
                    Log.v("snakeposition", "dx = " + dx + " dy = " + dy);
                    if (abs(dx) > abs(dy) && x3 > x1) {
                        move = 1; //RIGHT
                        Log.v("snakeposition", "move right");
                    } else if (abs(dx) > abs(dy) && x3 < x1) {
                        move = 3; //LEFT
                        Log.v("snakeposition", "move left");
                    } else if (abs(dy) > abs(dx) && y3 < y1) {
                        move = 0; //UP
                        Log.v("snakeposition", "move up");
                    } else if (abs(dy) > abs(dx) && y3 > y1) {
                        move = 2; //DOWN
                        Log.v("snakeposition", "move down");
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    x2 = (int) event.getRawX();
                    y2 = (int) event.getRawY();
                    Log.v("snakeposition", "x2 = " + x2 + " y2 = " + y2);

                    /*dx = x2 - x1;
                    dy = y2 - y1;
                    Log.v("snakeposition", "dx = " + dx + " dy = " + dy);
                    if (abs(dx) > abs(dy) && x2 > x1) {
                        move = 1; //RIGHT
                        Log.v("snakeposition", "move right");
                    } else if (abs(dx) > abs(dy) && x2 < x1) {
                        move = 3; //LEFT
                        Log.v("snakeposition", "move left");
                    } else if (abs(dy) > abs(dx) && y2 < y1) {
                        move = 0; //UP
                        Log.v("snakeposition", "move up");
                    } else if (abs(dy) > abs(dx) && y2 > y1) {
                        move = 2; //DOWN
                        Log.v("snakeposition", "move down");
                    }*/
                    break;
            }


            return true;
        }
    };


}
