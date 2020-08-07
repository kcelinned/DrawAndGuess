package com.example.drawguess;

import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrawingActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawingView drawView;
    private ImageButton currentPaint, drawBtn, eraseBtn, clearBtn, fillBtn;

    private Button scoreBtn;
    private int numberScore = 0;

    private static final long START_TIME_IN_MILLIS = 60000;
    private TextView textCountdown, textScore;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeft = START_TIME_IN_MILLIS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        textCountdown = findViewById(R.id.countdown_text);
        startTimer();

        textScore = (TextView) findViewById(R.id.score);
        textScore.setText(String.valueOf(numberScore));

        scoreBtn = findViewById(R.id.correct);
        scoreBtn.setOnClickListener(this);

        drawBtn = findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);

        drawView = findViewById(R.id.drawing);
        LinearLayout paintLayout = findViewById(R.id.paint_colors);

        currentPaint = (ImageButton) paintLayout.getChildAt(0);
        currentPaint.setImageDrawable(getResources().getDrawable((R.drawable.paint_presse)));

        eraseBtn = findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        clearBtn = findViewById(R.id.clear_btn);
        clearBtn.setOnClickListener(this);

        fillBtn = findViewById(R.id.fill_btn);
        fillBtn.setOnClickListener(this);


    }



    /** Sets up the timer**/
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountdownText();

            }

            @Override
            public void onFinish() {
                timerRunning = false;
                Intent intent = new Intent(DrawingActivity.this, EndActivity.class);
                intent.putExtra("score", numberScore);
                startActivity(intent);
            }
        }.start();
        timerRunning = true;
    }

    /** Updates the textView for the timer**/
    private void updateCountdownText(){
        int minutes = (int) timeLeft/1000/60;
        int seconds = (int) timeLeft/1000%60;

        String timeLeftText = String.format("%02d:%02d", minutes, seconds);
        textCountdown.setText(timeLeftText);
    }


    /** Sets the current paint colour to the paint icon that was pressed**/
    public void paintClicked(View view){
        drawView.setErase(false);
        if(view!=currentPaint){
            ImageButton imgView = (ImageButton)view;
            String color = view.getTag().toString();
            drawView.setColor(color);
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_presse));
            currentPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint));
            currentPaint=(ImageButton)view;
        }
    }


    /** Increases the score when the correct button is pressed **/
    public void increaseScore(){
        numberScore++;
        textScore.setText(String.valueOf(numberScore));

    }

    /** Performs different functions depending on button pressed **/
    @Override
    public void onClick(View view){
        if(view.getId() == R.id.draw_btn){
            drawView.setErase(false);
        }else if(view.getId() == R.id.erase_btn){
            drawView.setErase(true);
        }else if(view.getId() == R.id.clear_btn){
            drawView.clearCanvas();
        }else if(view.getId() == R.id.fill_btn) {
            drawView.fillCanvas();
        }else if(view.getId() == R.id.correct){
            increaseScore();
            drawView.clearCanvas();
        }
    }
}
