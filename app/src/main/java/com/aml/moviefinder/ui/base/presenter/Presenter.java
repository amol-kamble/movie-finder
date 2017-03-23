package com.aml.moviefinder.ui.base.presenter;

import android.content.pm.PackageInstaller;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by owner on 21/11/16.
 */

public abstract class Presenter {
    public static final int DEFAULT_DELAY = 200;
    Timer timer;

    public void startInitializationDelay() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel();
                onPresenterStart();
            }
        }, DEFAULT_DELAY, DEFAULT_DELAY);
    }

    abstract public void onPresenterStart();


}
