package com.weatherornot.weatherornot;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
    private final String CLOUDY = "CLOUDY";
    private final String CLEAR_DAY = "CLEAR-DAY";
    private final String CLEAR_NIGHT = "CLEAR-NIGHT";
    private final String RAIN = "RAIN";
    private final String SNOW = "SNOW";
    private final String SLEET = "SLEET";
    private final String WIND = "WIND";
    private final String FOG = "FOG";
    private final String PARTLY_CLOUDY_DAY = "PARTLY-CLOUDY-DAY";
    private final String PARTLY_CLOUDY_NIGHT = "PARTLY-CLOUDY-NIGHT";
    private final String HAIL = "HAIL";
    private final String THUNDERSTORMS = "THUNDERSTORMS";
    private final String TORNADO = "TORNADO";

    public static void setTheIcon(int theIcon) {
        DisplayWeatherActivity.theIcon = theIcon;
    }

    public static int theIcon;


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



    }

////3
    ////////////////////////////////////////////////
    public void getWeather(){       //asking for the weather
        new GetWeatherDataTask(this);  //z is a new GetWeatherDataTask get the data and receiving the
        //instance of DisplayWeatherActivity - this refers to the instance of DisplayWeatherActivity(on create) that just got created.
    }
/////////////////////////////////////////////////////

    

    /////////////////////////////////////////////////////
    public void receiveWeatherData (PantsWeatherData myDataObject){

        TextView textView = (TextView) findViewById(R.id.currenttemp);
        String mCurrentTemp = myDataObject.getmCurrentTempString();
        String roundedDouble = "";
        roundedDouble = mCurrentTemp.substring(0,mCurrentTemp.indexOf('.'));
        textView.setText(roundedDouble + "\u00B0");

        String icon = myDataObject.getIcon();

        Drawable theIcon;

            if(icon.equalsIgnoreCase(CLOUDY)){
                theIcon = getResources().getDrawable (R.drawable.cloudy);
            }else if (icon.equalsIgnoreCase(CLEAR_DAY)){
                theIcon = getResources().getDrawable (R.drawable.sunstandin);
            }else if (icon.equalsIgnoreCase(CLEAR_NIGHT)){
                theIcon = getResources().getDrawable (R.drawable.clearnight);
            }else if (icon.equalsIgnoreCase(RAIN)){
                theIcon = getResources().getDrawable (R.drawable.rain);
            }else if (icon.equalsIgnoreCase(SNOW)){
                theIcon = getResources().getDrawable (R.drawable.snow);
            }else if (icon.equalsIgnoreCase(SLEET)){
                theIcon = getResources().getDrawable (R.drawable.sleet);
            }else if (icon.equalsIgnoreCase(WIND)){
                theIcon = getResources().getDrawable (R.drawable.windy);
            }else if (icon.equalsIgnoreCase(FOG)){
                theIcon = getResources().getDrawable (R.drawable.fog);
            }else if (icon.equalsIgnoreCase(PARTLY_CLOUDY_DAY)){
                theIcon = getResources().getDrawable (R.drawable.partlycloudyday);
            }else if (icon.equalsIgnoreCase(PARTLY_CLOUDY_NIGHT)){
                theIcon = getResources().getDrawable (R.drawable.partlycloudynight);;
            }else if (icon.equalsIgnoreCase(THUNDERSTORMS)){
                theIcon = getResources().getDrawable (R.drawable.thunderstorms);
            }else theIcon = getResources().getDrawable (R.drawable.tornado);

        ImageView weatherIconView = (ImageView) findViewById(R.id.icon);
        weatherIconView.setImageDrawable(theIcon);


        mListView = (ListView)findViewById(R.id.hourly);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, myDataObject.getmHourlyData() );

        mListView.setAdapter(adapter);

        

        new GeonameAPITask(this).execute(myDataObject.myGeoLocation);

    }



    public void updateMyCity(String cityData){
        TextView v = (TextView)findViewById(R.id.location);
        v.setText(cityData);
    }




//    //you could do it this way (but dont for this project)
//
//    public class AnotherAPICallTask extends AsyncTask<Location,Integer,String>
//    {
//        @Override
//        protected String doInBackground(Location... locationArray) {
//            //use locationArray[0] to refer to the input
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//
//            //one way:
//            //myTextView.setText(s)
//            //
//            //or the way you're already doing it
//            //
//            //updateMyCity(s);
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//        }
//    }




    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }



}


