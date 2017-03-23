package com.aml.moviefinder.ui.base.view;

/**
 * Created by owner on 6/10/16.
 */

public interface BaseViewBasic {

    void showProgress();

    void hideProgress();

    void onError(String error);

    void onException(String message);

}