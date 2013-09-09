package com.weatherornot.weatherornot;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

//The main activity that we will be using to display weather data


public class DisplayWeatherActivity extends Activity {




    public UserLocationManager mMyLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("duh","it runs");
        getWeather();

    }

    ////////////////////////////////////////////////
    public void getWeather(){       //asking for the weather
        Log.e("now", "get Weather runs");
        new GetWeatherDataTask(this);  //z is a new GetWeatherDataTask get the data and receiving the
        //instance of DisplayWeatherActivity - this refers to the instance of DisplayWeatherActivity(on create) that just got created.

    }

/////////////////////////////////////////////////////
    public void receiveWeatherData (PantsWeatherData myDataObject){
        Log.e("look","step 3 receive weather data works");

        TextView textview = (TextView) findViewById(R.id.currenttemp);
        textview.setText(myDataObject.getmCurrentTempString());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
