package com.example.luke.tournamentplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class bracket extends AppCompatActivity {

    Spinner options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bracket);

        Intent intent = getIntent();
        ArrayList<Participant> teams = intent.getExtras().getParcelableArrayList("Tournament_Teams");

        addItemstoSpinner();
    }

    public void addItemstoSpinner(){
        options = (Spinner) findViewById(R.id.options);
        String[] types = new String[]{"Classic","QuickPlay", "DoubleElimination"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, types);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(dataAdapter);
        options.setPrompt("Tournament Types");
    }
}
