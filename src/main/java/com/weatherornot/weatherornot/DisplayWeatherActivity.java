package com.weatherornot.weatherornot;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DisplayWeatherActivity extends Activity {

    ListView mListView;

    static String TIME = "time";
    static String TEMPERATURE = "temperature";
    public String giveDate;
//    public UserLocationManager mMyLocationManager;
    public PantsWeatherData cityData;

    /////1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView)findViewById(R.id.hourly);

/////2
        //////setting background image as drawable
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
                }
                catch (IOException e){

                }
            }
        }


        //setting date from phone
        Date now = new Date();
        Date giveDate = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("EEEE,  LLLLL  dd,  yyyy").format(now);


        TextView dateview = (TextView) findViewById(R.id.date);
        dateview.setText(nowAsString);
        getWeather();/////////////?

//        new GeonameAPIObject().execute();
//        TextView v = (TextView)findViewById(R.id.location);
//        v.setText((CharSequence) cityData);


    }

////3
    ////////////////////////////////////////////////
    public void getWeather(){       //asking for the weather
        new GetWeatherDataTask(this);  //z is a new GetWeatherDataTask get the data and receiving the
        //instance of DisplayWeatherActivity - this refers to the instance of DisplayWeatherActivity(on create) that just got created.
    }
/////////////////////////////////////////////////////
    public void receiveWeatherData (PantsWeatherData myDataObject){

        TextView textView = (TextView) findViewById(R.id.currenttemp);
        String mCurrentTemp = myDataObject.getmCurrentTempString();
        String roundedDouble = "";
        roundedDouble = mCurrentTemp.substring(0,mCurrentTemp.indexOf('.'));
        textView.setText(roundedDouble + "\u00B0");

        mListView = (ListView)findViewById(R.id.hourly);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, myDataObject.getmHourlyData() );

        mListView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
}


