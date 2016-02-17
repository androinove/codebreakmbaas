package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.presenter.IUserPresenter;
import com.codebreak.codebreakmbaas.presenter.impl.UserPresenter;
import com.codebreak.codebreakmbaas.util.Constants;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;
import com.codebreak.codebreakmbaas.view.activity.impl.FeedActivity;
import com.codebreak.codebreakmbaas.view.activity.impl.SignUpActivity;
import com.codebreak.codebreakmbaas.view.fragment.IUserView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IUserView, View.OnClickListener {

    @Bind(R.id.activity_main_splash_screen) RelativeLayout mRelativeLayoutSplashScreen;
    @Bind(R.id.relative_layout_sign_in) RelativeLayout mRelativeLayoutSignIn;
    @Bind(R.id.relative_layout_loading) RelativeLayout mRelativeLayoutLoading;
    @Bind(R.id.edit_text_username) EditText mEditTextUsername;
    @Bind(R.id.edit_text_password) EditText mEditTextPassword;

    private View mView;
    private IUserPresenter mIUserPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, MainActivityFragment.this.mView);
        this.mIUserPresenter = new UserPresenter(MainActivityFragment.this);
        return this.mView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        verifyUserAuthenticated();
                    }
                });
            }
        }, 2000);
    }

    private void verifyUserAuthenticated() {
        if (this.mIUserPresenter.getCurrentUser() != null) {
            this.showFeedActivity();
            Log.d(Constants.DEBUG_KEY, "User connected");
        } else {
            Log.d(Constants.DEBUG_KEY, "User not connected");
            this.hideSplashScreen();
            this.showRootLayout();
        }
    }

    private String getUsername() {
        return this.mEditTextUsername.getText().toString();
    }

    private String getPassword() {
        return this.mEditTextPassword.getText().toString();
    }

    @OnClick({R.id.button_sign_in, R.id.button_sign_up})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                this.mIUserPresenter.signIn(getUsername(), getPassword());
                break;
            case R.id.button_sign_up:
                startActivityForResult(new Intent(getFragmentContext(), SignUpActivity.class), Constants.REQUEST_CODE);
                break;
        }
    }

    @Override
    public void showSnackbarMessage(String message, int duration) {
        GenericSnackbar.showSnackbar(getFragmentContext(), this.mView, message, duration);
    }

    @Override
    public void showSnackbarMessage(int resId, int duration) {
        GenericSnackbar.showSnackbar(getFragmentContext(), this.mView, resId, duration);
    }

    @Override
    public void showSplashScreen() {
        this.mRelativeLayoutSplashScreen.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSplashScreen() {
        this.mRelativeLayoutSplashScreen.setVisibility(View.GONE);
    }

    @Override
    public void showRootLayout() {
        this.mRelativeLayoutSignIn.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootLayout() {
        this.mRelativeLayoutSignIn.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingLayout() {
        this.mRelativeLayoutLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingLayout() {
        this.mRelativeLayoutLoading.setVisibility(View.GONE);
    }

    @Override
    public void showMainActivity() {

    }

    @Override
    public void showFeedActivity() {
        startActivity(new Intent(getFragmentContext(), FeedActivity.class));
        getFragmentContext().finish();
    }

    @Override
    public Activity getFragmentContext() {
        return getActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_CODE:
                switch (resultCode) {
                    case AppCompatActivity.RESULT_OK:
                    getFragmentContext().finish();
                    break;
                }
                break;
        }
    }
}
