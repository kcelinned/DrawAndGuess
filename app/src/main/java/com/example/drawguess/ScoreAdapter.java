package com.example.drawguess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ScoreAdapter extends ArrayAdapter<Scores> {


    public ScoreAdapter(Context context,  ArrayList<Scores> arrayList) {
        super(context, 0, arrayList);
    }


    /** Converts the Score object to a View**/
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
     Scores tmpScore = getItem(position);
     if(convertView == null){
         convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_template, parent, false);
     }

        TextView name = (TextView) convertView.findViewById(R.id.name_col);
        TextView score =(TextView) convertView.findViewById(R.id.score_col);

        name.setText(tmpScore.getName());
        score.setText(String.valueOf(tmpScore.getScore()));

        return convertView;

    }
}
