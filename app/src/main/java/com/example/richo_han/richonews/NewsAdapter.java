package com.example.richo_han.richonews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Richo_Han on 2016/12/13.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_news, parent, false);
        }

        Uri uri = Uri.parse(news.getUrlToImage());
        SimpleDraweeView imageView = (SimpleDraweeView) convertView.findViewById(R.id.thumbnail);
        imageView.setImageURI(uri);

        ((TextView) convertView.findViewById(R.id.tvTitle)).setText(news.getTitle());
        ((TextView) convertView.findViewById(R.id.tvAuthor)).setText(news.getAuthor());
        String date = news.getPublishedAt().split("T")[0];
        ((TextView) convertView.findViewById(R.id.tvDate)).setText(date);

        return convertView;
    }

}
