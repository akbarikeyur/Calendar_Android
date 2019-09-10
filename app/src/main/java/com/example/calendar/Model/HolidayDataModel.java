package com.example.calendar.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HolidayDataModel {

    @SerializedName("date")
    public String strDate;

    @SerializedName("day")
    public String strDay;

    @SerializedName("desc")
    public String strDesc;

}
