package com.aml.moviefinder.ui.base.fragment;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TableRow;
import android.widget.Toast;


import com.aml.moviefinder.R;
import com.aml.moviefinder.core.utils.ui.ActivityUtils;

import java.util.List;

/**
 * Created by owner on 30/9/16.
 */

public abstract class BaseFragment extends Fragment {

    ProgressDialog progress;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public abstract void initViews();

    public abstract void initListener();

    public abstract void initObjects();

    public void showShortToast(String message) {
        try{
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        }catch (Exception e){

        }


    }

    public void showLongToast(String message) {
        try{
            Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
        }catch (Exception e){

        }
    }


    private void initProgressDialog() {
        progress = new ProgressDialog(getActivity());
        progress.setCancelable(false);
    }

    public void showProgress() {
        showProgress(null);
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
        try{
            if (progress != null && progress.isShowing()) {
                progress.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void hideKeyboard(){
        ActivityUtils.hideKeyboard(getActivity().getCurrentFocus());
    }






}
