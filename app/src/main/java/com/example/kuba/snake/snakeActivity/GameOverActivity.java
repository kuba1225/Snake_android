package com.example.kuba.snake.snakeActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.kuba.snake.R;
import com.example.kuba.snake.snakeLogic.SnakeLogic;
import static com.example.kuba.snake.snakeActivity.SnakeActivity.*;

public class GameOverActivity extends AppCompatActivity {

    Button repeat;
    Button menu;
    TextView points;
    private Integer point;

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public GameOverActivity(){
        point = new Integer(0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game_over);

        repeat = (Button) findViewById(R.id.GameOverRepeatButton);
        menu = (Button) findViewById(R.id.GameOverMenuButton);
        points = (TextView) findViewById(R.id.GameOverPoints);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void gameOver(Canvas canvas, Context context) {
        setPoint(logic.getPoints());


        points.setText(logic.getPoints() + " PKT");

        logic.getSnake().length = 0;
        logic.getSnake().getPosition().clear();
        logic.initSnake();
        logic.setGameOver(false);
        START_STOP = 0;
        Intent intent = new Intent(context, GameOverActivity.class);
        //intent.putExtra("",logic.getPoints());!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        startActivity(intent);
    }


}
