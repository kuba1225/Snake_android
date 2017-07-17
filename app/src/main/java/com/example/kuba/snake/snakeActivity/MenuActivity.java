package com.example.kuba.snake.snakeActivity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.kuba.snake.R;
import com.example.kuba.snake.snakeLogic.SnakeLogic;

public class MenuActivity extends AppCompatActivity {
    Button startButton;
    Button highScoresButton;
    Button settingsButton;
    Button exitButton;


    static int snakeColorMP = Color.BLACK;
    static int levelMP = 1;
    static int timerDelay = 350;
    private GraphicsTools tools;
    private int linesColor = Color.WHITE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        startButton = (Button) findViewById(R.id.MenuStartButton);
        highScoresButton = (Button) findViewById(R.id.MenuHighScoresButton);
        settingsButton = (Button) findViewById(R.id.MenuSettingsButton);
        exitButton = (Button) findViewById(R.id.MenuExitButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SnakeLogic logic = new SnakeLogic(getApplicationContext());!!!!!!!!!

                switch (levelMP) {
                    case 1:
                        timerDelay = 350;
                        break;
                    case 2:
                        timerDelay = 225;
                        break;
                    case 3:
                        timerDelay = 100;
                        break;
                }

                //timer.setDelay(timerDelay);!!!!!!!!!!!!!!!!
                //logic.setLevel(levelMP);!!!!!!!!!!!!!!!!
                //paint.setSnakeColor(snakeColorMP);!!!!!!!!!!!!!!!!

                Intent intent = new Intent(getApplicationContext(),SnakeActivity.class);
                startActivity(intent);
            }
        });

        highScoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),HighScoresActivity.class);
                startActivity(intent);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

    }
}
