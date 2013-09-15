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

import static java.lang.reflect.Array.getChar;
import static java.lang.reflect.Array.getDouble;


//                                               params                    progress     result
public class GetWeatherDataTask extends AsyncTask<ForecastAPIRequestObject,Integer,PantsWeatherData> {
    public DisplayWeatherActivity pantsWeatherDisplay;
    UserLocationManager myLocationManager;
    public Date formattedTime;








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
    public void receiveUserLocation(Location location){
        ForecastAPIRequestObject forecastAPIRequestObject = new ForecastAPIRequestObject(location);
        this.execute(forecastAPIRequestObject);

        Log.e("look", "step 6 - step 5 has successfully worked");
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

                //get the temperature
                myData.setmCurrentTemp(currentlyJSON.getDouble("temperature"));




                JSONObject hourlyJSON = rootJSON.getJSONObject("hourly");
                JSONArray hourlyDataJSON = hourlyJSON.getJSONArray("data");

                String[] myHourlyText = new String[hourlyDataJSON.length()];

                for(int i = 0; i<hourlyDataJSON.length();i++){


                    JSONObject name = hourlyDataJSON.getJSONObject(i);
                    Long value = hourlyDataJSON.getJSONObject(i).getLong("time");
                    SimpleDateFormat format = new SimpleDateFormat("hh:mm aa");
                    formattedTime = new Date();

                    try {
                        formattedTime = format.parse(value.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    Date now = new Date();
//                    Date giveDate = Calendar.getInstance().getTime();
//                    String nowAsString = new SimpleDateFormat("EEEE,  LLLLL  dd,  yyyy").format(now);




                    Double temperature = name.getDouble("temperature");




                    String summary = name.getString("summary");



                    //create the string you want to display
                    String a = formattedTime.toString() + " " + temperature.toString() + " " + summary.toString();
                    myHourlyText[i] = a;

                    Log.e("getweatherdatatask", formattedTime.toString() + " , " + temperature.toString() + summary.toString());
                }

                myData.setmHourlyData(myHourlyText);



                // This is for future versions of the application
//                HashMap<Long,JSONObject> hourlyHashMap = new HashMap<Long,JSONObject>();
//                for(int i = 0; i<hourlyDataJSON.length();i++){
//                    Long value = hourlyDataJSON.getJSONObject(i).getLong("time");
//                    JSONObject name = hourlyDataJSON.getJSONObject(i);
//                    hourlyHashMap.put(value,name);
//                    Double temperature = name.getDouble("temperature");
//
//
//                    //hourlyHashMap.put(String, value);
//                    Log.e("getweatherdatatask", value.toString() + " , " + temperature.toString());
//                }
//               hourlyDataJSON.length();




               Log.e("Look","Shirley Farted parsing data");



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


    //public void receiveUserLocation(Location location) {
//    }
}
