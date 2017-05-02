package com.example.luke.tournamentplanner;

import android.icu.text.MessagePattern;

import java.util.ArrayList;

/**
 * Created by Ben on 5/2/2017.
 */

public class Game {
    private ArrayList<Participant> opponents= new ArrayList<>();
    private Participant team1;
    private Participant team2;
    private int differential;

    public Game(Participant _team1, Participant _team2){
        team1= _team1;
        team2= _team2;
        differential=0;
    }

    public void setScore(int team1Score, int team2Score){
//        team1Points= team1Score;
//        team2Points= team2Score;
        team1.setScore(team1Score, team2Score);
        team2.setScore(team2Score, team1Score);
        differential= team1Score- team2Score;
    }

    public int getDifferential(){
        return differential;
    }

    public Participant getWinner(){
        if (getDifferential()<0){
            return team2;
        }
        return team1;
    }

    public Participant getLoser(){
        if (getDifferential()<0){
            return team1;
        }
        return team2;
    }

    public String toString(){
        return "[" + " ]";
    }
}
