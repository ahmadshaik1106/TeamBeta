package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ViewPager vp;
    TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = findViewById(R.id.pager);
        vp.setAdapter(new MyAdapter(getSupportFragmentManager()));

        tl=findViewById(R.id.tab);
        tl.setupWithViewPager(vp);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.id_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.id_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);

                        return true;
                    case R.id.id_search:
                        startActivity(new Intent(getApplicationContext(),SearchActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.id_account:
                        startActivity(new Intent(getApplicationContext(),AccountActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }

                return false;
            }
        });


    }


    class MyAdapter extends FragmentStatePagerAdapter {

        private String[] tabTitles = new String[]{"All","Business","Entertainment","Health","Science","Sports","Technology"};

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new AllNewsFragment();
                case 1:return new BusinessFragment();
                case 2:return new EntertainmentFragment();
                case 3:return new HealthFragment();
                case 4:return new ScienceFragment();
                case 5:return new SportsFragment();
                case 6:return new TechnologyFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//
//                    return "Chats";
//                case 1:
//
//                    return "status";
//                case 2:
//
//                    return "Calls";
//            }
//            return super.getPageTitle(position);
            return tabTitles[position];
        }
    }

}
