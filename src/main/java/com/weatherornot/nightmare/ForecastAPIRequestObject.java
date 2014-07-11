package com.weatherornot.nightmare;

import android.location.Location;


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


        return pantsURL;

    }

}