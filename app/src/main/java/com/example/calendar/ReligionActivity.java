package com.example.calendar;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.calendar.Utility.Utility;

public class ReligionActivity extends AppCompatActivity implements View.OnClickListener {

    Button buddhistBtn, christianBtn, hinduBtn, islamBtn, jewishBtn, sikhBtn;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_religion);
        init();
        setStatusBarDesign();
    }

    public void init()
    {
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        buddhistBtn = findViewById(R.id.buddhist);
        buddhistBtn.setOnClickListener(this);
        christianBtn = findViewById(R.id.christian);
        christianBtn.setOnClickListener(this);
        hinduBtn = findViewById(R.id.hindu);
        hinduBtn.setOnClickListener(this);
        islamBtn = findViewById(R.id.islam);
        islamBtn.setOnClickListener(this);
        jewishBtn = findViewById(R.id.jewish);
        jewishBtn.setOnClickListener(this);
        sikhBtn = findViewById(R.id.sikh);
        sikhBtn.setOnClickListener(this);
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
        switch (v.getId()){
            case R.id.buddhist:
                redirectToHolidays(1);
                break;
            case R.id.christian:
                redirectToHolidays(2);
                break;
            case R.id.hindu:
                redirectToHolidays(3);
                break;
            case R.id.islam:
                redirectToHolidays(4);
                break;
            case R.id.jewish:
                redirectToHolidays(5);
                break;
            case R.id.sikh:
                redirectToHolidays(6);
                break;
            case R.id.back:
                finish();
                break;

        }
    }


    public void redirectToHolidays(Integer type)
    {
        Utility.religion_type = type;
        Intent intent = new Intent(getApplicationContext(), HolidayListActivity.class);
        startActivity(intent);
    }
}
