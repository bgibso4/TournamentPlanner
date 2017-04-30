package com.example.luke.tournamentplanner;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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

    private ArrayList<Participant> teams = new ArrayList<>();
    private List<String> teamNames = new ArrayList<>();
    private ListView list;
    private Adapter MyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naming_teams);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.players);
        textView.setText(message);
        final int capacity = Integer.parseInt(message);

        //List view which will display the players participating in the tournament
        list = (ListView) findViewById(R.id.list);
        final ParticipantAdapter adapter = new ParticipantAdapter(this, teams);
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
                    //teamNames.add("Team " + teams.size() + ": " + editText.getText().toString());
                    editText.getText().clear();
                } else {
                    editText.setText("Number of specified teams reached");
                }
            }

        });

        //need to send the list of teams participating to the tournament scheduler
        
    }


    /*
    need to find a better way to represent the teams participating in the tournament
    to support direct editing and a better visualization for now we move on
     */

    public class ParticipantAdapter extends ArrayAdapter<Participant>{
        public ParticipantAdapter(Context context, ArrayList<Participant> teams){
            super(context, 0, teams);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            Participant player = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
            }
            TextView teamName = (TextView) convertView.findViewById(R.id.name);
            teamName.setText(player.getName());
            return convertView;
        }
    }

}