package com.codebreak.codebreakmbaas.presenter;

import android.app.Activity;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Stack;

/**
 * Created by PedroFelipe on 16/02/2016.
 */
public interface IContactPresenter {

    void saveContact(ParseObject newContact);

    void updateContact(ParseObject contact);

    void deleteContact(ParseObject parseObject);

    Stack<ParseObject> getContacts(ParseUser parseUser);

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
