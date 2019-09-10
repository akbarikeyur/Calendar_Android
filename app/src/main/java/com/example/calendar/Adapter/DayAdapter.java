package com.example.calendar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calendar.CalendarActivity;
import com.example.calendar.HolidayListActivity;
import com.example.calendar.Model.Day;
import com.example.calendar.Model.HolidayDataModel;
import com.example.calendar.Model.HolidayModel;
import com.example.calendar.R;
import com.example.calendar.Tab.CurrentTab;

import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.MyViewHolder> {

    public ArrayList<HolidayDataModel> holiday_list = new ArrayList<>();
    private Fragment currentTab;
    private Context context;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView strDay, strDesc;

        public MyViewHolder(View view) {
            super(view);
            strDay = view.findViewById(R.id.day);
            strDesc = view.findViewById(R.id.desc);
        }
    }


    public DayAdapter(Fragment currentTab, Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.day_layout_cell, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final HolidayDataModel day= holiday_list.get(position);
        holder.strDay.setText(day.strDay + " - " + day.strDate);
        holder.strDesc.setText(day.strDesc);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CalendarActivity.class);
                intent.putExtra("selectedDate", day.strDate);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return holiday_list.size();
    }

    public void AddAll(ArrayList<HolidayDataModel> contact_list) {
        try {

            this.holiday_list.clear();
            this.holiday_list.addAll(contact_list);
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
