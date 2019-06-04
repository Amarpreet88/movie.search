package com.amar.moviesearch.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.amar.moviesearch.R;
import com.amar.moviesearch.data.model.MovieSearch;
import com.amar.moviesearch.data.model.MovieSearchDetails;

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchViewHolder> {

    private MovieSearch movieSearch;

    @Override
    public MovieSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);
        return new MovieSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieSearchViewHolder holder, int position) {
        MovieSearchDetails movieSearchDetails = movieSearch.getSearch().get(position);
        holder.bind(movieSearchDetails);
    }

    @Override
    public int getItemCount() {
        return movieSearch == null ? 0 : movieSearch.getSearch().size();
    }

    public void setMovieSearch(MovieSearch movieSearch) {
        if (movieSearch != null) {
            this.movieSearch = movieSearch;
            notifyDataSetChanged();
        }
    }
}
