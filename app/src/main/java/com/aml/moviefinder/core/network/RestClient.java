package com.aml.moviefinder.core.network;

import com.aml.moviefinder.core.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by owner on 2/7/15.
 */
public class RestClient {


    public static String ROOT = Constants.MovieAPI.END_POINT;
    private static Gson gson;
    private static Retrofit retrofit;


    public static void setDefaultRestClient() {
        gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(ROOT)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static Retrofit getDefaultRestClient() {
        if (retrofit != null) {
            return retrofit;
        } else {
            setDefaultRestClient();
            return retrofit;
        }
    }

    public static Retrofit getRestClient(String root) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(root)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

}
