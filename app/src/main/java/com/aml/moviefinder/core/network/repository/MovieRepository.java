package com.aml.moviefinder.core.network.repository;

import com.aml.moviefinder.core.Constants;
import com.aml.moviefinder.core.network.RestClient;
import com.aml.moviefinder.core.network.api.MovieAPI;
import com.aml.moviefinder.core.network.response.MovieListResponse;
import com.aml.moviefinder.ui.moviefinder.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amolkamble on 22/03/2017 AD.
 */

public class MovieRepository {

    public static void getMovies(String search, int page, final RepositoryCallback<List<Movie>> callback){
        MovieAPI movieAPI= RestClient.getDefaultRestClient().create(MovieAPI.class);
        final Call<MovieListResponse> request=movieAPI.getMovies(search,page);
        request.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                if(response.isSuccessful() && response.body().getResponse().equalsIgnoreCase("true")){
                    callback.onResponse(response.body().getMovies());
                }else{
                    callback.onError("Oops, Nothing found!");
                }

            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                    callback.onError("Oops, Something went wrong. Please check your connection " +
                            "try again later");
            }
        });
    }

    public static void getMovie(String imdbId, final RepositoryCallback<Movie> callback){
        MovieAPI movieAPI= RestClient.getDefaultRestClient().create(MovieAPI.class);
        final Call<Movie> request=movieAPI.getMovie(imdbId);
        request.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful() && response.body().getResponse().equalsIgnoreCase("true")){
                    callback.onResponse(response.body());
                }else{
                    callback.onError("Oops, Nothing found!");
                }

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.onError("Oops, Something went wrong. Please check your connection " +
                        "try again later");
            }
        });
    }

}
