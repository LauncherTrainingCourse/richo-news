package com.example.richo_han.richonews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class NewsContentActivity extends Activity {
    public final static String EXTRA_NEWS = "com.example.richo_han.richonews.EXTRA_NEWS";
    News mNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        Intent intent = getIntent();
        mNews = intent.getParcelableExtra(EXTRA_NEWS);
    }
}
