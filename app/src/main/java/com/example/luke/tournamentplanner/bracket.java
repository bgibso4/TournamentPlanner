package com.example.luke.tournamentplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

enum Type{
    CLASSIC, QUICKPLAY, DOUBLEELIMINATION
}

public class bracket extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner options;
    Type type;
    String[] types = {"Classic","QuickPlay", "DoubleElimination"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket);

        Intent intent = getIntent();
        ArrayList<Participant> teams = intent.getExtras().getParcelableArrayList("Tournament_Teams");

        options = (Spinner) findViewById(R.id.options);
        options.setOnItemSelectedListener(this);

        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),types);
        options.setAdapter(customAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(), types[i], Toast.LENGTH_LONG).show();
        if(types[i]=="Classic"){
            type = Type.CLASSIC;
        }
        else if(types[i] == "QuickPlay"){
            type = Type.QUICKPLAY;
        }
        else{
            type = Type.QUICKPLAY;
        }
        //from here set up a tournament based upon the selected choice
        //also we need to make a view of the finalized teams
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
