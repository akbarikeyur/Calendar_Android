package com.example.calendar;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.calendar.Tab.CurrentTab;
import com.example.calendar.Tab.NextTab;
import com.example.calendar.Tab.PreviousTab;
import com.example.calendar.Utility.Utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HolidayListActivity extends AppCompatActivity{

    ImageButton backBtn;
    TextView titleLbl;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_list);
        init();
        setStatusBarDesign();
    }

    public void init()
    {
        backBtn = findViewById(R.id.back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // Do something
                finish();
            }
        });

        titleLbl = findViewById(R.id.title);
        switch (Utility.holiday_type){
            case 1:
                titleLbl.setText(getResources().getString(R.string.menu_bank));
                break;
            case 2:
                titleLbl.setText(getResources().getString(R.string.menu_stock));
                break;
            case 3:
                titleLbl.setText(getResources().getString(R.string.menu_public));
                break;
            case 4:
                switch (Utility.religion_type){
                    case 1:
                        titleLbl.setText(getResources().getString(R.string.buddhist_title));
                        break;
                    case 2:
                        titleLbl.setText(getResources().getString(R.string.christian_title));
                        break;
                    case 3:
                        titleLbl.setText(getResources().getString(R.string.hindu_title));
                        break;
                    case 4:
                        titleLbl.setText(getResources().getString(R.string.islam_title));
                        break;
                    case 5:
                        titleLbl.setText(getResources().getString(R.string.jewish_title));
                        break;
                    case 6:
                        titleLbl.setText(getResources().getString(R.string.sikh_title));
                        break;
                }
                break;
        }

        setupTabBar();
    }

    public void setStatusBarDesign()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.statusBar));
        }
    }

    public void setupTabBar()
    {
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(1);
    }

    private void setupViewPager(ViewPager viewPager) {

        Integer year = Calendar.getInstance().get(Calendar.YEAR);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PreviousTab(), ((Integer) (year-1)).toString());
        adapter.addFragment(new CurrentTab(), ((Integer) year).toString());
        adapter.addFragment(new NextTab(), ((Integer) (year+1)).toString());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
