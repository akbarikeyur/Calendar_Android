package com.example.calendar;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateLongClickListener;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, OnMonthChangedListener, OnDateLongClickListener {

    ImageButton backBtn;
    TextView currentHoliday;

    MaterialCalendarView calendar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        init();
        setStatusBarDesign();
    }

    public void init()
    {
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this);

        currentHoliday = findViewById(R.id.holiday);

        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangedListener(this);
        calendar.setOnDateLongClickListener(this);
        calendar.setOnMonthChangedListener(this);
        calendar.state().edit().setMinimumDate(CalendarDay.from(Integer.valueOf(2018), Integer.valueOf(01), Integer.valueOf(01))).setMaximumDate(CalendarDay.from(Integer.valueOf(2020), Integer.valueOf(12), Integer.valueOf(31))).commit();

        if (getIntent().getStringExtra("selectedDate") != null)
        {
            String selectedDate = getIntent().getStringExtra("selectedDate");
            SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");

            try {
                Date date = format.parse(selectedDate);
                String dayNumber = (String) DateFormat.format("d",   date); // 20
                String monthNumber = (String) DateFormat.format("M",   date); // 06
                String yearNumber = (String) DateFormat.format("yyyy", date); // 2013

                CalendarDay newDay = CalendarDay.from(Integer.valueOf(yearNumber), Integer.valueOf(monthNumber), Integer.valueOf(dayNumber));
                calendar.setDateSelected(newDay, true);
                calendar.setCurrentDate(newDay);
            } catch (ParseException e) {
                Log.e("Error", e.getLocalizedMessage());
                calendar.setDateSelected(calendar.getCurrentDate(), true);
            }
        }
        else
        {
            calendar.setDateSelected(calendar.getCurrentDate(), true);
        }
    }

    public void setStatusBarDesign()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void onDateLongClick(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay) {

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

    }

    @Override
    public void onMonthChanged(MaterialCalendarView materialCalendarView, CalendarDay calendarDay) {

    }
}