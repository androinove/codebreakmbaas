package com.codebreak.codebreakmbaas.view.fragment;

import android.app.Activity;

/**
 * Created by PedroFelipe on 15/02/2016.
 */
public interface IUserView {

    void showSnackbarMessage(String message, int duration);

    void showSnackbarMessage(int resId, int duration);

    void showSplashScreen();

    void hideSplashScreen();

    void showRootLayout();

    void hideRootLayout();

    void showLoadingLayout();

    void hideLoadingLayout();

    void showMainActivity();

    void showFeedActivity();

    Activity getFragmentContext();

}
