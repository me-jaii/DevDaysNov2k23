package com.example.devdays2k23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    ImageView poster;
    TextView title, author, dateTime, content, readmore;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Bundle bundle= getIntent().getExtras();
        String url2P= bundle.getString("urlToPhoto");
        String title1= bundle.getString("title");
        String author1=bundle.getString("author");
        String content1=bundle.getString("content");
        String DT=bundle.getString("dateTime");
        String url=bundle.getString("url");
        poster= findViewById(R.id.img);
        title= findViewById(R.id.title);
        author= findViewById(R.id.author);
        dateTime= findViewById(R.id.pd);
        content= findViewById(R.id.content);
        back=findViewById(R.id.back);
        readmore=findViewById(R.id.readmore);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Picasso.get().load(url2P)
                .error(R.drawable.no_picture)
                .placeholder(R.drawable.no_picture)
                .into(poster);
        title.setText(title1);
        author.setText("By "+author1);
        dateTime.setText(DT);
        content.setText(content1);
        readmore.setText(url);
        readmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewsDetailsActivity.this, NewsActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });





    }
}