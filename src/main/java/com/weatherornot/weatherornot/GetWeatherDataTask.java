package com.weatherornot.weatherornot;


import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


//                                               params                    progress     result
public class GetWeatherDataTask extends AsyncTask<ForecastAPIRequestObject, Integer, PantsWeatherData> {
    public DisplayWeatherActivity pantsWeatherDisplay;
    UserLocationManager myLocationManager;
    public Date formattedTime;
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
    public static int theIcon;

    public ProgressDialog waiting;


    /////4
    public GetWeatherDataTask(DisplayWeatherActivity b) {
        //this is the constructor//expecting a display weather activity and we are calling it B
        super();
        Log.e("LOOK-------------------------------------", "Get Weather Data task works");
        pantsWeatherDisplay = b;  //This is a DisplayWeatherActivity (refers to our display view) we
        //are referencing it as pantsWeatherDisplay a

        goGetLocation();


    }

    //////5
    public void goGetLocation() {

        myLocationManager = new UserLocationManager(this);


    }

    ///11
    public void receiveUserLocation(Location location) {
        ForecastAPIRequestObject forecastAPIRequestObject = new ForecastAPIRequestObject(location);
        this.execute(forecastAPIRequestObject);


    }

    public static void setTheIcon(int theIcon) {
        GetWeatherDataTask.theIcon = theIcon;

    }


    @Override
    ///...means array of forecastAPIRequestObjects
    public PantsWeatherData doInBackground(ForecastAPIRequestObject... forecastAPIRequestObjects) {

        PantsWeatherData myData = new PantsWeatherData();
        myData.myGeoLocation = forecastAPIRequestObjects[0].myLocation;


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet g = new HttpGet(forecastAPIRequestObjects[0].getAssembledURL());
            HttpResponse httpResponse = httpClient.execute(g);
            StatusLine statusLine = httpResponse.getStatusLine();
            if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();

                //parsing the data from the api using JSON methods

                JSONObject rootJSON = new JSONObject(responseString);
                JSONObject currentlyJSON = rootJSON.getJSONObject("currently");

                //get the temperature
                myData.setmCurrentTemp(currentlyJSON.getDouble("temperature"));
                myData.setIcon(currentlyJSON.getString("icon"));


                if (currentlyJSON.getString("icon").equalsIgnoreCase(CLOUDY))
                    theIcon = (R.drawable.cloudy);
                else if (currentlyJSON.getString("icon").equalsIgnoreCase(CLEAR_DAY)) {
                    theIcon = (R.drawable.sunstandin);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(CLEAR_NIGHT)) {
                    theIcon = (R.drawable.clearnight);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(RAIN)) {
                    theIcon = (R.drawable.rain);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(SNOW)) {
                    theIcon = (R.drawable.snow);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(SLEET)) {
                    theIcon = (R.drawable.sleet);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(WIND)) {
                    theIcon = (R.drawable.windy);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(FOG)) {
                    theIcon = (R.drawable.fog);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(PARTLY_CLOUDY_DAY)) {
                    theIcon = (R.drawable.partlycloudyday);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(PARTLY_CLOUDY_NIGHT)) {
                    theIcon = (R.drawable.partlycloudynight);
                } else if (currentlyJSON.getString("icon").equalsIgnoreCase(THUNDERSTORMS)) {
                    theIcon = (R.drawable.thunderstorms);
                } else theIcon = (R.drawable.tornado);


                Log.e("LOOK-------------------------------------------getting icon", (String) currentlyJSON.get("icon"));


                JSONObject hourlyJSON = rootJSON.getJSONObject("hourly");
                JSONArray hourlyDataJSON = hourlyJSON.getJSONArray("data");

                String[] myHourlyText = new String[hourlyDataJSON.length()];

                for (int i = 0; i < hourlyDataJSON.length(); i++) {

                    JSONObject name = hourlyDataJSON.getJSONObject(i);
                    Long value = hourlyDataJSON.getJSONObject(i).getLong("time");
                    Date hourlyDate;
                    hourlyDate = new Date(value * 1000);
                    SimpleDateFormat myFormat = new SimpleDateFormat("hh:mm aa");
                    String finalFormattedDate = "";

                    try {
                        finalFormattedDate = myFormat.format(hourlyDate);
                        //formattedDateTime = format.parse(value.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    int temperature = name.getInt("temperature");
                    String summary = name.getString("summary");
                    //create the string you want to display
                    String a = finalFormattedDate + "     " + temperature + "\u00BA" + "   " + summary;
                    myHourlyText[i] = a;

                    Log.e("LOOK---------------------------------------------------weather data", finalFormattedDate + " , " + temperature + summary);
                }

                myData.setmHourlyData(myHourlyText);

            } else {
                httpResponse.getEntity().getContent().close();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return myData;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    @Override
    public void onPostExecute(PantsWeatherData myData) {
        super.onPostExecute(myData);

        pantsWeatherDisplay.receiveWeatherData(myData);


        Log.e("LOOK---------------------------------------------------------------", "received weather data");

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }


}
