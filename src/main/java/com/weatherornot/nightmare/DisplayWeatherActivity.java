package com.weatherornot.nightmare;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;






public class DisplayWeatherActivity extends Activity {

    ListView mListView;
    Typeface font;
    String fontPathB = "fonts/playtime.ttf";
    String fontPathC = "fonts/edo.ttf";


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


    static final String PREFERENCES = "temps";
    ProgressDialog waiting;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar();

        AppRater.app_launched(this);
        mListView = (ListView) findViewById(R.id.hourly);
        getWeather();


        /* setting date from phone */
        Date now = new Date();
        now = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("EEEE,  LLLLL  dd,  yyyy").format(now);


        TextView dateview = (TextView) findViewById(R.id.date);

        dateview.setText(nowAsString);


        TextView templabel = (TextView) findViewById(R.id.tempLabel);
        font = Typeface.createFromAsset(getAssets(), fontPathC);
        templabel.setTypeface(font);

        TextView hourlyscroll = (TextView) findViewById(R.id.Hourlyscroll);
        font = Typeface.createFromAsset(getAssets(), fontPathC);
        hourlyscroll.setTypeface(font);

        TextView snarky = (TextView) findViewById(R.id.snarky);
        font = Typeface.createFromAsset(getAssets(), fontPathB);
        snarky.setTypeface(font);

    }


    ////////////////////////////////////////////
    public void getWeather() {       //asking for the weather
        new GetWeatherDataTask(this);


        waiting = new ProgressDialog(this);
        waiting.setTitle("Getting Weather Data!");
        waiting.setMessage("Good things come to those who wait.");
        waiting.setIndeterminate(true);
        waiting.show();

    }

    /////////////////////////////////////////////////////
    public void receiveWeatherData(PantsWeatherData myDataObject) throws ParseException {

        TextView textView = (TextView) findViewById(R.id.currenttemp);


        double mCurrentTemp = myDataObject.getmCurrentTemp();
        int temp = (int) mCurrentTemp;
        textView.setText(String.valueOf(temp) + "\u00B0");


        mListView = (ListView) findViewById(R.id.hourly);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, myDataObject.getmHourlyData());
        waiting.dismiss();
        mListView.setAdapter(adapter);

        String icon = myDataObject.getIcon();

        Drawable theIcon;

        if (icon.equalsIgnoreCase(CLOUDY)) {
            theIcon = getResources().getDrawable(R.drawable.cloudy);
        } else if (icon.equalsIgnoreCase(CLEAR_DAY)) {
            theIcon = getResources().getDrawable(R.drawable.sunstandin);
        } else if (icon.equalsIgnoreCase(CLEAR_NIGHT)) {
            theIcon = getResources().getDrawable(R.drawable.clearnight);
        } else if (icon.equalsIgnoreCase(RAIN)) {
            theIcon = getResources().getDrawable(R.drawable.rain);
        } else if (icon.equalsIgnoreCase(SNOW)) {
            theIcon = getResources().getDrawable(R.drawable.snow);
        } else if (icon.equalsIgnoreCase(SLEET)) {
            theIcon = getResources().getDrawable(R.drawable.sleet);
        } else if (icon.equalsIgnoreCase(WIND)) {
            theIcon = getResources().getDrawable(R.drawable.windy);
        } else if (icon.equalsIgnoreCase(FOG)) {
            theIcon = getResources().getDrawable(R.drawable.fog);
        } else if (icon.equalsIgnoreCase(PARTLY_CLOUDY_DAY)) {
            theIcon = getResources().getDrawable(R.drawable.partlycloudyday);
        } else if (icon.equalsIgnoreCase(PARTLY_CLOUDY_NIGHT)) {
            theIcon = getResources().getDrawable(R.drawable.partlycloudynight);
        } else if (icon.equalsIgnoreCase(THUNDERSTORMS)) {
            theIcon = getResources().getDrawable(R.drawable.thunderstorms);
        } else if (icon.equalsIgnoreCase(HAIL)) {
            theIcon = getResources().getDrawable(R.drawable.hail);
        } else if (icon.equalsIgnoreCase(TORNADO)) {
            theIcon = getResources().getDrawable(R.drawable.tornado);
        } else theIcon = getResources().getDrawable(R.drawable.sunstandin);

        ImageView weatherIconView = (ImageView) findViewById(R.id.icon);
        weatherIconView.setImageDrawable(theIcon);


        new GeonameAPITask(this).execute(myDataObject.myGeoLocation);


        //oh, go and update with a snarky message
        //convert the temperature to an int
        int currentTemp = temp;

        //pass it to the snark
        determineSnark(currentTemp);

    }


    public void updateMyCity(String cityData) {
        TextView v = (TextView) findViewById(R.id.location);
        font = Typeface.createFromAsset(getAssets(), fontPathC);
        v.setTypeface(font);
        v.setText(cityData);
    }


    //get snark
    public void determineSnark(int currentTemp) throws ParseException {

        String range;

        SharedPreferences myPrefs = getSharedPreferences(PREFERENCES, 0);
        SharedPreferences.Editor editor = myPrefs.edit();

        int hot1 = myPrefs.getInt("hot", 0);
        int cold1 = myPrefs.getInt("cold", 0);
        int perfect1 = myPrefs.getInt("perfect", 0);
        editor.commit();


        //this is getting a reference to the view
        TextView myTextView = (TextView) findViewById(R.id.currenttemp);


//        //creating a string of  the temp data from the api

        int mCTemp = currentTemp;


        if (mCTemp < cold1) {    //bittercold
            range = "bittercold";
        } else if ((mCTemp >= cold1) && mCTemp < (cold1 + 10)) {     //toocold
            range = "toocold";
        } else if ((mCTemp > (cold1 + 10) && (mCTemp <= (cold1 + 17)))) {      //good
            range = "good";
        } else if ((mCTemp > (cold1 + 10) && (mCTemp <= (perfect1 + 10)))) {   //perfect
            range = "perfect";
        } else if ((mCTemp > (perfect1 + 10) && (mCTemp <= (hot1 - 5)))) {   //warm
            range = "warm";
        } else if (mCTemp > (hot1 - 2) && (mCTemp <= (hot1 + 6))) { //too hot
            range = "toohot";
        } else if (mCTemp < (hot1 + 7)) {
            range = "toodanghot";
        } else {
            range = "toodanghot";
        }

        displaySnarkiness(range);


        myTextView = (TextView) findViewById(R.id.snarky);
    }

    public String getJSONFile() {

        //
        //  If you get a parsing error, use this website:
        //  http://jsonformatter.curiousconcept.com/
        //

        String json = null;

        try {
            InputStream is = getResources().openRawResource(R.raw.pantsjson02);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void displaySnarkiness(String snarkType) {

        try {
            JSONObject rootJSON = new JSONObject(getJSONFile());
            JSONArray snarkyCommentArray = rootJSON.getJSONArray(snarkType);
            int snarkyCommentArraySize = snarkyCommentArray.length();
            Random r = new Random();

            int randomObjectIndex = r.nextInt(snarkyCommentArraySize - 0) + 0;
            JSONObject selectedRandomObject = snarkyCommentArray.getJSONObject(randomObjectIndex);
            String text = selectedRandomObject.getString("text");

            TextView snarky = (TextView) findViewById(R.id.snarky);
            //here you have to actually set the text here

            snarky.setText(text);
            //here you have to set the image view
            String nameOfImage = selectedRandomObject.getString("image");

            //this code sets the image on myImageView
            ImageView myImageView = (ImageView) findViewById(R.id.pants);
            int resID = getResources().getIdentifier(nameOfImage, "drawable", getPackageName());
            myImageView.setImageResource(resID);

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.Change:
                //removes shared preferences goes back to settings activity
                SharedPreferences myPrefs = getSharedPreferences(PREFERENCES, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                myPrefs.edit().clear().commit();
                Intent boo = new Intent(this,DisplaySettingsActivity.class);
                startActivity(boo);

                return true;

            case R.id.email:
                Intent email = new Intent(this,Email_activity.class);
                startActivity(email);
                return true;

            case R.id.rate:
                Intent rate = new Intent(this,RateMyAPPActivity.class);
                startActivity(rate);



            default:
                return super.onOptionsItemSelected(item);
        }




    }

}