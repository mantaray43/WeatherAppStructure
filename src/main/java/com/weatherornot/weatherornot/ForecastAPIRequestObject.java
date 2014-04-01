package com.weatherornot.weatherornot;

import android.annotation.TargetApi;
import android.location.Location;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;


public class ForecastAPIRequestObject {


    public Double mlatitude;
    public Double mlongitude;
    private String mURL = "https://api.forecast.io/forecast/";
    private String mAPI_KEY = "e23af31f7c88b9c76b05b3bbcaae176f";

    public Location myLocation;

    ////9
    public ForecastAPIRequestObject(Location myLocation) {
        this.setMyLocation(myLocation);
    }

    ///10
    public void setMyLocation(Location myLocation) {
        this.myLocation = myLocation;
        mlatitude = myLocation.getLatitude();
        mlongitude = myLocation.getLongitude();

    }

    ///10.5
    public String getAssembledURL() {
        String pantsURL;
        pantsURL = mURL + mAPI_KEY + "/" + mlatitude.toString() + "," + mlongitude.toString();
        Log.e("LOOK------------------------------------- we have location data!", mlatitude.toString() + " " + mlongitude.toString());

        return pantsURL;

    }

}








