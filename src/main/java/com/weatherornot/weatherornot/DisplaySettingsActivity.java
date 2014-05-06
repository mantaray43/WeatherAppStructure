package com.weatherornot.weatherornot;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class DisplaySettingsActivity extends Activity {

    static final String PREFERENCES = "temps";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        checkIfNetworkLocationAvailable();

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
                int x = Integer.parseInt(hot);
                editor.putInt("hot", x);
                String cold = c.getText().toString();
                int y = Integer.parseInt(cold);
                editor.putInt("cold", y);
                String perfect = p.getText().toString();
                int z = Integer.parseInt(perfect);
                editor.putInt("perfect", z);
                editor.putBoolean("tempSaved",true);
                editor.commit();
                finish();

                editor.putBoolean("tempSaved",true);

                Intent toWeather = new Intent(getApplicationContext(), DisplayWeatherActivity.class);
                startActivity(toWeather);
                finish();

            }
        });

    }//end on create





    /**
     * checks if NETWORK LOCATION PROVIDER is available
     */
    private void checkIfNetworkLocationAvailable() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean networkLocationEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if(networkLocationEnabled==false){
            //show dialog to allow user to enable location settings
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Location Services Not Active");
            dialog.setMessage("Please enable Location Services and GPS");

            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);



                }

            });


            dialog.show();


        }
        if(networkLocationEnabled==true){
            loadPrefs();
        }
    }
    private void loadPrefs(){
        SharedPreferences myPrefs = getSharedPreferences(PREFERENCES, 0);
        boolean lp = myPrefs.getBoolean("tempSaved",false);

        if(lp == true){
            Intent i = new Intent(getApplicationContext(),DisplayWeatherActivity.class);
            startActivity(i);

        }else if(lp == false){
            onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}