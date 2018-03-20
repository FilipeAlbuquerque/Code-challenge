package com.arctouch.codechallenge.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.home.DetailsActivity;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    //adding Context
    private List<Movie> movies;
    private Context mContext;

    //constructor
    public HomeAdapter(Context mContext, List<Movie> movies) {
        this.movies = movies;
        this.mContext=mContext;
    }

    //hold the image in memory of each indivudal item

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();

        private final TextView titleTextView;
        private final TextView genresTextView;
        private final TextView releaseDateTextView;
        private final ImageView posterImageView;
        CardView parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            genresTextView = itemView.findViewById(R.id.genresTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }

        public void bind(Movie movie) {
            titleTextView.setText(movie.title);
            genresTextView.setText(TextUtils.join(", ", movie.genres));
            releaseDateTextView.setText(movie.releaseDate);


            String posterPath = movie.posterPath;
            if (TextUtils.isEmpty(posterPath) == false) {
                Glide.with(itemView)
                        .load(movieImageUrlBuilder.buildPosterUrl(posterPath))
                        .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                        .into(posterImageView);
            }
        }
    }
                // method responsible to inflate the view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position));

        holder.parentLayout.setOnClickListener(view -> {

            //Changing to another activity after clicked
            Intent intent = new Intent(mContext, DetailsActivity.class);
            intent.putExtra("original_title", movies.get(position).getTitle());
            intent.putExtra("image_name", movies.get(position).getTitle());
            intent.putExtra("poster_path", movies.get(position).getPosterPath());
            intent.putExtra("overview", movies.get(position).getOverview());
            intent.putExtra("release_date", movies.get(position).getReleaseDate());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //starting the activity
            mContext.startActivity(intent);

            Toast.makeText(view.getContext(), "You clicked in: " +
                    movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        });
    }
}
