package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    //Variables for earthquake location
    String locationOffset;
    String primaryLocation;
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquake){
        super(context, 0, earthquake);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable  View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(convertView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        //Get the current Earthquakes object
        Earthquake currentEarthquake = getItem(position);

        // format magnitude to display in specified format
        String formattedMagnitude = formatMagnitude(currentEarthquake.getmMagnitude());
        ((TextView) listItemView.findViewById(R.id.magnitudeTv)).setText(formattedMagnitude);

        // format location to display in specified format
        String originalLocation = currentEarthquake.getmLocation();
        if(originalLocation.contains(LOCATION_SEPARATOR)){
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near);
            primaryLocation = originalLocation;
        }
        ((TextView) listItemView.findViewById(R.id.location_offsetTv)).setText(locationOffset);
        ((TextView) listItemView.findViewById(R.id.primary_locationTv)).setText(primaryLocation);


//        ((TextView) listItemView.findViewById(R.id.dateTv))
//                .setText(currentEarthquake.getmDate());

        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());

        // format date to display in specified format
        String formattedDate = formatDate(dateObject);
        ((TextView) listItemView.findViewById(R.id.dateTv)).setText(formattedDate);

        // format time to display in specified format
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

    private String formatMagnitude (double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

}
