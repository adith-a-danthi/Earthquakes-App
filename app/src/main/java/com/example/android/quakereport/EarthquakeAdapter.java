package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
        TextView magnitudeView = listItemView.findViewById(R.id.magnitudeTv);
        magnitudeView.setText(formattedMagnitude);

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
        TextView primaryLocationView = listItemView.findViewById(R.id.primary_locationTv);
        primaryLocationView.setText(primaryLocation);
        TextView locationOffsetView = listItemView.findViewById(R.id.location_offsetTv);
        locationOffsetView.setText(locationOffset);
//         <-! Alternate method without creating TextView objects !->
//        ((TextView) listItemView.findViewById(R.id.location_offsetTv)).setText(locationOffset);
//        ((TextView) listItemView.findViewById(R.id.primary_locationTv)).setText(primaryLocation);


//        ((TextView) listItemView.findViewById(R.id.dateTv))
//                .setText(currentEarthquake.getmDate());

        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());

        // format date to display in specified format
        String formattedDate = formatDate(dateObject);
        ((TextView) listItemView.findViewById(R.id.dateTv)).setText(formattedDate);

        // format time to display in specified format
        String formattedTime = formatTime(dateObject);
        ((TextView) listItemView.findViewById(R.id.timeTv)).setText(formattedTime);

        //Color and shape background for magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());
        magnitudeCircle.setColor(magnitudeColor);

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

    private int getMagnitudeColor(double magnitude){
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(magnitude);
        switch (magnitudeFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(),magnitudeColorResourceId);
    }

}
