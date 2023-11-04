package com.example.devdays2k23.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devdays2k23.NewsActivity;
import com.example.devdays2k23.NewsDetailsActivity;
import com.example.devdays2k23.R;
import com.example.devdays2k23.struct.ArticleModel;
import com.squareup.picasso.Picasso;

import java.util.List;
import android.text.format.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NewsViewHolder> {

    List<ArticleModel> articleList;
    public MyAdapter(List<ArticleModel> articleList){
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        ArticleModel article = articleList.get(position);
        if(article.getUrl().toString().equals("https://removed.com")){
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));        }
        else {
            holder.titleTextView.setText(article.getTitle());

            long currentTimeMillis = System.currentTimeMillis();
            long publishedTimeMillis = parseDateTime(article.getPublishedAt());
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(publishedTimeMillis, currentTimeMillis, DateUtils.MINUTE_IN_MILLIS);

            holder.publishedTextView.setText(timeAgo.toString());
            holder.sourceTextView.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage())
                    .error(R.drawable.no_picture)
                    .placeholder(R.drawable.no_picture)
                    .into(holder.imageView);
            holder.itemView.setVisibility(View.VISIBLE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        holder.itemView.setOnClickListener((v -> {
            Intent intent = new Intent(v.getContext(), NewsActivity.class);
            intent.putExtra("url",article.getUrl());
//            Bundle bundle = new Bundle();
//            bundle.putString("url", article.getUrl());
//            bundle.putString("urlToPhoto", article.getUrlToImage());
//            bundle.putString("title", article.getTitle());
//            bundle.putString("author", article.getAuthor());
//            bundle.putString("content", article.getDescription());
//            bundle.putString("dateTime", article.getPublishedAt());
//            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        }));

    }

    public void updateData(List<ArticleModel> data){
        articleList.clear();
        articleList.addAll(data);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextView,sourceTextView,publishedTextView;
        ImageView imageView;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.article_title);
            sourceTextView = itemView.findViewById(R.id.article_source);
            publishedTextView = itemView.findViewById(R.id.published);
            imageView = itemView.findViewById(R.id.article_image_view);
        }
    }

    private long parseDateTime(String dateTimeString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        try {
            Date date = sdf.parse(dateTimeString);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
