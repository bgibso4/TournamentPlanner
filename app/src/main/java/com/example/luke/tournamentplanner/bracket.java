package com.example.luke.tournamentplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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


        //creating a button that will allow for the creation of the specified tournament type
        //****Needs to be filled in when the other type have been created*****
        Button confirmBtn = (Button)findViewById(R.id.TTconfirmButton);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goes to a classic tournament- round robin + single-KO
                if(type==type.CLASSIC){

                }
                //goes to a single-KO stype tourney
                else if(type==type.QUICKPLAY){

                }
                //goes to a double-KO stype tourney
                else if (type==type.DOUBLEELIMINATION){

                }

                //receiving the updates
                //notifyDataSetChanged();
            }
        });

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
