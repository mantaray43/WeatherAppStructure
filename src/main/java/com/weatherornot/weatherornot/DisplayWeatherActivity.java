package com.weatherornot.weatherornot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
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
import java.util.Random;

import android.graphics.Typeface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DisplayWeatherActivity extends Activity {

    ListView mListView;
    Typeface font;

    String fontPathB = "fonts/playtime.ttf";
    String fontPathC = "fonts/edo.ttf";

    static String TIME = "time";
    static String TEMPERATURE = "temperature";
    public String giveDate;
    //public UserLocationManager mMyLocationManager;
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
    static final String PREFERENCES = "temps";


    /////1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //shoot off the display settings activity
        Intent intent = new Intent();
        Intent i = new Intent(getApplicationContext(), DisplaySettingsActivity.class);
        startActivity(i);

        //get the prefs
        SharedPreferences myPrefs = getApplicationContext().getSharedPreferences("PREFERENCES", 0);
        SharedPreferences.Editor editor = myPrefs.edit();


        if (myPrefs.getBoolean("prefscompleted", false)) {
            Intent intentPrefs;
            intentPrefs = new Intent(getApplicationContext(), DisplaySettingsActivity.class);
            startActivity(intentPrefs);
        }



        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.hourly);


/////2
        //////setting background image as drawable
        //  (THE HARD WAY)
//        AssetManager manager = getAssets();
//        InputStream open = null;
//
//        try {
//            open = manager.open("chalkboardbackground.png");
//            Bitmap bitmap = BitmapFactory.decodeStream(open);
//            ImageView view = (ImageView) findViewById(R.id.background);
//            view.setImageBitmap(bitmap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (open != null) {
//                try {
//                    open.close();
//                } catch (IOException e) {
//
//                }
//            }
//        }

//      //////setting background image as drawable, if it was an image view
        // its not, its a linear layout- so dont use this code.
        // also you're already setting it.
//        ImageView backgroundImageView = (ImageView) findViewById(R.id.background);
//        int resID = this.getResources().getIdentifier("chalkboardbackground", "drawable", this.getPackageName());
//        backgroundImageView.setImageResource(resID);


        //setting date from phone
        Date now = new Date();
        Date giveDate = Calendar.getInstance().getTime();
        String nowAsString = new SimpleDateFormat("EEEE,  LLLLL  dd,  yyyy").format(now);


        TextView dateview = (TextView) findViewById(R.id.date);

        dateview.setText(nowAsString);
        getWeather();/////////////?

        TextView templabel = (TextView) findViewById(R.id.tempLabel);
        font = Typeface.createFromAsset(getAssets(), fontPathB);
        templabel.setTypeface(font);

        TextView hourlyscroll = (TextView) findViewById(R.id.Hourlyscroll);
        font = Typeface.createFromAsset(getAssets(), fontPathB);
        hourlyscroll.setTypeface(font);

        TextView snarky = (TextView) findViewById(R.id.snarky);
        font = Typeface.createFromAsset(getAssets(), fontPathB);
        snarky.setTypeface(font);


//        Intent snark = new Intent();
//        Intent b = new Intent(getApplicationContext(),SnarkyDisplay.class);
//        startActivity(b);




    }


    ////3
    ////////////////////////////////////////////////
    public void getWeather() {       //asking for the weather
        new GetWeatherDataTask(this);  //z is a new GetWeatherDataTask get the data and receiving the
        //instance of DisplayWeatherActivity - this refers to the instance of DisplayWeatherActivity(on create) that just got created.
    }
/////////////////////////////////////////////////////


    /////////////////////////////////////////////////////
    public void receiveWeatherData(PantsWeatherData myDataObject) {

        TextView textView = (TextView) findViewById(R.id.currenttemp);

        String mCurrentTemp = myDataObject.getmCurrentTempString();
        String roundedDouble = "";
        roundedDouble = mCurrentTemp.substring(0, mCurrentTemp.indexOf('.'));
        textView.setText(roundedDouble + "\u00B0");

        mListView = (ListView) findViewById(R.id.hourly);
        ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.row, myDataObject.getmHourlyData());
        ////////////////////is there an error here with context?///////
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
        int currentTemp = (int) myDataObject.getmCurrentTemp().intValue();
        //pass it to the snark
        determineSnark(currentTemp);
    }


    public void updateMyCity(String cityData) {
        TextView v = (TextView) findViewById(R.id.location);
        font = Typeface.createFromAsset(getAssets(), fontPathC);
        v.setTypeface(font);
        v.setText(cityData);
    }

//    public void receiveSnarkyData(SnarkyDisplay rootJSON){
//        new SnarkyDisplay(this);


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //
    //   Here is all the code from SnarkyDisplay moved over to this
    //   Activity so we can use it on our display.
    //

    static SharedPreferences settings;

    String hotString = "temphot";
    String coldString = "tempcold";
    String perfectString = "tempperfect";
    String range;

    public void determineSnark(int mCTemp) {

        settings = getSharedPreferences(PREFERENCES, 0);
        SharedPreferences.Editor editor = settings.edit();
        int hot = new Integer(settings.getString(hotString, ""));
        SharedPreferences.Editor editorCold = settings.edit();
        int cold = new Integer(settings.getString(coldString, ""));
        SharedPreferences.Editor editorPerfect = settings.edit();
        int perfect = new Integer(settings.getString(perfectString, ""));
//
//        //this is getting a reference to the view
//        TextView currentTemp = (TextView) findViewById(R.id.currenttemp);
//        //creating a string of whats currenty being displayed
//        String cTemp = currentTemp.getText().toString();
        //this is converting that to an integer


        if (mCTemp > cold) {    //bittercold
            range = "bittercold";
        } else if ((mCTemp >= cold) && (mCTemp <= (cold + 10))) {     //toocold
            range = "toocold";
        } else if ((mCTemp > (cold + 10) && (mCTemp <= (cold + 17)))) {      //good
            range = "good";
        } else if ((mCTemp > (cold + 10) && (mCTemp <= (perfect + 10)))) {   //perfect
            range = "perfect";
        } else if ((mCTemp > (perfect + 10) && (mCTemp <= (hot - 5)))) {   //warm
            range = "warm";
        } else if (mCTemp > (hot - 5) && (mCTemp <= (hot + 3))) { //too hot
            range = "toohot";
        } else if (mCTemp > (hot + 4)) {
            range = "toodanghot";
        } else {
            range = "toodanghot";
        }

        displaySnarkiness(range);
    }

    public String getJSONFile() {

        //
        //  If you get a parsing error, use this website:
        //  http://jsonformatter.curiousconcept.com/
        //

        String json = null;

        try {
            InputStream is = getResources().openRawResource(R.raw.pantsjson);
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

            snarky.setText("MySnarkyComment");
            //here you have to set the image view
            String nameOfImage = "cloudy";

            //this code sets the image on myImageView
            ImageView myImageView = (ImageView) findViewById(R.id.pants);
            int resID = this.getResources().getIdentifier(nameOfImage, "drawable", this.getPackageName());
            myImageView.setImageResource(resID);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}


