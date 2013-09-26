package com.weatherornot.weatherornot;


import android.content.Context;
import android.content.Intent;
import android.location.Location;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
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
import org.json.JSONTokener;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Array.getChar;
import static java.lang.reflect.Array.getDouble;

//                                               params                    progress     result
public class GetWeatherDataTask extends AsyncTask<ForecastAPIRequestObject,Integer,PantsWeatherData> {
    public DisplayWeatherActivity pantsWeatherDisplay;
    UserLocationManager myLocationManager;
    public Date formattedTime;

/////4
    public GetWeatherDataTask(DisplayWeatherActivity b){
            //this is the constructor//expecting a display weather activity and we are calling it B
            super();
            Log.e("look","step 2 works");
            pantsWeatherDisplay = b;  //This is a DisplayWeatherActivity (refers to our display view) we
            //are referencing it as pantsWeatherDisplay a
            //myLocationManager = new UserLocationManager(this);
            goGetLocation();
    }

//////5
    public void goGetLocation(){

        myLocationManager = new UserLocationManager(this);
    }

///11
    public void receiveUserLocation(Location location){
        ForecastAPIRequestObject forecastAPIRequestObject = new ForecastAPIRequestObject(location);
        this.execute(forecastAPIRequestObject);

    }

    @Override                                           ///...means array of forecastAPIRequestObjects
    protected PantsWeatherData doInBackground(ForecastAPIRequestObject... forecastAPIRequestObjects) {
             PantsWeatherData myData = new PantsWeatherData();

    try{
        HttpClient httpClient = new DefaultHttpClient ();
        HttpGet g = new HttpGet(forecastAPIRequestObjects[0].getAssembledURL());
        HttpResponse httpResponse = httpClient.execute(g);
        StatusLine statusLine = httpResponse.getStatusLine();
            if(statusLine.getStatusCode()== HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();

                //parsing the data from the api using JSON methods

                JSONObject rootJSON = new JSONObject(responseString);
                JSONObject currentlyJSON = rootJSON.getJSONObject("currently");

                //get the temperature
                myData.setmCurrentTemp(currentlyJSON.getDouble("temperature"));
                //myData.getIcon(currentlyJSON.getString("icon"));/////////////////////////////////////////new



                JSONObject hourlyJSON = rootJSON.getJSONObject("hourly");
                JSONArray hourlyDataJSON = hourlyJSON.getJSONArray("data");

                String[] myHourlyText = new String[hourlyDataJSON.length()];

                for(int i = 0; i<hourlyDataJSON.length();i++){

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

                    Double temperature = name.getDouble("temperature");
                    String summary = name.getString("summary");
                   //create the string you want to display
                    String a = finalFormattedDate + "   " + temperature + "   " + summary.toString();
                    myHourlyText[i] = a;

                    Log.e("getweatherdatatask", finalFormattedDate + " , " + temperature + summary.toString());
                }

                myData.setmHourlyData(myHourlyText);

            }else{
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
    protected void onPostExecute(PantsWeatherData myData) {
        super.onPostExecute(myData);

        pantsWeatherDisplay.receiveWeatherData(myData);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

}
