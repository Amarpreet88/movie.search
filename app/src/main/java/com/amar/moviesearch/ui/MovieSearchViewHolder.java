package com.amar.moviesearch.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amar.moviesearch.R;
import com.amar.moviesearch.data.model.MovieSearchDetails;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieSearchViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.year)
    TextView year;

    public MovieSearchViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(MovieSearchDetails movieSearchDetails) {
        if (movieSearchDetails != null) {
            title.setText(movieSearchDetails.getTitle());
            year.setText(movieSearchDetails.getYear());
            Picasso.get()
                    .load(movieSearchDetails.getPoster())
                    .error(R.drawable.ic_placeholder)
                    .into(poster);

        }
    }

}
