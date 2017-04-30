package com.example.luke.tournamentplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.luke.tournamentplanner.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button enter = (Button)findViewById(R.id.enter);

        //sending the amount of players to the NamingTeams Activity
        //for some reason i couldnt use a sendMessage Function but this will do
        enter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, NamingTeams.class);
                EditText numPlayers = (EditText)findViewById(R.id.numPlayers);
                String message = numPlayers.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });
    }


    //didnt need this function as a spinner was no longer required
//    public void addItemstoSpinner(){
//        spinner = (Spinner) findViewById(R.id.spinner);
//        List<Integer> numbers = new ArrayList<>();
//        for(int i = 0; i < 50; i++){
//            numbers.add(i+1);
//        }
//        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this,
//                android.R.layout.simple_spinner_dropdown_item, numbers);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(dataAdapter);
//    }
}
