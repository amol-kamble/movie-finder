package com.aml.moviefinder.ui.moviefinder.list;

import com.aml.moviefinder.core.network.repository.MovieRepository;
import com.aml.moviefinder.core.network.repository.RepositoryCallback;
import com.aml.moviefinder.core.network.response.MovieListResponse;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

import java.util.List;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public class MovieListPresenter implements MovieListContract.Presenter {
    private static final String TAG = MovieListPresenter.class.getSimpleName();
    MovieListContract.View view;

    public MovieListPresenter(MovieListContract.View view) {
        this.view=view;
        this.view.setPresenter(this);
    }


    @Override
    public void start() {

    }

    @Override
    public void getMovies(String search, int page, int limit) {
        if(search==null || search.isEmpty()){
            search="a"; // load movie alphabetically
        }
        MovieRepository.getMovies(search, page, new RepositoryCallback<List<Movie>>() {
            @Override
            public void onResponse(List<Movie> movies) {
                if(movies.isEmpty()){
                    view.onError("Oops, Nothing to show!");
                }else{
                    view.onMovieFound(movies);
                }
            }

            @Override
            public void onError(String errorMessage) {
                view.onError(errorMessage);
            }

            @Override
            public void onException(Throwable e) {

            }
        });
    }
}
