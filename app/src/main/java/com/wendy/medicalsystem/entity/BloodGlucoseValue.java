package com.wendy.medicalsystem.entity;


import cn.bmob.v3.BmobObject;


/**
 * Created by huacao on 2018/3/25.
 */

public class BloodGlucoseValue extends BmobObject {

    private User user;
    private String timeSelect;
    private String bGlucoseLevelValue;
    private int year ;
    private int mouth;
    private int day;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimeSelect() {
        return timeSelect;
    }

    public void setTimeSelect(String timeSelect) {
        this.timeSelect = timeSelect;
    }

    public String getBGlucoseLevelValue() {
        return bGlucoseLevelValue;
    }

    public void setBGlucoseLevelValue(String boldGlucoseLevelValue) {
        this.bGlucoseLevelValue = boldGlucoseLevelValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMouth() {
        return mouth;
    }

    public void setMouth(int mouth) {
        this.mouth = mouth;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
