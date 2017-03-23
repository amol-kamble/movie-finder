package com.aml.moviefinder.ui.moviefinder.list;

import android.os.Bundle;

import com.aml.moviefinder.R;
import com.aml.moviefinder.ui.base.activity.BaseActivity;

public class MovieListActivity extends BaseActivity {

    MovieListFragment movieListFragment;
    MovieListPresenter movieListPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addMovieListFragment();
    }

    public void addMovieListFragment(){
        movieListFragment=new MovieListFragment();
        addFragmentToActivity(movieListFragment,R.id.container,null,false);
        movieListPresenter=new MovieListPresenter(movieListFragment);
    }
}
