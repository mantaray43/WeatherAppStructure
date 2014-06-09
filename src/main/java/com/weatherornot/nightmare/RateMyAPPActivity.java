package com.weatherornot.nightmare;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.view.View.OnClickListener;


public class RateMyAPPActivity extends Activity {
    static final String PREFERENCES = "temps";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_my_app);
        getActionBar();

        Button rateIt = (Button) findViewById(R.id.ratebutton);
        rateIt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW);
                //Try Google play
                intent.setData(Uri.parse("market://details?id=[weatherornot.nightmare]"));
                if (!MyStartActivity(intent)) {
                    //Market (Google play) app seems not installed, let's try to open a webbrowser
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?[weatherornot.nightmare]"));
                    //Well if this also fails, we have run out of options, inform the user.
                    if (!MyStartActivity(intent)) {
                        Toast.makeText(getApplicationContext(), "Hey, we couldn't open Google Play, please install the Google Play app. It will make you happy!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }




    private boolean MyStartActivity(Intent aIntent) {
        try {
            startActivity(aIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.Change:
                //removes shared preferences goes back to settings activity
                SharedPreferences myPrefs = getSharedPreferences(PREFERENCES, 0);
                SharedPreferences.Editor editor = myPrefs.edit();
                myPrefs.edit().clear().commit();
                Intent boo = new Intent(getApplicationContext(), DisplaySettingsActivity.class);
                startActivity(boo);

                return true;

            case R.id.email:
                Intent email = new Intent(getApplicationContext(), Email_activity.class);
                startActivity(email);
                return true;

            case R.id.rate:
                Intent rate = new Intent(getApplicationContext(), RateMyAPPActivity.class);
                startActivity(rate);


            default:
                return super.onOptionsItemSelected(item);
        }

    }

}












