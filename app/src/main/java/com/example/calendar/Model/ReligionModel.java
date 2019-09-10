package com.example.calendar.Model;

import com.google.gson.annotations.SerializedName;

public class ReligionModel {
    @SerializedName("Buddhist")
    public HolidayModel BuddhistModel;

    @SerializedName("Christian")
    public HolidayModel ChristianModel;

    @SerializedName("Hindu")
    public HolidayModel HinduModel;

    @SerializedName("Islam")
    public HolidayModel IslamModel;

    @SerializedName("Jewish")
    public HolidayModel JewishModel;

    @SerializedName("Sikh")
    public HolidayModel SikhModel;

}
