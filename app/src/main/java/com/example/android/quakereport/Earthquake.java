package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {

    private Double mMagnitude;

    private String mLocation;

//    private String mDate;

    private long mTimeInMilliseconds;

    public Earthquake(Double vMagnitude, String vLocation, long vTimeInMilliseconds){

        mMagnitude = vMagnitude;
        mLocation = vLocation;
        mTimeInMilliseconds = vTimeInMilliseconds;

    }

    public Double getmMagnitude(){
        return mMagnitude;
    }

    public String getmLocation(){
        return mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }

    //    public String getmDate() {
//        return mDate;
//    }

}
