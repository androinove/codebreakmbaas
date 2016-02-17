package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.presenter.IUserPresenter;
import com.codebreak.codebreakmbaas.presenter.impl.UserPresenter;
import com.codebreak.codebreakmbaas.util.Constants;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;
import com.codebreak.codebreakmbaas.view.activity.impl.MainActivity;
import com.codebreak.codebreakmbaas.view.activity.impl.NewContactActivity;
import com.codebreak.codebreakmbaas.view.fragment.IFeedView;
import com.codebreak.codebreakmbaas.view.fragment.IUserView;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class FeedActivityFragment extends Fragment implements IFeedView, IUserView, View.OnClickListener {

    private View mView;
    private IUserPresenter mIUserPresenter;

    public FeedActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.bind(FeedActivityFragment.this, this.mView);
        this.mIUserPresenter = new UserPresenter(FeedActivityFragment.this);
        setHasOptionsMenu(true);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_feed, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @OnClick({R.id.floating_action_button_add_contact})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_action_button_add_contact:
                startActivityForResult(new Intent(getFragmentContext(), NewContactActivity.class), Constants.REQUEST_CODE);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                this.mIUserPresenter.signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(Constants.DEBUG_KEY, "requestCode -> " + requestCode + ", resultCode -> " + resultCode + ", data -> " + data);
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
    public void showFeedActivity() {

    }

    @Override
    public Activity getFragmentContext() {
        return getActivity();
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
    public void showMainActivity() {
        getFragmentActivity().startActivity(new Intent(getFragmentActivity(), MainActivity.class));
        getFragmentActivity().finish();
    }

    @Override
    public void showSplashScreen() {

    }

    @Override
    public void hideSplashScreen() {

    }

    @Override
    public void showContactDetailActivity() {

    }

    @Override
    public Activity getFragmentActivity() {
        return getActivity();
    }
}
