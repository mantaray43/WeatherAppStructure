package com.weatherornot.weatherornot;

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

public class GeonameAPITask extends AsyncTask<Location, Integer, String> {

    public String myCity;
    public String myURL = "http://api.geonames.org/findNearbyPlaceNameJSON?";
    public String API_User= "mantaray43";
    public String mlatitude;
    public String mLongitude;
    public DisplayWeatherActivity cityDisplay;

    public String geoAssembledURL(Location l){

        String cityURL;
        cityURL = myURL  + "lat=" + l.getLatitude() + "&lng=" + l.getLongitude() + "&username=" + API_User;
        return cityURL;

    }

    public GeonameAPITask(DisplayWeatherActivity cityDisplay) {
        this.cityDisplay = cityDisplay;
    }

    @Override
    protected String doInBackground(Location... myLocations) {


       String myCity = "none";

        try{


            HttpClient httpClient = new DefaultHttpClient();
            HttpGet c = new HttpGet(geoAssembledURL(myLocations[0]));
            HttpResponse httpResponse = httpClient.execute(c);
            StatusLine statusLine = httpResponse.getStatusLine();
            if(statusLine.getStatusCode()== HttpStatus.SC_OK){
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                httpResponse.getEntity().writeTo(out);
                out.close();
                String responseString = out.toString();
                Log.e("look",responseString);


                JSONObject gRootJSON = new JSONObject(responseString);
                JSONArray geonamesJSON = gRootJSON.getJSONArray("geonames");

                if(geonamesJSON.length() > 0){

                    JSONObject cName = geonamesJSON.getJSONObject(0);
                    JSONObject sName = geonamesJSON.getJSONObject(0);

                    //cName.getJSONObject("name");

                    myCity = cName.getString("name")+ ", " + sName.getString("adminCode1" );

                    Log.e("LOOK we have the city name", myCity);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return myCity;

    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected void onPostExecute(String cityData) {

        super.onPostExecute(cityData);
        cityDisplay.updateMyCity(cityData);

        Log.e("LOOK, city is displayed", cityData);

    }

}
