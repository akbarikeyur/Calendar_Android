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

public class NextTab extends Fragment {

    RecyclerView nextRecycleView;
    public DayAdapter dayAdapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab_next,container,false);

        nextRecycleView = view.findViewById(R.id.nextRecycle);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        nextRecycleView.setLayoutManager(mLayoutManager);
        nextRecycleView.setItemAnimator(new DefaultItemAnimator());
        nextRecycleView.setAdapter(dayAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dayAdapter = new DayAdapter(NextTab.this, getContext());
        prepareHolidayData();

    }

    private void prepareHolidayData() {

        String jsonString = (String) Utility.getJsonStringForHoliday(getContext());
        if (jsonString != "")
        {
            if (Utility.holiday_type == 4) {
                ReligionModel religion_model = new Gson().fromJson(jsonString, ReligionModel.class);
                dayAdapter.AddAll(Utility.getReligionHolidayData(religion_model,3));
            }
            else {
                HolidayModel holiday_model = new Gson().fromJson(jsonString, HolidayModel.class);
                dayAdapter.AddAll(holiday_model.holidayNext);
            }
        }
    }
}
