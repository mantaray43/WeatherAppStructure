package com.weatherornot.weatherornot;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by cheryl on 11/5/13.
 */
public class SnarkyDisplay {
}

//using this class to work out code.  May be deleted.

//to display the snarky text and image based on the current temperature
//steps needed

//get shared prefs data and convert the strings to integers (may do that in the shared prefs section in DisplaySettings
getSharedPreferences(PREFERENCES,0);


SharedPreferences sharedPref = getActivity().getPreferences(Context,0)
        int hotValue = getResources()


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


