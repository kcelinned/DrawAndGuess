package com.example.drawguess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBhandler extends SQLiteOpenHelper {

    private static final String TAG = "Database";

    private static final String TABLE_NAME ="Scores";
    private static final String COLUMN1 = "ID";
    private static final String COLUMN2 = "Name";
    private static final String COLUMN3 = "Score";

    public DBhandler(Context context){
        super(context, TABLE_NAME, null,1);
    }


    /** Creates Table **/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                COLUMN2 + " VARCHAR(255) NOT NULL, " +
                COLUMN3 + " INT NOT NULL)";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    /** Adds data into the database**/
    public boolean addData(String name, int score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN2, name);
        contentValues.put(COLUMN3, score);

        Log.d(TAG, "Adding Score");

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    /** Returns all the data from the database**/
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN3 + " DESC";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
