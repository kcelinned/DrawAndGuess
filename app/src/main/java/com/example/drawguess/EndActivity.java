package com.example.drawguess;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EndActivity extends AppCompatActivity {

    TextView totalScore;
    int scoreValue;

    DBhandler databaseHelper;

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Intent intent = getIntent();
        scoreValue = intent.getIntExtra("score", 0);

        totalScore = findViewById(R.id.score_text);
        totalScore.setText(String.valueOf(scoreValue));

        editText = findViewById(R.id.enterText);

        databaseHelper = new DBhandler(this);

    }

    /** Starts DrawingActivity when the playAgain button is pressed **/
    public void playAgain(View view) {
        Intent playIntent = new Intent(this,DrawingActivity.class);
        startActivity(playIntent);
    }

    /** Starts MainActivyt when the home button is pressed **/
    public void goHome(View view) {
        Intent homeIntent = new Intent(this,MainActivity.class);
        startActivity(homeIntent);
    }


    /** Starts ScoresActivity when the showScores_btn button is pressed **/
    public void showScores(View view){
        Intent scoresIntent = new Intent(this, ScoresActivity.class);
        startActivity(scoresIntent);
    }



    /** Saves score to database when the save_btn button is pressed **/
    public void saveData(View view) {
        String name = editText.getText().toString();
        if(editText.length() != 0){
            addData(name,scoreValue);
        }else{
            toastMessage("Enter a Name");
        }
    }

    public void addData(String name, int score){
        boolean insertData = databaseHelper.addData(name, score);
        if(insertData){
            toastMessage("Score Added");
            editText.setText("");
        }else{
            toastMessage("Score was not Added. Try Again.");
        }
    }

    /** Creates and shows a Toast object**/
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
