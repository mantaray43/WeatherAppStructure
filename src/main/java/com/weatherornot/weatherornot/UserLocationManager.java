package com.weatherornot.weatherornot;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import static android.location.LocationManager.*;

/**
 * Created by spawrks on 8/30/13.
 */

//  This class wraps all the code we will use to manage getting updates on the location

public class UserLocationManager implements LocationListener{


    private GetWeatherDataTask getWeatherDataTask;
       LocationManager lm;



    public UserLocationManager (GetWeatherDataTask x){
        super();
        Log.e("look","rufus called location service");
        getWeatherDataTask = x;   // use getWeatherDataTask when onLocationChanged is called
        lm = (LocationManager) getWeatherDataTask.pantsWeatherDisplay.getSystemService(Context.LOCATION_SERVICE);

        try{
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);



        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public void onLocationChanged(Location location) {

        lm.removeUpdates(this);
        lm = null;
        getWeatherDataTask.receiveUserLocation(location);
        Log.e("Look", "Step 4 updates removed");

   }



    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



}
