package com.example.mathtrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start_button, red_button, blue_button, green_button, yellow_button, reset_button;
    TextView timer_textView, score_textView, add_textView, result_textView;
    ImageView app_logo;
    ConstraintLayout game_layout;
    android.support.v7.widget.GridLayout answers_grid;
    int score = 0 , questions = 0, location;
    List<Integer> answers = new ArrayList<>();
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_textView = findViewById(R.id.add_textView);
        timer_textView = findViewById(R.id.timer_textView);
        score_textView = findViewById(R.id.score_textView);
        result_textView = findViewById(R.id.result_textView);
        app_logo = findViewById(R.id.logo_imageView);
        start_button = findViewById(R.id.start_button);
        red_button = findViewById(R.id.red_button);
        blue_button = findViewById(R.id.blue_button);
        green_button = findViewById(R.id.green_button);
        yellow_button = findViewById(R.id.yellow_button);
        reset_button = findViewById(R.id.reset_button);
        answers_grid = findViewById(R.id.answers_gridLayout);
        game_layout = findViewById(R.id.game_layout);
        game_layout.setVisibility(View.INVISIBLE);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_button.setVisibility(v.INVISIBLE);
                game_layout.setVisibility(v.VISIBLE);
                app_logo.setVisibility(v.INVISIBLE);
                resetGame();
            }
        });

        red_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAnswer(v);
            }
        });

        blue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAnswer(v);
            }
        });

        green_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAnswer(v);
            }
        });

        yellow_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseAnswer(v);
            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        newAnswers();

    }

    private void resetGame() {
        score = 0; questions = 0;
        result_textView.setText("");
        reset_button.setVisibility(View.INVISIBLE);
        timer_textView.setText("60s");
        score_textView.setText(Integer.toString(score) + " / " + Integer.toString(questions));
        answers_grid.setVisibility(View.VISIBLE);
        startTimer();
        newAnswers();
    }

    private void newAnswers() {
        Random rand = new Random();

        int a = rand.nextInt(100);
        int b = rand.nextInt(100);
        location = rand.nextInt(4);
        int wrongAnswer;

        add_textView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == location) {
                answers.add(a + b);
            } else {
                wrongAnswer = rand.nextInt(200);
                while (wrongAnswer == (a + b)) {
                    wrongAnswer = rand.nextInt(200);
                }
                answers.add(wrongAnswer);
            }
        }

        red_button.setText(answers.get(0).toString());
        blue_button.setText(answers.get(1).toString());
        green_button.setText(answers.get(2).toString());
        yellow_button.setText(answers.get(3).toString());
    }

    private void chooseAnswer(View v) {
        if (Integer.toString(location).equals(v.getTag().toString())) {
            result_textView.setText("CORRECT!");
            score++;
        } else {
            result_textView.setText("WRONG");
        }
        questions++;
        score_textView.setText(Integer.toString(score) + " / " + Integer.toString(questions));
        newAnswers();
    }

    private  void startTimer() {
        cdt = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                updateTimer((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                result_textView.setText("Done");
                reset_button.setVisibility(View.VISIBLE);
                answers_grid.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    private void updateTimer(int seconds) {
        String secs = Integer.toString(seconds);
        if (seconds < 10) secs = String.format("0%d", seconds);
        timer_textView.setText(secs);
    }
}
