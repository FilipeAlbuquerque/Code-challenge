package com.arctouch.codechallenge.home;

/**
 * Created by filipe on 19/03/18.
 */

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.bumptech.glide.Glide;

/**
 * Created by filipe on 19/03/18.
 */

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailsActivity";

    ImageView posterImageView;
    TextView movieTitle, overview, genres, genresIds, releaseDateTextView;
    


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        Log.d(TAG, "onCreate: started ");

        getIntentIncoming();

        posterImageView.findViewById(R.id.posterImageView2);
        movieTitle.findViewById(R.id.text_title);
        overview.findViewById(R.id.text_overview);
        genres.findViewById(R.id.text_genres);
        genresIds.findViewById(R.id.text_genresIds);
        releaseDateTextView.findViewById(R.id.text_releaseDateTextView);
    }

    private void getIntentIncoming() {

        if (getIntent().hasExtra("image_name") && getIntent().hasExtra("image_title")
                && getIntent().hasExtra("image_overview") && getIntent().hasExtra("image_genres")
                && getIntent().hasExtra("image_genresID") && getIntent().hasExtra("image_releaseDate")) {

            Log.d(TAG, "getIntentIncoming: found intent extras");

            String imageName = getIntent().getStringExtra("image_name");
            String textTitle = getIntent().getStringExtra("image_title");
            String textOverview = getIntent().getStringExtra("image_overview");
            String textGenres = getIntent().getStringExtra("image_genres");
            String textGenresId = getIntent().getStringExtra("image_genresID");
            String textReleaseDate = getIntent().getStringExtra("image_releaseDate");

            //calling the method
            setImage(textTitle, imageName, textOverview, textGenres, textGenresId, textReleaseDate);

        }
    }

    private void setImage(String textTitle, String imageName, String textOverview,
                          String textGenres, String textGenresId, String textReleaseDate) {

        TextView title = findViewById(R.id.text_title);
        title.setText(textTitle);

        TextView overView = findViewById(R.id.text_overview);
        overView.setText(textOverview);

        TextView genres = findViewById(R.id.text_genres);
        genres.setText(textGenres);

        TextView genresID = findViewById(R.id.text_genresIds);
        genresID.setText(textGenresId);

        TextView releaseDate = findViewById(R.id.text_releaseDateTextView);
        releaseDate.setText(textReleaseDate);

        //setting the imageview
        ImageView image = findViewById(R.id.posterImageView2);
        Glide.with(this)
                .asBitmap()
                .load(imageName)
                .into(image);
    }
}
