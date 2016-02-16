package com.codebreak.codebreakmbaas.model.remote.impl;

import android.support.design.widget.Snackbar;

import com.codebreak.codebreakmbaas.model.remote.IUserRemoteDAO;
import com.codebreak.codebreakmbaas.presenter.IUserPresenter;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by PedroFelipe on 15/02/2016.
 */
public class UserRemoteDAO implements IUserRemoteDAO {

    private static IUserRemoteDAO mInstance;
    private static IUserPresenter mIUserPresenter;

    private UserRemoteDAO() {

    }

    public static IUserRemoteDAO getInstance(IUserPresenter iUserPresenter) {
        synchronized (UserRemoteDAO.class) {
            if (mInstance == null) {
                mInstance = new UserRemoteDAO();
            }
        }
        mIUserPresenter = iUserPresenter;
        return mInstance;
    }

    @Override
    public ParseUser getCurrentUser() {
        return ParseUser.getCurrentUser();
    }

    @Override
    public void signUp(ParseUser parseUser) {
        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mIUserPresenter.showFeedActivity();
                } else {
                    mIUserPresenter.hideLoadingLayout();
                    mIUserPresenter.showRootLayout();
                    mIUserPresenter.showSnackbarMessage("Erro " + e.getMessage(), Snackbar.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        ParseUser.logInInBackground(email, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    mIUserPresenter.showFeedActivity();
                } else {
                    mIUserPresenter.hideLoadingLayout();
                    mIUserPresenter.showRootLayout();
                    mIUserPresenter.showSnackbarMessage("Algo deu errado: " + e.getMessage(), Snackbar.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    public void signOut() {

    }
}
