package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;
import com.codebreak.codebreakmbaas.view.fragment.IFeedView;

import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedActivityFragment extends Fragment implements IFeedView {

    private View mView;

    public FeedActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(FeedActivityFragment.this, this.mView);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void showRootLayout() {

    }

    @Override
    public void hideRootLayout() {

    }

    @Override
    public void showLoadingLayout() {

    }

    @Override
    public void hideLoadingLayout() {

    }

    @Override
    public void showSnackbarMessage(String message, int duration) {
        GenericSnackbar.showSnackbar(getFragmentActivity(), this.mView, message, duration);
    }

    @Override
    public void showSnackbarMessage(int resId, int duration) {
        GenericSnackbar.showSnackbar(getFragmentActivity(), this.mView, resId, duration);
    }

    @Override
    public void showContactDetailActivity() {

    }

    @Override
    public Activity getFragmentActivity() {
        return getActivity();
    }
}
