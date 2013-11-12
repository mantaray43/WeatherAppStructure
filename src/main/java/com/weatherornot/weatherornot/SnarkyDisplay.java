package com.weatherornot.weatherornot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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



    int hot;
    int cold;
    int perfect;


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


    if (mCTemp > cold){    //bittercold
        range = "bittercold";

    }   else if ((mCTemp >= cold) && (mCTemp <= (cold + 10))){     //toocold
        range = "toocold";

    }   else if ((mCTemp > (cold +10) && (mCTemp <= (cold + 17)))){      //good
        range = "good";

    }   else if ((mCTemp > (cold +10) && (mCTemp <= (perfect + 10)))){   //perfect
        range = "perfect";

    }   else if ((mCTemp > (perfect + 10) && (mCTemp <= (hot - 5)))){   //warm
        range = "warm";

    }   else if (mCTemp > (hot - 5) && (mCTemp <= (hot+3))) { //too hot
        range = "toohot";

    }   else if (mCTemp > (hot + 4)){
        range = "toodanghot";

    }   else{
        range = "toodanghot";

    }

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


