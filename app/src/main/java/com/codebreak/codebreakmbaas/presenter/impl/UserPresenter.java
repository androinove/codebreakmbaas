package com.codebreak.codebreakmbaas.presenter.impl;

import android.app.Activity;

import com.codebreak.codebreakmbaas.model.remote.IUserRemoteDAO;
import com.codebreak.codebreakmbaas.model.remote.impl.UserRemoteDAO;
import com.codebreak.codebreakmbaas.presenter.IUserPresenter;
import com.codebreak.codebreakmbaas.view.fragment.IUserView;
import com.parse.ParseUser;

/**
 * Created by PedroFelipe on 15/02/2016.
 */
public class UserPresenter implements IUserPresenter {

    private IUserView mIUserView;
    private IUserRemoteDAO mIUserRemoteDAO;

    public UserPresenter(IUserView iUserView) {
        this.mIUserView = iUserView;
        this.mIUserRemoteDAO = UserRemoteDAO.getInstance(UserPresenter.this);
    }

    @Override
    public ParseUser getCurrentUser() {
        return this.mIUserRemoteDAO.getCurrentUser();
    }

    @Override
    public void signUp(ParseUser parseUser) {
        this.hideRootLayout();
        this.showLoadingLayout();
        this.mIUserRemoteDAO.signUp(parseUser);
    }

    @Override
    public void signIn(String email, String password) {
        this.hideRootLayout();
        this.showLoadingLayout();
        this.mIUserRemoteDAO.signIn(email, password);
    }

    @Override
    public void signOut() {
        this.mIUserRemoteDAO.signOut();
    }

    @Override
    public void showSnackbarMessage(String message, int duration) {
        this.mIUserView.showSnackbarMessage(message, duration);
    }

    @Override
    public void showSnackbarMessage(int resId, int duration) {
        this.mIUserView.showSnackbarMessage(resId, duration);
    }

    @Override
    public void showSplashScreen() {
        this.mIUserView.showSplashScreen();
    }

    @Override
    public void hideSplashScreen() {
        this.mIUserView.hideSplashScreen();
    }

    @Override
    public void showRootLayout() {
        this.mIUserView.showRootLayout();
    }

    @Override
    public void hideRootLayout() {
        this.mIUserView.hideRootLayout();
    }

    @Override
    public void showLoadingLayout() {
        this.mIUserView.showLoadingLayout();
    }

    @Override
    public void hideLoadingLayout() {
        this.mIUserView.hideLoadingLayout();
    }

    @Override
    public void showFeedActivity() {
        this.mIUserView.showFeedActivity();
    }

    @Override
    public Activity getFragmentContext() {
        return this.mIUserView.getFragmentContext();
    }
}
