package com.example.drawguess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Starts DrawingActivity when the play_btn button is pressed **/
    public void openDrawingActivity(View view) {
        Intent intent = new Intent(this, DrawingActivity.class);
        startActivity(intent);
    }

    /** Starts ScoresActivity when the main_ShowScores button is pressed **/
    public void showResults(View view){
        Intent scoresIntent = new Intent(this, ScoresActivity.class);
        startActivity(scoresIntent);
    }
}
