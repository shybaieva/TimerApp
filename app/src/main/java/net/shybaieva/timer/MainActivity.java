package net.shybaieva.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText setTime;
    TextView showMilliSeconds, showSeconds, showMinutes, feedTheCat;
    ImageView hungryCat;
    long time, timerInterval = 1;
    MediaPlayer catSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setTime.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    time = (Integer.parseInt(setTime.getText().toString())) * 1000;
                    return true;
                }
                return false;
            }
        });
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CountDownTimer timer = new CountDownTimer(time, timerInterval) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        setTime.setText("");
                        showMilliSeconds.setText( String.valueOf(millisUntilFinished %1000));
                        showSeconds.setText(String.valueOf(millisUntilFinished/1000));
                        showMinutes.setText(String.valueOf(millisUntilFinished/60000));
                    }

                    @Override
                    public void onFinish() {
                        showMilliSeconds.setText("00");
                        soundPlay(catSound);
                        feedTheCat.setVisibility(View.VISIBLE);
                        hungryCat.setVisibility(View.VISIBLE);
                    }
                };
                timer.start();
            }
        });

    }

    private void init() {
        startButton = findViewById(R.id.startButton);
        setTime = findViewById(R.id.setTime);
        showMilliSeconds = findViewById(R.id.milliSeconds);
        showSeconds = findViewById(R.id.seconds);
        showMinutes = findViewById(R.id.minutes);
        hungryCat = findViewById(R.id.imageView);
        feedTheCat = findViewById(R.id.textView5);
        catSound = MediaPlayer.create(this, R.raw.meow);
    }

    public void soundPlay(MediaPlayer sound){
        sound.start();
    }
}
