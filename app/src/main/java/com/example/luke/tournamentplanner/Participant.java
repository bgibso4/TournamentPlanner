package com.example.luke.tournamentplanner;

import android.annotation.TargetApi;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by luke on 2017-04-19.
 */

public class Participant implements Parcelable{
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

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return "[" + name + ": " + Integer.toString(pointsFor) + ": "
                + Integer.toString(pointsAgainst) + " ]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(pointsAgainst);
        out.writeInt(pointsFor);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Participant> CREATOR = new Parcelable.Creator<Participant>() {
        public Participant createFromParcel(Parcel in) {
            return new Participant(in);
        }

        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Participant(Parcel in) {
        name = in.readString();
        pointsAgainst = in.readInt();
        pointsFor = in.readInt();
    }


//    @TargetApi(24)
//    public int hashCode(){
//        return 31*(31*name.hashCode() + Integer.hashCode(pointsAgainst)) + Integer.hashCode(pointsFor);
//    }

}
