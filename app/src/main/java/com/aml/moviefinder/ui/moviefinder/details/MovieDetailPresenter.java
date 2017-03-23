package com.aml.moviefinder.ui.moviefinder.details;

import com.aml.moviefinder.core.network.repository.MovieRepository;
import com.aml.moviefinder.core.network.repository.RepositoryCallback;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public class MovieDetailPresenter implements MovieDetailContract.Presenter {
    private static final String TAG = MovieDetailPresenter.class.getSimpleName();
    MovieDetailContract.View view;

    public MovieDetailPresenter(MovieDetailContract.View view) {
        this.view=view;
        this.view.setPresenter(this);
    }

    @Override
    public void getMovie(String imdbId) {
        MovieRepository.getMovie(imdbId, new RepositoryCallback<Movie>() {
            @Override
            public void onResponse(Movie movie) {
                view.onMovieFound(movie);
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

    @Override
    public void start() {

    }
}
