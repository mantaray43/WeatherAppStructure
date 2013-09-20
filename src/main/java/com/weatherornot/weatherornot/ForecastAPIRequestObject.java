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

/**
 * Created by cheryl on 9/3/13.
 */
public class ForecastAPIRequestObject {


    public Double mlatitude;
    public Double mlongitude;
    private String mURL ="https://api.forecast.io/forecast/";
    private String mAPI_KEY = "e23af31f7c88b9c76b05b3bbcaae176f";

    public Location myLocation;






    public ForecastAPIRequestObject (Location myLocation){
           this.setMyLocation(myLocation);

}

    //private void setMyLocation() {
    //}

    public void setMyLocation(Location myLocation) {
            this.myLocation = myLocation;




    }

    public String getAssembledURL() {
            String pantsURL;
            pantsURL = mURL + mAPI_KEY + "/" + mlatitude.toString() + "," + mlongitude.toString();
            return pantsURL;
      





}

    public Double getMlatitude() {
        return mlatitude;
    }

    public void setMlatitude(Double mlatitude) {
        this.mlatitude = mlatitude;
    }

    public Double getMlongitude() {
        return mlongitude;
    }

    public void setMlongitude(Double mlongitude) {
        this.mlongitude = mlongitude;
    }

    public String getmURL() {
        return mURL;
    }

    public void setmURL(String mURL) {
        this.mURL = mURL;
    }

    public String getmAPI_KEY() {
        return mAPI_KEY;
    }

    public void setmAPI_KEY(String mAPI_KEY) {
        this.mAPI_KEY = mAPI_KEY;
    }

    public Location getMyLocation() {
        return myLocation;
    }

}








