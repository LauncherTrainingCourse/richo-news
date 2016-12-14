package com.example.richo_han.richonews;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int RATING_REQUEST = 1;
    NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<News> arrayOfUsers = new ArrayList<>();
        adapter = new NewsAdapter(this, arrayOfUsers);

        AsyncLoadNewsTask task = new AsyncLoadNewsTask();
        task.execute();

        ListView listView = (ListView) findViewById(R.id.news_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news = (News) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(view.getContext(), NewsContentActivity.class);
                intent.putExtra(NewsContentActivity.EXTRA_NEWS_INDEX, i);
                intent.putExtra(NewsContentActivity.EXTRA_NEWS, news);
                startActivityForResult(intent, RATING_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RATING_REQUEST) {
            if(resultCode == RESULT_OK) {
                ListView listView = (ListView) findViewById(R.id.news_list);
                int index = data.getIntExtra(NewsContentActivity.EXTRA_NEWS_INDEX, 0);
                float rating = data.getFloatExtra(NewsContentActivity.EXTRA_NEWS_RATING, 0);
                View view = listView.getChildAt(index);
                ((RatingBar) view.findViewById(R.id.ratingBar)).setRating(rating);
            }
        }
    }

    private class AsyncLoadNewsTask extends AsyncTask<Void, Void, Void> {
        News[] list = {};

        @Override
        protected Void doInBackground(Void... voids) {
            NewsHelper helper = new NewsHelper();
            list = helper.loadNews();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        for(News news : list) {
                            adapter.add(news);
                        }
                    }
                }
            );
        }
    }
}
