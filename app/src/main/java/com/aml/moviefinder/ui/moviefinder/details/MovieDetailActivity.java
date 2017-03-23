package com.aml.moviefinder.ui.moviefinder.details;

import android.os.Bundle;

import com.aml.moviefinder.R;
import com.aml.moviefinder.ui.base.activity.BaseActivity;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

public class MovieDetailActivity extends BaseActivity {

    MovieDetailFragment movieDetailFragment;
    MovieDetailPresenter movieDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        addMovidDetailFragment();
    }
    public void addMovidDetailFragment(){
        movieDetailFragment=new MovieDetailFragment();
        addFragmentToActivity(movieDetailFragment,R.id.container,getIntent().getExtras(),false);
        movieDetailPresenter=new MovieDetailPresenter(movieDetailFragment);
    }
}
