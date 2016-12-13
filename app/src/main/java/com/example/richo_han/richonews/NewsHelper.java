package com.example.richo_han.richonews;

import android.util.Log;

import com.loopj.android.http.*;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Richo_Han on 2016/12/13.
 */

public class NewsHelper {

    public void loadNews() {
        Log.d("Tag", "Loading...");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://www.google.com", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("Tag", "Loading succeeded! Status code: " + statusCode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
}
