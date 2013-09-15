package com.weatherornot.weatherornot;

/**
* Created by cheryl on 9/3/13.
*/
public class PantsWeatherData {


    private Double mtime;
    private Double mCurrentTemp;
    private Double[] mHourly;
    private String[] mHourlyData;

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
