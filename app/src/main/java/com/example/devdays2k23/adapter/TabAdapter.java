package com.example.devdays2k23.adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.devdays2k23.Fragment.BusinessFragment;
import com.example.devdays2k23.Fragment.EntertainmentFragment;
import com.example.devdays2k23.Fragment.GeneralFragment;
import com.example.devdays2k23.Fragment.HealthFragment;
import com.example.devdays2k23.Fragment.ScienceFragment;
import com.example.devdays2k23.Fragment.SportsFragment;
import com.example.devdays2k23.Fragment.TechnologyFragment;

public class TabAdapter  extends FragmentStatePagerAdapter {


    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new GeneralFragment();
            case 1:
                return new BusinessFragment();
            case 2:
                return new SportsFragment();
            case 3:
                return new TechnologyFragment();
            case 4:
                return new HealthFragment();
            case 5:
                return new EntertainmentFragment();
            case 6:
                return new ScienceFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 7;
    }
}
