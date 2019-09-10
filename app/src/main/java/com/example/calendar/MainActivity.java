package com.example.calendar;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calendar.Model.Day;
import com.example.calendar.Sqlite.DatabaseHandler;
import com.example.calendar.Utility.Utility;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler db;
    Button start;
    TextView today, tomorrow;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setStatusBarDesign();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setNextHolidays();
        }
    }

    public void init()
    {
        start = findViewById(R.id.startBtn);
        start.setOnClickListener(this);

        today = findViewById(R.id.todayLbl);
        tomorrow= findViewById(R.id.tomorrowLbl);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setNextHolidays()
    {
        String nextHoliday = Utility.getNextHoliday(getApplicationContext());
        if (nextHoliday != "")
        {
            tomorrow.setText(nextHoliday);
        }
        else
        {
            today.setText("");
            tomorrow.setText("");
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
        switch (v.getId()) {
            case R.id.startBtn:
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
