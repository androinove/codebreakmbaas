package com.codebreak.codebreakmbaas.view.fragment.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.codebreak.codebreakmbaas.R;
import com.codebreak.codebreakmbaas.presenter.impl.UserPresenter;
import com.codebreak.codebreakmbaas.util.GenericSnackbar;
import com.codebreak.codebreakmbaas.view.activity.impl.FeedActivity;
import com.codebreak.codebreakmbaas.view.fragment.IUserView;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class SignUpActivityFragment extends Fragment implements View.OnClickListener, IUserView {

    @Bind(R.id.relative_layout_sign_up) RelativeLayout mRelativeLayoutSignUp;
    @Bind(R.id.relative_layout_loading) RelativeLayout mRelativeLayoutLoading;
    @Bind(R.id.edit_text_username) EditText mEditTextUsername;
    @Bind(R.id.edit_text_email) EditText mEditTextEmail;
    @Bind(R.id.edit_text_password) EditText mEditTextPassword;
    @Bind(R.id.edit_text_confirm_password) EditText mEditTextConfirmPassword;

    private View mView;
    private UserPresenter mUserPresenter;

    public SignUpActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(SignUpActivityFragment.this, this.mView);
        this.mUserPresenter = new UserPresenter(SignUpActivityFragment.this);
        return this.mView;
    }

    @OnClick({R.id.button_sign_up})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_up:
                this.mUserPresenter.signUp(getUserData());
                break;
        }
    }

    private ParseUser getUserData() {
        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(mEditTextUsername.getText().toString());
        parseUser.setEmail(mEditTextEmail.getText().toString());
        parseUser.setPassword(mEditTextPassword.getText().toString());
        return parseUser;
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

    }

    @Override
    public void hideSplashScreen() {

    }

    @Override
    public void showRootLayout() {
        this.mRelativeLayoutSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootLayout() {
        this.mRelativeLayoutSignUp.setVisibility(View.GONE);
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
        getFragmentContext().setResult(AppCompatActivity.RESULT_OK);
        getFragmentContext().finish();
    }

    @Override
    public Activity getFragmentContext() {
        return getActivity();
    }
}
