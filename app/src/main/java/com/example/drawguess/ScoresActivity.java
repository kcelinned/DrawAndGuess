package com.example.drawguess;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ScoresActivity extends AppCompatActivity {

    DBhandler databaseHelper;

    private ListView scoreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        scoreList = findViewById(R.id.list_score);
        databaseHelper = new DBhandler(this);

        displayScores();
    }


    /** Retrieves and displays the data from the database into a ListView **/
    private void displayScores() {
        ArrayList<Scores> scoreResults = new ArrayList();
        Cursor results = databaseHelper.getAllData();
        while(results.moveToNext()){
            String name = results.getString(1);
            int score = results.getInt(2);

            Scores tmpScore = new Scores(name,score);
            scoreResults.add(tmpScore);
        }

        ScoreAdapter adapter = new ScoreAdapter(this, scoreResults);
        scoreList.setAdapter(adapter);

    }
}
