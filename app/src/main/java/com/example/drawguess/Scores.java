package com.example.drawguess;

public class Scores {

    /** To create a score object that can be saved in an ArrayList**/

    private String name;
    private int score;

    public Scores(String name, int score){
        this.name = name;
        this.score = score;

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
