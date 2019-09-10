package com.example.calendar;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.calendar.Tab.CurrentTab;
import com.example.calendar.Utility.Utility;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton backBtn;
    Button calendarBtn, bankBtn, stockBtn, publicBtn, religionBtn, settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        setStatusBarDesign();
    }

    public void init()
    {
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        calendarBtn = findViewById(R.id.calBtn);
        calendarBtn.setOnClickListener(this);
        bankBtn = findViewById(R.id.bankH);
        bankBtn.setOnClickListener(this);
        stockBtn = findViewById(R.id.stockH);
        stockBtn.setOnClickListener(this);
        publicBtn = findViewById(R.id.publicH);
        publicBtn.setOnClickListener(this);
        religionBtn = findViewById(R.id.religionH);
        religionBtn.setOnClickListener(this);
        settingBtn = findViewById(R.id.setting);
        settingBtn.setOnClickListener(this);
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
            case R.id.calBtn:
                redirectToCalendar();
                break;
            case R.id.bankH:
                redirectToHolidayListView(1);
                break;
            case R.id.stockH:
                redirectToHolidayListView(2);
                break;
            case R.id.publicH:
                redirectToHolidayListView(3);
                break;
            case R.id.religionH:
                redirectToReligionView();
                break;
            case R.id.setting:
                redirectToAppSetting();
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    public  void redirectToCalendar()
    {
        Intent intent = new Intent(getApplicationContext(), CalendarActivity.class);
        startActivity(intent);
    }

    public  void redirectToAppSetting()
    {
        Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(intent);
    }

    public void redirectToHolidayListView(int type)
    {
        Utility.holiday_type = type;
        Intent intent = new Intent(getApplicationContext(), HolidayListActivity.class);
        startActivity(intent);
    }

    public void redirectToReligionView()
    {
        Utility.holiday_type = 4;
        Intent intent = new Intent(getApplicationContext(), ReligionActivity.class);
        startActivity(intent);
    }

}
