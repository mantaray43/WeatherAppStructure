package com.weatherornot.nightmare;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Email_activity extends Activity {
    static final String PREFERENCES = "temps";

    String to = "moreweatherpants@gmail.com";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alt_email_activity);
        getActionBar();
        TextView x = (TextView) findViewById(R.id.linkview);


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
                Intent boo = new Intent(this, DisplaySettingsActivity.class);
                startActivity(boo);

                return true;

            case R.id.email:
                Intent email = new Intent(this, Email_activity.class);
                startActivity(email);
                return true;

            case R.id.rate:
                Intent rate = new Intent(this, RateMyAPPActivity.class);
                startActivity(rate);


            default:
                return super.onOptionsItemSelected(item);
        }


    }

}