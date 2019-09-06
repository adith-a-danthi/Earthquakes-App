package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake){
        super(context, 0, earthquake);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {

        Earthquake currentEarthquake =getItem(position);
        View listItemView = convertView;

        if(convertView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        ((TextView) listItemView.findViewById(R.id.magnitudeTv))
                .setText(currentEarthquake.getmMagnitude());

        TextView locationTv = listItemView.findViewById(R.id.locationTv);
        locationTv.setText(currentEarthquake.getmLocation());


//        ((TextView) listItemView.findViewById(R.id.dateTv))
//                .setText(currentEarthquake.getmDate());

        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());

        String formattedDate = formatDate(dateObject);
        ((TextView) listItemView.findViewById(R.id.dateTv)).setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        ((TextView) listItemView.findViewById(R.id.timeTv)).setText(formattedTime);


        return listItemView;
    }

    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime (Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
    
}
