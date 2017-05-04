package com.example.luke.tournamentplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by luke on 2017-05-03.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] tournyStyles;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] types){
        tournyStyles = types;
        context = applicationContext;
        inflter =(LayoutInflater.from(applicationContext));

    }
    @Override
    public int getCount() {
        return tournyStyles.length;
    }

    @Override
    public Object getItem(int i) {
        return tournyStyles[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_tournyoptions, null);
        TextView type = (TextView) view.findViewById(R.id.type);
        type.setText(tournyStyles[i]);
        return view;
    }
}
