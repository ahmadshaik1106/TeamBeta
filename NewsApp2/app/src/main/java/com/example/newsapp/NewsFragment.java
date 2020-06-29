package com.example.newsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;


//
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link AccountFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class NewsFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public NewsFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AccountFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AccountFragment newInstance(String param1, String param2) {
//        AccountFragment fragment = new AccountFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_news, container, false);
//    }
//}


public class NewsFragment extends Fragment {
    private Toolbar toolbar;
    ViewPager vp;
    TabLayout tl;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        try {
            vp = (ViewPager)rootView.findViewById(R.id.pager);
            vp.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));

            tl=(TabLayout)rootView.findViewById(R.id.tab);
            tl.setupWithViewPager(vp);



        }catch (Exception e){
            Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return rootView;
    }


    class MyAdapter extends FragmentStatePagerAdapter{

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

           return tabTitles[position];
        }
    }


}



//=====================================










