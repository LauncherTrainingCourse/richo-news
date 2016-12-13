package com.example.richo_han.richonews;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Richo_Han on 2016/12/13.
 */

public class NewsHelper {
    OkHttpClient client = new OkHttpClient();

    String run() throws IOException {
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("newsapi.org")
                .addPathSegment("/v1/articles")
                .addQueryParameter("source", "techcrunch")
                .addQueryParameter("apiKey", "63b4ee01b2df4c5f8d36e91e8e3e5c8f")
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public News[] loadNews() {
        News[] list = {};
        try {
            String response = this.run();

            try {
                JSONObject object = new JSONObject(response);
                JSONArray array = object.getJSONArray("articles");
                Gson gson = new Gson();
                News[] newsList = gson.fromJson(array.toString(), News[].class);
                for (News news : newsList) {
                    System.out.println(news.author);
                }
                list = newsList;
            } catch (JSONException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
