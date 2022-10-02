package com.lemzeeyyy.retrofitdemo4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> moviesList;

    public MovieAdapter(Context context, List<Movie> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.title.setText(moviesList.get(position).getTitle());

        // The Trick: Getting data from the nested json object
        holder.duration.setText(moviesList.get(position).getDetails().getDuration());
        holder.category.setText(moviesList.get(position).getDetails().getCategory());
        holder.release.setText(moviesList.get(position).getDetails().getRelease());


        // Adding Glide library to display the images
        Glide.with(context)
                .load(moviesList.get(position).getPoster())
                .into(holder.img);


        // Setting the rating bar value
        // Rating bar is 5 and ratings is over 10
        holder.ratingBar.setRating((Float.parseFloat(String.valueOf(moviesList.get(position).getRating())))/2);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView duration;
        ImageView img;

        RatingBar ratingBar;
        TextView category;
        TextView release;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            duration = itemView.findViewById(R.id.textView3);
            img = itemView.findViewById(R.id.imageView);
            ratingBar =  itemView.findViewById(R.id.ratingBar);
            category = itemView.findViewById(R.id.textView);
            release = itemView.findViewById(R.id.textView4);

        }
    }
}
