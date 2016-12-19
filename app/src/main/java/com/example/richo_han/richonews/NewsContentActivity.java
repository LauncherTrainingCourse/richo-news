package com.example.richo_han.richonews;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NewsContentActivity extends AppCompatActivity implements RatingDialogFragment.RatingDialogListener{
    public final static String EXTRA_NEWS = "com.example.richo_han.richonews.EXTRA_NEWS";
    public final static String EXTRA_NEWS_INDEX = "com.example.richo_han.richonews.EXTRA_NEWS_INDEX";
    public final static String EXTRA_NEWS_RATING = "com.example.richo_han.richonews.EXTRA_NEWS_RATING";

    News mNews;
    int mIndex;

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

        Uri uri = Uri.parse(mNews.getUrlToImage());
        SimpleDraweeView imageView = (SimpleDraweeView) findViewById(R.id.news_image);
        imageView.setImageURI(uri);

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
                finish();
                return true;
            case R.id.action_rating:
                RatingDialogFragment dialog = new RatingDialogFragment();
                dialog.show(getSupportFragmentManager(), "RatingDialogFragment");
                return true;
            case R.id.action_browser:
                Uri webPage = Uri.parse(mNews.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webPage);

                // Check the number of apps that are able to deal with the intent.
                PackageManager packageManager = getPackageManager();
                List activities = packageManager.queryIntentActivities(webIntent,
                        PackageManager.MATCH_DEFAULT_ONLY);
                boolean isIntentSafe = activities.size() > 0;
                showNumberOfActivities(activities.size());

                if(isIntentSafe) {
                    startActivity(webIntent);
                }
                return true;
            case R.id.action_share:
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, mNews.getTitle());
                shareIntent.putExtra(Intent.EXTRA_TEXT, mNews.getUrl());

                String title = getResources().getString(R.string.sharing_title);

                if(shareIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(Intent.createChooser(shareIntent, title));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, float rating) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_NEWS_INDEX, mIndex);
        intent.putExtra(EXTRA_NEWS_RATING, rating);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void showNumberOfActivities(int num) {
        Context context = getApplicationContext();
        CharSequence text = "Number of activities: " + num;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
