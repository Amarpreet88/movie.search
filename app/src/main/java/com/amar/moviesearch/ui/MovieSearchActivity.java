package com.amar.moviesearch.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amar.moviesearch.MovieSearchApplication;
import com.amar.moviesearch.R;
import com.amar.moviesearch.factory.MovieSearchFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieSearchActivity extends AppCompatActivity {

    @Inject
    MovieSearchFactory viewModelFactory;

    @BindView(R.id.movies_recyler_view)
    RecyclerView moviesRecylerView;
    @BindView(R.id.empty_view)
    TextView searchMovies;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private MovieSearchViewModel movieSearchViewModel;
    private MovieSearchAdapter movieSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search);
        MovieSearchApplication.getDaggerComponent().inject(this);
        ButterKnife.bind(this);
        movieSearchViewModel = ViewModelProviders.of(this,
                viewModelFactory).get(MovieSearchViewModel.class);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchMovies.setVisibility(View.VISIBLE);
        initRecylerView();
    }

    private void initRecylerView() {
        movieSearchAdapter = new MovieSearchAdapter();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        moviesRecylerView.setLayoutManager(mLayoutManager);
        moviesRecylerView.addItemDecoration(new DividerItemDecoration(this,
                mLayoutManager.getOrientation()));
        moviesRecylerView.setAdapter(movieSearchAdapter);
    }

    private void getMovieList() {
        if (movieSearchViewModel != null) {
            movieSearchViewModel.getMovieList().observe(this, movieSearch -> {
                if (movieSearch != null && movieSearch.getSearch() != null) {
                    moviesRecylerView.setVisibility(View.VISIBLE);
                    searchMovies.setVisibility(View.GONE);
                    moviesRecylerView.setVisibility(View.VISIBLE);
                    movieSearchAdapter.setMovieSearch(movieSearch);
                } else {
                    searchMovies.setText(R.string.no_result_found);
                    moviesRecylerView.setVisibility(View.GONE);
                    searchMovies.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);

            });
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            progressBar.setVisibility(View.VISIBLE);
            movieSearchViewModel.fetchMovies(query);
            getMovieList();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconified(false);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

}
