package com.example.richo_han.richonews;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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
                Intent intent = new Intent(view.getContext(), NewsContentActivity.class);
                startActivity(intent);
            }
        });
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
