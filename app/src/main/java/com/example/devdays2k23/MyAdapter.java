package com.example.devdays2k23;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;
import android.text.format.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.NewsViewHolder> {

    List<Article> articleList;
    MyAdapter(List<Article> articleList){
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Article article = articleList.get(position);
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

        holder.itemView.setOnClickListener((v -> {
            Intent intent = new Intent(v.getContext(),NewsActivity.class);
            intent.putExtra("url",article.getUrl());
            v.getContext().startActivity(intent);
        }));

    }

    void updateData(List<Article> data){
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
