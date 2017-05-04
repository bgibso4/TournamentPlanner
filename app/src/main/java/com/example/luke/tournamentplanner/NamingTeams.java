package com.example.luke.tournamentplanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NamingTeams extends AppCompatActivity {

    private ArrayList<Participant> teams = new ArrayList<>();
    private ListView list;
    public static final String EXTRA_MESSAGE = "com.example.luke.tournamentplanner.MESSAGE";

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
                    editText.getText().clear();
                } else {
                    editText.getText().clear();
                    Toast.makeText(getApplicationContext(),"Capactiy reached!", Toast.LENGTH_LONG).show();
                }
            }

        });


        //creating the confirmation button at the bottom of the screen
        Button confirmBtn = (Button)findViewById(R.id.next);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating a toast(reminder) if the user clicks the button before entering all the teams
                if(teams.size()!=capacity){
                    Toast.makeText(getApplicationContext(),"Please enter the correct number of teams", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(NamingTeams.this, bracket.class);
                    intent.putExtra("Tournament_Teams", teams);
                    startActivity(intent);
                }

                //receiving the updates
                //notifyDataSetChanged();
            }
        });



        
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
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_participant_listview, parent, false);
            }
            //getting the name form the editText and initializing it to the textVIew
            TextView teamName = (TextView) convertView.findViewById(R.id.name);
            teamName.setText(player.getName());

            Button btnDelete = (Button)convertView.findViewById(R.id.delete);
            //storing the specific player with respect to its designated delete button
            btnDelete.setTag(player);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //instead of editting an entry I will simply support the
                    //deletion of a team to allow for the correct team name to be passed
                    teams.remove(view.getTag());
                    //receiving the updates
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

}
