package com.weatherornot.weatherornot;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

public class DisplaySettingsActivity extends Activity {

    static final String PREFERENCES = "temps";
    EditText hot;
    EditText cold;
    EditText perfect;
    int hot1;
    int cold1;
    int perfect1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);




        Button doneSaveButton = (Button) findViewById(R.id.donebutton);

        doneSaveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                EditText h = (EditText) findViewById(R.id.hotNumber);
                EditText c = (EditText) findViewById(R.id.coldNumber);
                EditText p = (EditText) findViewById(R.id.perfectNumber);

                SharedPreferences myPrefs = getSharedPreferences(PREFERENCES, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                String hot = h.getText().toString();
                editor.putString("hot", hot);
                String cold = c.getText().toString();
                editor.putString("cold", cold);
                String perfect = p.getText().toString();
                editor.putString("perfect", perfect);
                editor.commit();

                Log.e("LOOK--------------------------------- prefs saved", hot + cold + perfect);
                editor.putBoolean("prefscompleted", true);


                Intent toWeather = new Intent(getApplicationContext(), DisplayWeatherActivity.class);
                startActivity(toWeather);

                editor.commit();
                finish();


            }
        });
        {


        }
    }


}