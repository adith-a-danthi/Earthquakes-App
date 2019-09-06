package com.example.android.quakereport;

import java.util.Date;

public class Earthquake {

    private String mMagnitude;

    private String mLocation;

//    private String mDate;

    private long mTimeInMilliseconds;

    public Earthquake(String vMagnitude, String vLocation, long vTimeInMilliseconds){

        mMagnitude = vMagnitude;
        mLocation = vLocation;
        mTimeInMilliseconds = vTimeInMilliseconds;

    }

    public String getmMagnitude(){
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
