package com.example.luke.tournamentplanner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NamingTeams extends AppCompatActivity {

    private List<Participant> teams = new ArrayList<>();
    private List<String> teamNames = new ArrayList<>();
    private ListView list;
    private Adapter MyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
        final int capacity = Integer.parseInt(message);

        //List view which will display the players participating in the tournament
        list = (ListView) findViewById(R.id.list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teamNames);
        list.setAdapter(adapter);

        final EditText editText = (EditText) findViewById(R.id.name);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    handled = true;
                    adapter.notifyDataSetChanged();
                }
                return handled;
            }

            private void sendMessage() {
                if (teams.size() != capacity) {
                    teams.add(new Participant(editText.getText().toString()));
                    teamNames.add("Team " + teams.size() + ": " + editText.getText().toString());
                    editText.getText().clear();
                } else {
                    editText.setText("Number of specified teams reached");
                }
            }

        });
    }


    /*
    need to find a better way to represent the teams participating in the tournament
    to support direct editing and a better visualization for now we move on
     */



}
