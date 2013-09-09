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

import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.reflect.Array.getDouble;


//                                               params                    progress     result
public class GetWeatherDataTask extends AsyncTask<ForecastAPIRequestObject,Integer,PantsWeatherData> {
    public DisplayWeatherActivity pantsWeatherDisplay;
    UserLocationManager myLocationManager;



    public GetWeatherDataTask(DisplayWeatherActivity b){
        //this is the constructor//expecting a display weather activity and we are calling it B
        super();
        Log.e("look","step 2 works");
        pantsWeatherDisplay = b;  //This is a DisplayWeatherActivity (refers to our display view) we
        //are referencing it as pantsWeatherDisplay a
        //myLocationManager = new UserLocationManager(this);
        goGetLocation();

    }

    public void goGetLocation(){
        Log.e("look","shirley step 3");
        myLocationManager = new UserLocationManager(this);

    }
    public void receivePantsData(Location location){
        ForecastAPIRequestObject forecastAPIRequestObject = new ForecastAPIRequestObject(location);
        this.execute(forecastAPIRequestObject);
    }
    //public void ForecastAPIRequestObject(Location location)


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

                myData.setmCurrentTemp(currentlyJSON.getDouble("temperature"));

                Log.e("Look","Shirley Farted");



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


    public void receiveUserLocation(Location location) {
    }
}
