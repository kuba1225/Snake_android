package com.example.kuba.snake.snakeActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.kuba.snake.R;
import com.example.kuba.snake.snakeLogic.PointsComparator;
import com.example.kuba.snake.snakeLogic.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HighScoresActivity extends AppCompatActivity {

    Button menu;
    Button clearList;
    TextView highScoresList;
    private List<User> userScores;
    private GraphicsTools tools;
    private int linesColor = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_high_scores);


        tools = new GraphicsTools();
        userScores = new ArrayList<User>();
        try {
            readFromFile("HighScores.txt");
        } catch (IOException ex) {
            System.out.println(ex + "Nieudana próba odczytu pliku.");
        }

        menu = (Button) findViewById(R.id.HighScoresMenuButton);
        clearList = (Button) findViewById(R.id.HighScoresClearListButton);
        highScoresList = (TextView) findViewById(R.id.HighScoresList);


        highScoresList.setMovementMethod(new ScrollingMovementMethod());


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        clearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PrintWriter file = null;
                try {
                    file = new PrintWriter("HighScores.txt");
                    file.print("");
                } catch (FileNotFoundException ex) {
                    System.out.println(ex + "Nieudana próba odczytu pliku.");
                } finally {
                    if (file != null) {
                        file.close();
                    }
                }
            }
        });
    }


    public void readFromFile(String fileName) throws IOException {
        int licznik = 1;
        Scanner file = null;
        String line;
        String name;
        int points;
        AssetManager assetManager = this.getResources().getAssets();
        InputStream input = assetManager.open(fileName);

        try {
            file = new Scanner(new BufferedReader(new InputStreamReader(input, "UTF-8")));

            while (file.hasNext()) {
                name = file.next();
                while (!file.hasNextInt()) {
                    name += (" " + file.next());
                }
                points = file.nextInt();
                userScores.add(new User(name, points));
                Log.v("tag",name);
                licznik++;
            }

            Collections.sort(userScores, new PointsComparator());

            for (int i = 0; i < licznik - 1; i++) {
                line = (i + 1) + ".  " + userScores.get(i).getUserName() + "     " + userScores.get(i).getPoints() + " PKT";
                highScoresList.append(line);
            }
        } finally {
            if (file != null) {
                file.close();
            }
        }
    }







    }

