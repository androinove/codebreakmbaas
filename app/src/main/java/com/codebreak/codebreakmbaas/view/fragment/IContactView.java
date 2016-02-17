package com.codebreak.codebreakmbaas.view.fragment;

import android.app.Activity;

/**
 * Created by PedroFelipe on 17/02/2016.
 */
public interface IContactView {
    void showRootLayout();

    void hideRootLayout();

    void showLoadingLayout();

    void hideLoadingLayout();

    void showSnackbarMessage(String message, int duration);

    void showSnackbarMessage(int resId, int duration);

    void showMainActivity();

    void showContactDetailActivity();

    Activity getFragmentActivity();
}
