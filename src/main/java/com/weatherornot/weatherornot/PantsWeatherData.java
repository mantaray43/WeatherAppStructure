package com.weatherornot.weatherornot;

/**
* Created by cheryl on 9/3/13.
*/
public class PantsWeatherData {


    private Double mtime;
    private Double mCurrentTemp;
    private Double[] mHourly;



    public String getmCurrentTempString(){
        return mCurrentTemp.toString();
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

    public void setmCurrentTemp(Double mCurrentTemp) {
        this.mCurrentTemp = mCurrentTemp;
    }

    public Double[] getmHourly() {
        return mHourly;
    }

    public void setmHourly(Double[] mHourly) {
        this.mHourly = mHourly;
    }



}
