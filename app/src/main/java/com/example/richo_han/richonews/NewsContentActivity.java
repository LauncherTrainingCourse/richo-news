package com.example.richo_han.richonews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class NewsContentActivity extends AppCompatActivity {
    public final static String EXTRA_NEWS = "com.example.richo_han.richonews.EXTRA_NEWS";
    public final static String EXTRA_NEWS_INDEX = "com.example.richo_han.richonews.EXTRA_NEWS_INDEX";
    public final static String EXTRA_NEWS_RATING = "com.example.richo_han.richonews.EXTRA_NEWS_RATING";

    News mNews;
    int mIndex;
    int mRating = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Intent intent = getIntent();
        mNews = intent.getParcelableExtra(EXTRA_NEWS);
        mIndex = intent.getIntExtra(EXTRA_NEWS_INDEX, 0);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_news_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent();
                intent.putExtra(EXTRA_NEWS_INDEX, mIndex);
                intent.putExtra(EXTRA_NEWS_RATING, mRating);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            case R.id.action_browser:
                Uri webPage = Uri.parse(mNews.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);
                startActivity(webIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
