package com.aml.moviefinder.ui.base.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.aml.moviefinder.R;
import com.aml.moviefinder.core.utils.common.Logger;
import com.aml.moviefinder.core.utils.ui.ActivityUtils;


/**
 * Created by owner on 30/9/16.
 */

public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void addFragmentToActivity(Fragment fragment, int fragmentID, Bundle bundle, boolean addToBackStack) {
        if (bundle == null) {
            bundle = new Bundle();

        }
        fragment.setArguments(bundle);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, fragmentID,addToBackStack);
    }

    public void addFragmentToActivity(Fragment fragment, int fragmentID, Bundle bundle) {
        addFragmentToActivity(fragment, fragmentID, bundle, false);
    }

    public void replaceFragmentInActivity(Fragment fragment, int fragmentID, Bundle bundle, boolean addToBackStack) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        fragment.setArguments(bundle);
        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(), fragment, fragmentID,addToBackStack);
    }

    public void reloadFragment(Fragment fragment) {
        FragmentTransaction fragTransaction =   this.getSupportFragmentManager().beginTransaction();
        fragTransaction.detach(fragment);
        fragTransaction.attach(fragment);
        fragTransaction.commit();

    }

    public void replaceFragmentInActivity(Fragment fragment, int fragmentID, Bundle bundle) {
        replaceFragmentInActivity(fragment, fragmentID, bundle, false);
    }


    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showProgress() {
        showProgress(null);
    }

    private void initProgressDialog() {
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
    }

    public synchronized void showProgress(String message) {
        if (progress == null) {
            initProgressDialog();
        }
        if (progress != null && !progress.isShowing()) {
            try {
                if (message != null && !message.isEmpty()) {
                    progress.setMessage(message);
                }
                progress.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                progress.show();
                progress.setContentView(R.layout.dialog_progress);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void hideProgress() {
        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }


    public void hideKeyboard() {
        ActivityUtils.hideKeyboard(getCurrentFocus());
    }

    public static void logPayment(String log){
        Logger.d("Payment","In app purchase -> "+log);
    }




}
