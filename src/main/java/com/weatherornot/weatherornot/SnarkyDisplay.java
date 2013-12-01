package com.weatherornot.weatherornot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
public class SnarkyDisplay extends Activity {





    public SnarkyDisplay(DisplayWeatherActivity d){
        //this is the constructor//expecting a display weather activity and we are calling it B
        super();
        Log.e("look", "snarky works");

    }

}

