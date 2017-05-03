package com.example.luke.tournamentplanner;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Ben on 5/2/2017.
 */

public class RoundMaker extends AppCompatActivity {
    private ArrayList<Participant> allTeams = new ArrayList<>();
    private ArrayList<Game> matches= new ArrayList<>();


    public RoundMaker(ArrayList<Participant> currentTeams){
        allTeams= currentTeams;
    }

    public void setMatches(){
        int index=0;
        while ((allTeams.size()-index)>=2){
            Game match= new Game(allTeams.get(index), allTeams.get(index+1));
            index= index+2;
            matches.add(match);
        }
        if ((allTeams.size()-index)==1){
            Game match= new Game(allTeams.get(index), null);
            index++;
            matches.add(match);
        }
    }

    public ArrayList<Game> getMatches(){
        return matches;
    }
}
