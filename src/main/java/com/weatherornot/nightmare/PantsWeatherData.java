package com.weatherornot.nightmare;

import android.location.Location;

/**
 * Created by cheryl on 9/3/13.
 */
public class PantsWeatherData {


    private Double mtime;
    public Double mCurrentTemp;
    private Double[] mHourly;
    private String[] mHourlyData;

    public Location myGeoLocation;
    public static int theIcon;

    public static int getTheIcon() {
        return theIcon;
    }


    public PantsWeatherData() {

    }


    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String icon;


    public String getGiveDate() {
        return giveDate;
    }

    public void setGiveDate(String giveDate) {
        this.giveDate = giveDate;
    }

    private String giveDate;


    public String[] getmHourlyData() {
        return mHourlyData;
    }

    public void setmHourlyData(String[] mHourlyData) {
        this.mHourlyData = mHourlyData;
    }



    public Double getMtime() {
        return mtime;
    }


    public void setMtime(Double mtime) {
        this.mtime = mtime;
    }

    public Double getmCurrentTemp() {
        return mCurrentTemp;
    }

    public String getmCurrentTempString() {
        return mCurrentTemp.toString();
    }

    public void setmCurrentTemp(Double mCurrentTemp) {
        this.mCurrentTemp = mCurrentTemp;
    }

    public Double[] getmHourly() {
        return mHourly;
    }

    public void setmHourly(Double[] mHourly) {
        this.mHourly = mHourly;
    }


    public void setMyCityName(String name) {
    }
}
