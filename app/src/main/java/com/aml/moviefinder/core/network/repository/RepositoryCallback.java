package com.aml.moviefinder.core.network.repository;

/**
 * Created by owner on 26/10/16.
 */

public interface RepositoryCallback<T> {
    public void onResponse(T t);
    public void onError(String errorMessage);
    public void onException(Throwable e);
}
