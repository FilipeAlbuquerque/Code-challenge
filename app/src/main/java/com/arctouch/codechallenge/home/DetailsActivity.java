package com.arctouch.codechallenge.home;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arctouch.codechallenge.R;
import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailsActivity";


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        Log.d(TAG, "onCreate: started ");

        getIntentIncoming();

    }

    private void getIntentIncoming() {

        if (getIntent().hasExtra("image_name") && getIntent().hasExtra("original_title")
                && getIntent().hasExtra("image_overview") && getIntent().hasExtra("release_date")) {

            Log.d(TAG, "getIntentIncoming: found intent extras");

            String imageName = getIntent().getStringExtra("image_name");
            String textTitle = getIntent().getStringExtra("original_title");
            String textOverview = getIntent().getStringExtra("overview");
            String textReleaseDate = getIntent().getStringExtra("release_date");

            //calling the method
            setImage(textTitle, imageName, textOverview, textReleaseDate);

        } else{
            Toast.makeText(this, "NO API Data", Toast.LENGTH_SHORT).show();
        }
    }

    private void setImage(String textTitle, String imageName, String textOverview,
                           String textReleaseDate) {

        TextView movieTitle = findViewById(R.id.text_title);
        movieTitle.setText(textTitle);

        TextView overView = findViewById(R.id.text_overview);
        overView.setText(textOverview);

        TextView releaseDate = findViewById(R.id.releaseDateTextView);
        releaseDate.setText(textReleaseDate);

        //setting the imageview
        ImageView image = findViewById(R.id.posterImageView2);
        Glide.with(this)
                .asBitmap()
                .load(imageName)
                .into(image);
    }
}
