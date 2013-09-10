package com.weatherornot.weatherornot;

import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

//The main activity that we will be using to display weather data



public class DisplayWeatherActivity extends Activity {

    ListView mListView;
    ArrayList<JSONArray> myJSONArrayList = new ArrayList<JSONArray>();
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<Long,JSONObject>> data;
    HashMap<Long,JSONObject> resultp = new HashMap<Long,JSONObject>();
    static String TIME = "time";
    static String TEMPERATURE = "temperature";


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
