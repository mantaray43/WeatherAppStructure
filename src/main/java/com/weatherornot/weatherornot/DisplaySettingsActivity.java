package com.weatherornot.weatherornot;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cheryl on 10/22/13.
 */
public class DisplaySettingsActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);


        //////setting background image as drawable
        AssetManager manager = getAssets();
        InputStream open = null;

        try{
            open = manager.open("chalkboardbackground.png");
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            ImageView view = (ImageView) findViewById(R.id.settingbackground);
            view.setImageBitmap(bitmap);
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(open!= null){
                try{
                    open.close();
                }
                catch (IOException e){

                }
            }
        }





    Button buttonToDisplayWeatherActivity = (Button)findViewById(R.id.donebutton);

    buttonToDisplayWeatherActivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i = new Intent(getApplicationContext(),DisplayWeatherActivity.class);
            onPostResume();
            finish();
        }
    }); {


        }
    }
    }

