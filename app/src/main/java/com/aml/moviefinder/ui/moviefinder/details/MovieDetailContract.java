package com.aml.moviefinder.ui.moviefinder.details;



import com.aml.moviefinder.ui.base.presenter.BasePresenter;
import com.aml.moviefinder.ui.base.view.BaseView;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public interface MovieDetailContract {
    public interface View extends BaseView<MovieDetailContract.Presenter>{
        public void onMovieFound(Movie movie);
    }

    public interface Presenter extends BasePresenter{
        public void getMovie(String imdbId);
    }
}
