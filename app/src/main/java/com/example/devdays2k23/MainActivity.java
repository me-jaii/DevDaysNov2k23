package com.example.devdays2k23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.devdays2k23.Fragment.BusinessFragment;
import com.example.devdays2k23.Fragment.EntertainmentFragment;
import com.example.devdays2k23.Fragment.GeneralFragment;
import com.example.devdays2k23.Fragment.HealthFragment;
import com.example.devdays2k23.Fragment.ScienceFragment;
import com.example.devdays2k23.Fragment.SportsFragment;
import com.example.devdays2k23.Fragment.TechnologyFragment;
import com.example.devdays2k23.adapter.MyAdapter;
import com.example.devdays2k23.adapter.TabAdapter;
import com.example.devdays2k23.struct.ArticleModel;
import com.example.devdays2k23.struct.ResponseModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    SearchView searchView;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter adapter;
    int tabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search_view);
        tabLayout = (TabLayout) findViewById(R.id.tab);
        viewPager = (ViewPager) findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("General"));
        tabLayout.addTab(tabLayout.newTab().setText("Business"));
        tabLayout.addTab(tabLayout.newTab().setText("Sports"));
        tabLayout.addTab(tabLayout.newTab().setText("Technology"));
        tabLayout.addTab(tabLayout.newTab().setText("Health"));
        tabLayout.addTab(tabLayout.newTab().setText("Entertainment"));
        tabLayout.addTab(tabLayout.newTab().setText("Science"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabPosition = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                switch (tabPosition) {
                    case 0:
                        GeneralFragment fragment=GeneralFragment.getInstance();
                        fragment.getQuery(query,"general");
                        break;
                    case 1:
                        BusinessFragment businessFragment= BusinessFragment.getInstance();
                        businessFragment.getQuery(query,"business");
                        break;
                    case 2:
                        SportsFragment sportsFragment= SportsFragment.getInstance();
                        sportsFragment.getQuery(query,"sports");
                        break;
                    case 3:
                        TechnologyFragment technologyFragment= TechnologyFragment.getInstance();
                        technologyFragment.getQuery(query,"technology");
                        break;
                    case 4:
                        HealthFragment healthFragment= HealthFragment.getInstance();
                        healthFragment.getQuery(query,"health");
                        break;
                    case 5:
                        EntertainmentFragment entertainmentFragment= EntertainmentFragment.getInstance();
                        entertainmentFragment.getQuery(query,"entertainment");
                        break;
                    case 6:
                        ScienceFragment scienceFragment= ScienceFragment.getInstance();
                        scienceFragment.getQuery(query,"science");
                        break;
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
}

