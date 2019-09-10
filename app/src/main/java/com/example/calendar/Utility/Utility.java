package com.example.calendar.Utility;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.calendar.Model.HolidayDataModel;
import com.example.calendar.Model.HolidayModel;
import com.example.calendar.Model.ReligionModel;
import com.example.calendar.R;
import com.google.gson.Gson;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utility {
    static public Integer holiday_type = 0;
    static public Integer religion_type = 0;

    public static String getJsonStringForHoliday(Context context)
    {
        String jsonString = "";
        switch (holiday_type){
            case 1:
                jsonString = loadJSONFromAsset(context, "bank_holiday.json");
                break;
            case 2:
                jsonString = loadJSONFromAsset(context, "stock_holiday.json");
                break;
            case 3:
                jsonString = loadJSONFromAsset(context, "public_holiday.json");
                break;
            case 4:
                jsonString = loadJSONFromAsset(context, "religion_holiday.json");
                break;
        }
        return jsonString;
    }

    public static String loadJSONFromAsset(Context context, String file_name) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(file_name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static ArrayList<HolidayDataModel> getReligionHolidayData(ReligionModel religion_model, Integer tab)
    {
        Log.e("Religion Type",Utility.religion_type + "");

        if (religion_type == 0){
            religion_type = 2;
        }

        if (Utility.religion_type == 1) {
            switch (tab){
                case 1:
                    return religion_model.BuddhistModel.holidayPrevious;
                case 2:
                    return religion_model.BuddhistModel.holidayCurrent;
                case 3:
                    return religion_model.BuddhistModel.holidayNext;
            }
        }
        else if (Utility.religion_type == 2){
            switch (tab){
                case 1:
                    return religion_model.ChristianModel.holidayPrevious;
                case 2:
                    return religion_model.ChristianModel.holidayCurrent;
                case 3:
                    return religion_model.ChristianModel.holidayNext;
            }
        }
        else if (Utility.religion_type == 3){
            switch (tab) {
                case 1:
                    return religion_model.HinduModel.holidayPrevious;
                case 2:
                    return religion_model.HinduModel.holidayCurrent;
                case 3:
                    return religion_model.HinduModel.holidayNext;
            }
        }
        else if (Utility.religion_type == 4){
                switch (tab) {
                    case 1:
                        return religion_model.IslamModel.holidayPrevious;
                    case 2:
                        return religion_model.IslamModel.holidayCurrent;
                    case 3:
                        return religion_model.IslamModel.holidayNext;
                }
        }
        else if (Utility.religion_type == 5){
            switch (tab) {
                case 1:
                    return religion_model.JewishModel.holidayPrevious;
                case 2:
                    return religion_model.JewishModel.holidayCurrent;
                case 3:
                    return religion_model.JewishModel.holidayNext;
            }
        }
        else if (Utility.religion_type == 6){
            switch (tab) {
                case 1:
                    return religion_model.SikhModel.holidayPrevious;
                case 2:
                    return religion_model.SikhModel.holidayCurrent;
                case 3:
                    return religion_model.SikhModel.holidayNext;
            }
            return religion_model.SikhModel.holidayCurrent;
        }
        Log.e("Default Religion",Utility.religion_type + "");
        return religion_model.ChristianModel.holidayCurrent;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String getNextHoliday(Context context) {
        String jsonString = loadJSONFromAsset(context, "public_holiday.json");
        HolidayModel holiday_model = new Gson().fromJson(jsonString, HolidayModel.class);

        String nextHoliday = "";
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");

        try {
            String formattedDate = df.format(date);
            Date currentDate = df.parse(formattedDate);

            Integer getHoliday = 0;
            for (int i = 0; i<holiday_model.holidayCurrent.size(); i++)
            {
                Log.e("Next Holiday", holiday_model.holidayCurrent.get(i).strDesc);
                Date newDate = df.parse(holiday_model.holidayCurrent.get(i).strDate);
                if (newDate.compareTo(currentDate) > 0)
                {
                    if (getHoliday == 0)
                    {
                        nextHoliday = holiday_model.holidayCurrent.get(i).strDesc + " (" + holiday_model.holidayCurrent.get(i).strDate + ")";
                    }
                    else if (getHoliday == 1){
                        nextHoliday = nextHoliday + "\n" + holiday_model.holidayCurrent.get(i).strDesc + " (" + holiday_model.holidayCurrent.get(i).strDate + ")";
                        break;
                    }
                    getHoliday += 1;
                }
            }

        } catch (ParseException e) {
            Log.e("Error", e.getLocalizedMessage());
        }
        Log.e("Final Holiday", nextHoliday);
        return nextHoliday;
    }
}
