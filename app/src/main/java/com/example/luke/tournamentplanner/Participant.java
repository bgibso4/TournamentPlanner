package com.example.luke.tournamentplanner;

import android.annotation.TargetApi;

/**
 * Created by luke on 2017-04-19.
 */

public class Participant {
    private int pointsFor, pointsAgainst;
    private String name;

    public Participant(String name){
        this.name = name;
        pointsFor = 0;
        pointsAgainst = 0;
    }

    public  void setScore(int pointsFor, int pointsAgainst){
        this.pointsFor += pointsFor;
        this.pointsAgainst += pointsAgainst;
    }

    public String toSttring(){
        return "[" + name + ": " + Integer.toString(pointsFor) + ": "
                + Integer.toString(pointsAgainst) + " ]";
    }

    public void setName(String name){
        this.name = name;
    }

    @TargetApi(24)
    public int hashCode(){
        return 31*(31*name.hashCode() + Integer.hashCode(pointsAgainst)) + Integer.hashCode(pointsFor);
    }

}
