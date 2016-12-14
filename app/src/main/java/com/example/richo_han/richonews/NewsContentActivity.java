package com.example.richo_han.richonews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsContentActivity extends AppCompatActivity {
    public final static String EXTRA_NEWS = "com.example.richo_han.richonews.EXTRA_NEWS";
    News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Intent intent = getIntent();
        mNews = intent.getParcelableExtra(EXTRA_NEWS);

        ((TextView) findViewById(R.id.news_title)).setText(mNews.getTitle());
        String meta = mNews.getAuthor() + " / " + mNews.getPublishedAt().split("T")[0];
        ((TextView) findViewById(R.id.news_meta)).setText(meta);
        ((TextView) findViewById(R.id.news_description)).setText(mNews.getDescription());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
