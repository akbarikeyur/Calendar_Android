package com.example.calendar.Tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calendar.Adapter.DayAdapter;
import com.example.calendar.Model.HolidayModel;
import com.example.calendar.Model.ReligionModel;
import com.example.calendar.R;
import com.example.calendar.Utility.Utility;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class PreviousTab extends Fragment {

    RecyclerView previousRecycleView;
    public DayAdapter dayAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_previous,container,false);

        previousRecycleView = view.findViewById(R.id.previousRecycle);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        previousRecycleView.setLayoutManager(mLayoutManager);
        previousRecycleView.setItemAnimator(new DefaultItemAnimator());
        previousRecycleView.setAdapter(dayAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dayAdapter = new DayAdapter(PreviousTab.this, getContext());
        prepareHolidayData();

    }

    private void prepareHolidayData() {

        String jsonString = (String) Utility.getJsonStringForHoliday(getContext());
        if (jsonString != "")
        {
            if (Utility.holiday_type == 4) {
                ReligionModel religion_model = new Gson().fromJson(jsonString, ReligionModel.class);
                dayAdapter.AddAll(Utility.getReligionHolidayData(religion_model,1));
            }
            else {
                HolidayModel holiday_model = new Gson().fromJson(jsonString, HolidayModel.class);
                dayAdapter.AddAll(holiday_model.holidayPrevious);
            }
        }
    }
}
