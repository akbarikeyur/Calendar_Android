package com.example.calendar.Tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.calendar.Adapter.DayAdapter;
import com.example.calendar.Model.Day;
import com.example.calendar.Model.HolidayModel;
import com.example.calendar.Model.ReligionModel;
import com.example.calendar.R;
import com.example.calendar.Sqlite.DatabaseHandler;
import com.example.calendar.Utility.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CurrentTab extends Fragment {

    RecyclerView currentRecycleView;
    public DayAdapter dayAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_current,container,false);

        currentRecycleView = view.findViewById(R.id.currentRecycle);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        currentRecycleView.setLayoutManager(mLayoutManager);
        currentRecycleView.setItemAnimator(new DefaultItemAnimator());
        currentRecycleView.setAdapter(dayAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dayAdapter = new DayAdapter(CurrentTab.this, getContext());
        prepareHolidayData();
    }

    private void prepareHolidayData() {

        String jsonString = (String) Utility.getJsonStringForHoliday(getContext());
        if (jsonString != "")
        {
            if (Utility.holiday_type == 4) {
                ReligionModel religion_model = new Gson().fromJson(jsonString, ReligionModel.class);
                dayAdapter.AddAll(Utility.getReligionHolidayData(religion_model,2));
//                if (Utility.religion_type == 1) {
//                    dayAdapter.AddAll(religion_model.BuddhistModel.holidayCurrent);
//                }
//                else if (Utility.religion_type == 2){
//                    dayAdapter.AddAll(religion_model.ChristianModel.holidayCurrent);
//                }
//                else if (Utility.religion_type == 3){
//                    dayAdapter.AddAll(religion_model.HinduModel.holidayCurrent);
//                }
//                else if (Utility.religion_type == 4){
//                    dayAdapter.AddAll(religion_model.IslamModel.holidayCurrent);
//                }
//                else if (Utility.religion_type == 5){
//                    dayAdapter.AddAll(religion_model.JewishModel.holidayCurrent);
//                }
//                else if (Utility.religion_type == 6){
//                    dayAdapter.AddAll(religion_model.SikhModel.holidayCurrent);
//                }
            }
            else {
                HolidayModel holiday_model = new Gson().fromJson(jsonString, HolidayModel.class);
                dayAdapter.AddAll(holiday_model.holidayCurrent);
            }
        }
    }
}
