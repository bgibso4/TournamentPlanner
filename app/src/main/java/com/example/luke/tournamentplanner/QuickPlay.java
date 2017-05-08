package com.example.luke.tournamentplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class QuickPlay extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);

        Intent intent = getIntent();
        int roundNumber= intent.getExtras().getInt("Round Number");
        TextView textView = (TextView) findViewById(R.id.roundText);

        //a list of all the teams that are still left inn the tourney
        //first creation is all teams and then it will be passed just the teams that win from then on
        //
        //TO DO DOUBLE ELIMINATION YOU COULD JUST CREATE ANOTHER LIST FOR TEAMS THAT HAVE BEEN DEFEATED ONLY ONCE
        //
        ArrayList<Participant> teamsLeft= intent.getExtras().getParcelableArrayList("Tournament_Teams");

        //creating the round and the games for the round
        RoundMaker round= new RoundMaker(teamsLeft);
        ArrayList<Game> currentGames= round.getMatches();

        //setting the text at the top of the screen
        //I want too add what the meaning of the round is......ie. semis, finals, championship...
        //thinking of doing this by passing a message each time based on what the current stage is
        textView.setText("Round #"+Integer.toString(roundNumber));



    }


    public class MatchAdapter extends ArrayAdapter<Participant> {
        public MatchAdapter(Context context, ArrayList<Participant> teams){
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

            Button btnPlay = (Button)convertView.findViewById(R.id.delete);
            //storing the specific player with respect to its designated delete button
            btnPlay.setTag(player);
            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //instead of editing an entry I will simply support the
                    //deletion of a team to allow for the correct team name to be passed
                    /////////////////teams.remove(view.getTag());
                    //receiving the updates
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}
