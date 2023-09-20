package com.sharveshapps.retroapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharveshapps.retroapp.Movie;
import com.sharveshapps.retroapp.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movieList;

    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.textViewTitle.setText(movie.getTitleText().getText());
        holder.textViewId.setText("ID: " + movie.getId());

        // Load the movie image using Picasso
        if (movie.getPrimaryImage() != null) {
            Picasso.get()
                    .load(movie.getPrimaryImage().getUrl())
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movies) {
        this.movieList = movies;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewId;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.movie_image);
            textViewTitle = itemView.findViewById(R.id.movie_title);
            textViewId = itemView.findViewById(R.id.movie_id);
        }
    }
}
