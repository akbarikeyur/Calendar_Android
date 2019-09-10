package com.example.calendar.Model;

        import com.google.gson.annotations.SerializedName;

        import java.util.ArrayList;

public class HolidayModel {

    @SerializedName("2018")
    public ArrayList<HolidayDataModel> holidayPrevious = new ArrayList<>();

    @SerializedName("2019")
    public ArrayList<HolidayDataModel> holidayCurrent = new ArrayList<>();

    @SerializedName("2020")
    public ArrayList<HolidayDataModel> holidayNext = new ArrayList<>();

}
