package com.weatherornot.weatherornot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

/**
 * Created by cheryl on 11/5/13.
 */
public class SnarkyDisplay extends DisplayWeatherActivity{

    static final String PREFERENCES = "temps";
    static SharedPreferences settings;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor editorCold;
    SharedPreferences.Editor editorPerfect;
    String hotString;
    String coldString;
    String perfectString;
    String range;
    String jsonb;
    JSONObject rootJSON = new JSONObject();


    int mCTemp;



@Override
public void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);

    settings = getSharedPreferences(PREFERENCES,0);
    SharedPreferences.Editor  editor = settings.edit();
    int hot = new Integer(settings.getString(hotString,""));
    SharedPreferences.Editor editorCold = settings.edit();
    int cold = new Integer(settings.getString(coldString,""));
    SharedPreferences.Editor editorPerfect = settings.edit();
    int perfect = new Integer(settings.getString(perfectString,""));

    TextView currentTemp = (TextView)findViewById(R.id.currenttemp);
    String cTemp = currentTemp.getText().toString();
    int mCTemp = Integer.valueOf(currentTemp.getText().toString());


    JSONObject rootJSON = new JSONObject();


    if (mCTemp > cold){    //bittercold
        range = "bittercold";

        try{
            JSONArray bitterCold = rootJSON.getJSONArray("bittercold");
            int bitterColdSize = bitterCold.length();
            Random r = new Random();
            int randomObjectIndex = r.nextInt(bitterColdSize-0) + 0;
            JSONObject selectedRandomObject = bitterCold.getJSONObject(randomObjectIndex);
            String text = selectedRandomObject.getString("text");
            TextView snarky = (TextView) findViewById(R.id.snarky);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }   else if ((mCTemp >= cold) && (mCTemp <= (cold + 10))){     //toocold
        range = "toocold";

            try{
                JSONArray tooCold = rootJSON.getJSONArray("toocold");
                int tooColdSize = tooCold.length();
                Random r = new Random();
                int randomObjectIndex = r.nextInt(tooColdSize-0) + 0;
                JSONObject selectedRandomObject = tooCold.getJSONObject(randomObjectIndex);
                String text = selectedRandomObject.getString("text");
                TextView snarky = (TextView) findViewById(R.id.snarky);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }   else if ((mCTemp > (cold +10) && (mCTemp <= (cold + 17)))){      //good
        range = "good";

            try{
                JSONArray good = rootJSON.getJSONArray("good");
                int goodSize = good.length();
                Random r = new Random();
                int randomObjectIndex = r.nextInt(goodSize-0) + 0;
                JSONObject selectedRandomObject = good.getJSONObject(randomObjectIndex);
                String text = selectedRandomObject.getString("text");
                TextView snarky = (TextView) findViewById(R.id.snarky);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }   else if ((mCTemp > (cold +10) && (mCTemp <= (perfect + 10)))){   //perfect
        range = "perfect";

            try{
                JSONArray perFect = rootJSON.getJSONArray("perfect");
                int perFectSize = perFect.length();
                Random r = new Random();
                int randomObjectIndex = r.nextInt(perFectSize-0) + 0;
                JSONObject selectedRandomObject = perFect.getJSONObject(randomObjectIndex);
                String text = selectedRandomObject.getString("text");
                TextView snarky = (TextView) findViewById(R.id.snarky);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }   else if ((mCTemp > (perfect + 10) && (mCTemp <= (hot - 5)))){   //warm
        range = "warm";

            try{
                JSONArray warm = rootJSON.getJSONArray("warm");
                int warmSize = warm.length();
                Random r = new Random();
                int randomObjectIndex = r.nextInt(warmSize-0) + 0;
                JSONObject selectedRandomObject = warm.getJSONObject(randomObjectIndex);
                String text = selectedRandomObject.getString("text");
                TextView snarky = (TextView) findViewById(R.id.snarky);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }   else if (mCTemp > (hot - 5) && (mCTemp <= (hot+3))) { //too hot
        range = "toohot";

            try{
                JSONArray tooHot = rootJSON.getJSONArray("toohot");
                int tooHotSize = tooHot.length();
                Random r = new Random();
                int randomObjectIndex = r.nextInt(tooHotSize-0) + 0;
                JSONObject selectedRandomObject = tooHot.getJSONObject(randomObjectIndex);
                String text = selectedRandomObject.getString("text");
                TextView snarky = (TextView) findViewById(R.id.snarky);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }   else if (mCTemp > (hot + 4)){
        range = "toodanghot";

            try{
                JSONArray tooDangHot = rootJSON.getJSONArray("toodanghot");
                int tooDangHotSize = tooDangHot.length();
                Random r = new Random();
                int randomObjectIndex = r.nextInt(tooDangHotSize-0) + 0;
                JSONObject selectedRandomObject = tooDangHot.getJSONObject(randomObjectIndex);
                String text = selectedRandomObject.getString("text");
                TextView snarky = (TextView) findViewById(R.id.snarky);
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }   else{
        range = "toodanghot";

    }

}
    public String getJSONFile(){

        String json = null;

        try{
            InputStream is = getResources().openRawResource(R.raw.pantsjson);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        }catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}

//using this class to work out code.  May be deleted.

//to display the snarky text and image based on the current temperature
//steps needed

//get shared prefs data and convert the strings to integers (may do that in the shared prefs section in DisplaySettings




//take shared prefs integers and create 7 number ranges that correspond to the JSON list ranges, bitter cold, too cold, good,
//perfect, warm, too hot, too dang hot

//take the too cold #
//(# + 10) == tooColdRange

//take the too cold #
// anything ># == bitterColdRange

//take too cold #
// (# + 17) == goodRange

//take perfect #
// (#-3) == range#one
// (#+10) == range#two    range#one - range#two = perfectRange

//take perfect #
//(#+5)   == range#one
//take toohot#
//(#-5)   == range#two    range#one - range#two == warmRange

//take toohot#
//(#-5) == range#one
//(#+3) == range#two    range#one - range#two = tooHotRange

//take toohot#
//anything <= (#+4)   == tooDangHotRange



//Ranges defined
//bitterCold #>
//tooCold #-#
//good #-#
//perfect #-#
//warm #-#
//tooHot #-#
//tooDangHot #-#

//compare current temp to see which range it falls in

//create JSON file

//parse JSON array according to temp range and display in UI
//key value pairs?

//either randomize the text/image data that is pulled from the JSON category, or go down the list numerically each time it is called - then rinse and repeat


