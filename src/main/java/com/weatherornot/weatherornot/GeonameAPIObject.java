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

//public class GeonameAPIObject extends AsyncTask<GeonameAPIObject, Integer, PantsWeatherData> {
//
//         public String myCity;
//         public String myURL = "http://api.geoname.org/findNearbyPlaceName?";
//         public String API_User= "mantaray43";
//         public String mlatitude;
//         public String mLongitude;
//         public PantsWeatherData cityData = new PantsWeatherData();
//         public DisplayWeatherActivity cityDisplay;
//
//         public String geoAssembledURL(){
//             String cityURL;
//             cityURL = myURL  + "lat=" + mlatitude + "&lng=" + mLongitude + "&username=" + API_User;
//             return cityURL;
//
//
//
//
//
//         }
//    @Override
//    protected PantsWeatherData doInBackground(GeonameAPIObject... PantsWeatherData) {
//
//        try{
//            HttpClient httpClient = new DefaultHttpClient();
//            HttpGet c = new HttpGet(geoAssembledURL());
//            HttpResponse httpResponse = httpClient.execute(c);
//            StatusLine statusLine = httpResponse.getStatusLine();
//            if(statusLine.getStatusCode()== HttpStatus.SC_OK){
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                httpResponse.getEntity().writeTo(out);
//                out.close();
//                String responseString = out.toString();
//                Log.e("look",responseString);
//
//
//                    JSONObject gRootJSON = new JSONObject(responseString);
//                    JSONArray geonamesJSON = gRootJSON.getJSONArray("geonames");
//
//
//                for(int i = 0; i<geonamesJSON.length();i++) {
//                    if (i > 0) {
//                        JSONObject cName = new JSONObject();
//                        cName.getJSONObject("name");
//
//                        String myCity = cName.getString("name");
//                        cityData.setMyCityName(myCity);
//
//                        Log.e("look", myCity);
//                    }
//                }
//            }
//            } catch (Exception e) {
//                e.printStackTrace();
//
//            }
//            return cityData;
//
//        }
//            @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//
//        protected void onPostExecute(PantsWeatherData cityData) {
//
//            super.onPostExecute(cityData);
//
//        }
//
//}
