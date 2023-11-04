package com.example.devdays2k23.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.devdays2k23.Api;
import com.example.devdays2k23.MainActivity;
import com.example.devdays2k23.MyRetro;
import com.example.devdays2k23.R;
import com.example.devdays2k23.adapter.MyAdapter;
import com.example.devdays2k23.struct.ArticleModel;
import com.example.devdays2k23.struct.ResponseModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceFragment extends Fragment {
    RecyclerView recyclerView;
    List<ArticleModel> articleList = new ArrayList<>();
    MyAdapter adapter;
    LinearProgressIndicator progressIndicator;

    private static String apiKey="d66c7c2d16ad4387bb2c8ad0ebb194f3";
    private static ScienceFragment instance;

    public static ScienceFragment getInstance() {
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_general, container, false);
        recyclerView = root.findViewById(R.id.news_recycler_view);
        progressIndicator = root.findViewById(R.id.progress_bar);
        instance=this;
        setupRecyclerView();
        getNews("science", null, "en");
        return root;


    }

    void setupRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyAdapter(articleList);
        recyclerView.setAdapter(adapter);
    }

    void changeInProgress(boolean show){
        if(show)
            progressIndicator.setVisibility(View.VISIBLE);
        else
            progressIndicator.setVisibility(View.INVISIBLE);
    }

    public void getNews(String category, String q, String language) {
        final Api api= MyRetro.getRetro().create(Api.class);
        Call<ResponseModel> call = api.getLatest(language,category,q,apiKey);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                List<ArticleModel> articleList1 = response.body().getArticles();
                if (articleList1.size()>0) {
                    adapter.updateData(articleList1);
                    adapter.notifyDataSetChanged();
                    changeInProgress(false);
                }
                else{
                    Toast.makeText(getContext(), "No data", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(getContext(), "No data Available", Toast.LENGTH_SHORT).show();

            }

        });

    }
    public void getQuery(String query, String category){
        getNews(category, query, "en");
    }
}