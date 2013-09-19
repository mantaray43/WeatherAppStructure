package com.weatherornot.weatherornot;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

//The main activity that we will be using to display weather data



public class DisplayWeatherActivity extends Activity {

    ListView mListView;

    static String TIME = "time";
    static String TEMPERATURE = "temperature";
    public String giveDate;



    //above code is in regards to the jason hashmap -


    public UserLocationManager mMyLocationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.hourly);


        ////////setting background image as drawable
        AssetManager manager = getAssets();
        InputStream open = null;

        try{
            open = manager.open("background_lg4");
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            ImageView view = (ImageView) findViewById(R.id.background);
            view.setImageBitmap(bitmap);
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(open!= null){
                try{
                    open.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }


        Date now = new Date();
        Date giveDate = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("EEEE,  LLLLL  dd,  yyyy").format(now);


        TextView dateview = (TextView) findViewById(R.id.date);
        dateview.setText(nowAsString);


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

//        TextView dateview = (TextView) findViewById(R.id.date);
//        dateview.setText(giveDate);

        TextView textview = (TextView) findViewById(R.id.currenttemp);
        //textview.setText(myDataObject.getmCurrentTempString());
        String mCurrentTemp = myDataObject.getmCurrentTempString();
        String roundedDouble = "";
        roundedDouble = mCurrentTemp.substring(0,mCurrentTemp.indexOf('.'));
        textview.setText(roundedDouble + "\u00B0");




        mListView = (ListView)findViewById(R.id.hourly);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, myDataObject.getmHourlyData() );
        
        mListView.setAdapter(adapter);
       }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
