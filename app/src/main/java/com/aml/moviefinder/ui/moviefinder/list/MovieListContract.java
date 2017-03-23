package com.aml.moviefinder.ui.moviefinder.list;



import com.aml.moviefinder.ui.base.presenter.BasePresenter;
import com.aml.moviefinder.ui.base.view.BaseView;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

import java.util.List;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public interface MovieListContract {
    public interface View extends BaseView<MovieListContract.Presenter>{
        public void onMovieFound(List<Movie> movie);
    }

    public interface Presenter extends BasePresenter{
        public void getMovies(String search,int pgae,int limit);
    }
}
