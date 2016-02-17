package com.codebreak.codebreakmbaas.presenter.impl;

import android.app.Activity;

import com.codebreak.codebreakmbaas.model.remote.IContactRemoteDAO;
import com.codebreak.codebreakmbaas.model.remote.impl.ContactRemoteDAO;
import com.codebreak.codebreakmbaas.presenter.IContactPresenter;
import com.codebreak.codebreakmbaas.view.fragment.IContactView;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by PedroFelipe on 16/02/2016.
 */
public class ContactPresenter implements IContactPresenter {

    private IContactView mIContactView;
    private IContactRemoteDAO mIContactRemoteDAO;

    public ContactPresenter(IContactView iContactView) {
        this.mIContactView = iContactView;
        this.mIContactRemoteDAO = ContactRemoteDAO.getInstance(ContactPresenter.this);
    }

    @Override
    public void saveContact(ParseObject newContact) {
        this.mIContactRemoteDAO.saveContact(newContact);
    }

    @Override
    public void updateContact(ParseObject contact) {

    }

    @Override
    public void deleteContact(ParseObject parseObject) {

    }

    @Override
    public void getContacts(ParseUser parseUser) {
        this.mIContactRemoteDAO.getContacts(parseUser);
    }

    @Override
    public void showContactsOnUI(List<ParseObject> contacts) {
        this.mIContactView.showContactsOnUI(contacts);
    }

    @Override
    public void showRootLayout() {
        this.mIContactView.showRootLayout();
    }

    @Override
    public void hideRootLayout() {
        this.mIContactView.hideRootLayout();
    }

    @Override
    public void showLoadingLayout() {
        this.mIContactView.showLoadingLayout();
    }

    @Override
    public void hideLoadingLayout() {
        this.mIContactView.hideLoadingLayout();
    }

    @Override
    public void showSnackbarMessage(String message, int duration) {
        this.mIContactView.showSnackbarMessage(message, duration);
    }

    @Override
    public void showSnackbarMessage(int resId, int duration) {
        this.mIContactView.showSnackbarMessage(resId, duration);
    }

    @Override
    public void showMainActivity() {
        this.mIContactView.showMainActivity();
    }

    @Override
    public void showContactDetailActivity() {
        this.mIContactView.showContactDetailActivity();
    }

    @Override
    public Activity getFragmentActivity() {
        return this.mIContactView.getFragmentActivity();
    }
}
