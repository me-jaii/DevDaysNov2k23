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

//    RecyclerView recyclerView;
//    List<ArticleModel> articleList = new ArrayList<>();
//    MyAdapter adapter;
//    LinearProgressIndicator progressIndicator;
//    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7;
//    private static String apiKey="d66c7c2d16ad4387bb2c8ad0ebb194f3";
    SearchView searchView;
    TabLayout tabLayout;
    ViewPager viewPager;
    TabAdapter adapter;
    int tabPosition;
    Dictionary<Integer,String > dict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        recyclerView = findViewById(R.id.news_recycler_view);
//        progressIndicator = findViewById(R.id.progress_bar);
        searchView = findViewById(R.id.search_view);
//        btn1 = findViewById(R.id.btn_1);
//        btn2 = findViewById(R.id.btn_2);
//        btn3 = findViewById(R.id.btn_3);
//        btn4 = findViewById(R.id.btn_4);
//        btn5 = findViewById(R.id.btn_5);
//        btn6 = findViewById(R.id.btn_6);
//        btn7 = findViewById(R.id.btn_7);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
//        btn3.setOnClickListener(this);
//        btn4.setOnClickListener(this);
//        btn5.setOnClickListener(this);
//        btn6.setOnClickListener(this);
//        btn7.setOnClickListener(this);
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
//        setupRecyclerView();
//        getNews("GENERAL",null,"en");
//    void setupRecyclerView(){
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new MyAdapter(articleList);
//        recyclerView.setAdapter(adapter);
//    }

//    void changeInProgress(boolean show){
//        if(show)
//            progressIndicator.setVisibility(View.VISIBLE);
//        else
//            progressIndicator.setVisibility(View.INVISIBLE);
//    }
//

//    void getNews(String category,String query){
//        changeInProgress(true);
//        NewsApiClient newsApiClient = new NewsApiClient("d66c7c2d16ad4387bb2c8ad0ebb194f3");
//        newsApiClient.getTopHeadlines(
//                new TopHeadlinesRequest.Builder()
//                        .language("en")
//                        .category(category)
//                        .q(query)
//                        .build(),
//                new NewsApiClient.ArticlesResponseCallback() {
//                    @Override
//                    public void onSuccess(ArticleResponse response) {
//
//                        runOnUiThread(()->{
//                            changeInProgress(false);
//                            articleList = response.getArticles();
//                            adapter.updateData(articleList);
//                            adapter.notifyDataSetChanged();
//                        });
//
//                    }
//
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        Log.i("GOT Failure",throwable.getMessage());
//                    }
//                }
//        );
//    }

//    public void getNews(String category, String q, String language) {
//        final Api api= MyRetro.getRetro().create(Api.class);
//        Call<ResponseModel> call = api.getLatest(language,category,q,apiKey);
//        call.enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                List<ArticleModel> articleList1 = response.body().getArticles();
//                if (articleList1.size()>0) {
//                    adapter.updateData(articleList1);
//                    adapter.notifyDataSetChanged();
//                    changeInProgress(false);
//                }
//                else{
//                    Toast.makeText(MainActivity.this, "No data", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "No data Available", Toast.LENGTH_SHORT).show();
//
//            }

//        });
//        MyRetro.getApi().getLatest(category,q,language,apiKey).enqueue(new Callback<ResponseModel>() {
//            @Override
//            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
//                List<ArticleModel> articleList1 = response.body().getArticles();
//                final MyAdapter adapter = new MyAdapter(articleList1);
//                articleList.addAll(articleList1);
//                MyAdapter adapter= new MyAdapter(articleList1);
//                recyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseModel> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "No data Available", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }

//    @Override
//    public void onClick(View view) {
//        Button btn = (Button) view;
//        String category = btn.getText().toString();
//        getNews(category,null,"en");
//    }
}

