package com.aml.moviefinder.ui.moviefinder.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.aml.moviefinder.R;
import com.aml.moviefinder.core.utils.ui.OnItemClickListener;
import com.aml.moviefinder.ui.base.fragment.BaseFragment;
import com.aml.moviefinder.ui.base.fragment.PageableBaseFragment;
import com.aml.moviefinder.ui.base.model.Pagination;
import com.aml.moviefinder.ui.moviefinder.details.MovieDetailActivity;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

import java.util.List;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public class MovieListFragment extends PageableBaseFragment<Movie,MovieListAdapter> implements MovieListContract.View,OnItemClickListener<Movie>{

    private static final String TAG = MovieListFragment.class.getSimpleName();

    View rootView;
    ProgressBar progressBar;
    MovieListContract.Presenter presenter;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movie_list_fragment, container, false);
        initViews();
        initListener();
        initObjects();
        return rootView;
    }

    @Override
    public void initViews() {
        list=(RecyclerView) rootView.findViewById(R.id.list);
        progressBar=(ProgressBar) rootView.findViewById(R.id.progress);
        searchView=(SearchView) rootView.findViewById(R.id.search);
        super.initViews();
        hideList();
    }

    @Override
    public void initListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                itemList.clear();
                hideList();
                refreshList();
                return false;
            }
        });

        super.initListener();
    }

    @Override
    public void initObjects() {
        adapter=new MovieListAdapter(itemList);
        adapter.setOnItemClickListener(this);
        super.initObjects();
        loadMovies();
    }

    public void loadMovies(){
        presenter.getMovies(searchView.getQuery().toString(),pagination.getNumber(),pagination.getSize());
    }

    @Override
    public void onLoadMore(Pagination pagination) {
        loadMovies();
    }

    @Override
    public void onMovieFound(List<Movie> movie) {
        setListItems(movie);
    }

    @Override
    public void setPresenter(MovieListContract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void onError(String error) {
       // showShortToast(error);
        showEmptyState();
    }

    @Override
    public void onException(String message) {
        showShortToast(message);
        showEmptyState();
    }

    public void showEmptyState(){
        list.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(Movie movie) {
        Bundle bundle=new Bundle();
        bundle.putString(Movie.IMDB_ID,movie.getImdbID());
        Intent intent=new Intent(getActivity(), MovieDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
