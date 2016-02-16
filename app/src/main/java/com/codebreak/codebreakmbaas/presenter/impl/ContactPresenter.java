package com.codebreak.codebreakmbaas.presenter.impl;

import android.app.Activity;

import com.codebreak.codebreakmbaas.model.remote.IContactRemoteDAO;
import com.codebreak.codebreakmbaas.model.remote.impl.ContactRemoteDAO;
import com.codebreak.codebreakmbaas.presenter.IContactPresenter;
import com.codebreak.codebreakmbaas.view.fragment.IFeedView;

/**
 * Created by PedroFelipe on 16/02/2016.
 */
public class ContactPresenter implements IContactPresenter {

    private IFeedView mIFeedView;
    private IContactRemoteDAO mIContactRemoteDAO;

    public ContactPresenter(IFeedView iFeedView) {
        this.mIFeedView = iFeedView;
        this.mIContactRemoteDAO = ContactRemoteDAO.getInstance(ContactPresenter.this);
    }

    @Override
    public void showRootLayout() {
        this.mIFeedView.showRootLayout();
    }

    @Override
    public void hideRootLayout() {
        this.mIFeedView.hideRootLayout();
    }

    @Override
    public void showLoadingLayout() {
        this.mIFeedView.showLoadingLayout();
    }

    @Override
    public void hideLoadingLayout() {
        this.mIFeedView.hideLoadingLayout();
    }

    @Override
    public void showSnackbarMessage(String message, int duration) {
        this.mIFeedView.showSnackbarMessage(message, duration);
    }

    @Override
    public void showSnackbarMessage(int resId, int duration) {
        this.mIFeedView.showSnackbarMessage(resId, duration);
    }

    @Override
    public void showContactDetailActivity() {
        this.mIFeedView.showContactDetailActivity();
    }

    @Override
    public Activity getFragmentActivity() {
        return this.mIFeedView.getFragmentActivity();
    }
}
