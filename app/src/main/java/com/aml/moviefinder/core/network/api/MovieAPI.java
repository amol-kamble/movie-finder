package com.aml.moviefinder.core.network.api;


import com.aml.moviefinder.core.network.response.MovieListResponse;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by owner on 2/11/16.
 */

public interface MovieAPI {

    @GET("/")
    Call<MovieListResponse> getMovies(@Query("s") String name,
                                      @Query("page") int page);

    @GET("/")
    Call<Movie> getMovie(@Query("i") String imbdId);
}
