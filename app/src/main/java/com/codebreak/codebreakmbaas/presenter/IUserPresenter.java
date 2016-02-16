package com.codebreak.codebreakmbaas.presenter;

import android.app.Activity;

import com.parse.ParseUser;

/**
 * Created by PedroFelipe on 15/02/2016.
 */
public interface IUserPresenter {

    ParseUser getCurrentUser();

    void signUp(ParseUser parseUser);

    void signIn(String email, String password);

    void signOut();

    void showSnackbarMessage(String message, int duration);

    void showSnackbarMessage(int resId, int duration);

    void showSplashScreen();

    void hideSplashScreen();

    void showRootLayout();

    void hideRootLayout();

    void showLoadingLayout();

    void hideLoadingLayout();

    void showFeedActivity();

    Activity getFragmentContext();

}
