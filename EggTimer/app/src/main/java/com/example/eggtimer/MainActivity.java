package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar timerSeekBar;
    TextView timerView;
    Button startButton;
    CountDownTimer cdt;
    Boolean counterIsActive = false;

    public void stratCountdown(View view) {

        startButton = findViewById(R.id.startButton);

        if (counterIsActive) {
            timerView.setText("00:00");
            timerSeekBar.setProgress(0);
            timerSeekBar.setEnabled(true);
            cdt.cancel();
            startButton.setText("START");
            counterIsActive = false;
        } else {
            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            startButton.setText("STOP");

            cdt = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) (millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();
                }
            }.start();
        }

    }

    public void updateTimer(int secs) {
        int minutes = secs / 60;
        int seconds = secs - (minutes * 60);
        String minutestring = Integer.toString(minutes);
        String secondstring = Integer.toString(seconds);
        if (minutes < 10) {
            minutestring = String.format("0%d", minutes);
        }
        if (seconds < 10) {
            secondstring = String.format("0%d", seconds);
        }
        timerView.setText(minutestring + ":" + secondstring);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.timerSeekBar);
        timerView = findViewById(R.id.countTextView);

        timerSeekBar.setMax(1200);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
