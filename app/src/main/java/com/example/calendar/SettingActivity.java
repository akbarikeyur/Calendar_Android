package com.example.calendar;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton backBtn;
    RelativeLayout inAppView, privacyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        setStatusBarDesign();
    }

    public void init()
    {
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(this);
        inAppView = findViewById(R.id.inAppView);
        inAppView.setOnClickListener(this);
        privacyView = findViewById(R.id.privacyView);
        privacyView.setOnClickListener(this);

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
            case R.id.inAppView:
                clickOnInAppProduct();
                break;

            case R.id.privacyView:
                clickToPrivacyView();
                break;

            case R.id.back:
                finish();
                break;
        }
    }

    public void clickOnInAppProduct(){

    }

    public void clickToPrivacyView(){

    }


}
