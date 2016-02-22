package com.codebreak.codebreakmbaas.model.remote.impl;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.codebreak.codebreakmbaas.model.remote.IContactRemoteDAO;
import com.codebreak.codebreakmbaas.presenter.IContactPresenter;
import com.codebreak.codebreakmbaas.util.Constants;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by PedroFelipe on 16/02/2016.
 */
public class ContactRemoteDAO implements IContactRemoteDAO {

    private static IContactRemoteDAO mInstance;
    private static IContactPresenter mIContactPresenter;

    private ContactRemoteDAO() {

    }

    public static IContactRemoteDAO getInstance(IContactPresenter iContactPresenter) {
        synchronized (ContactRemoteDAO.class) {
            if (mInstance == null) {
                mInstance = new ContactRemoteDAO();
            }
        }
        mIContactPresenter = iContactPresenter;
        return mInstance;
    }

    @Override
    public void saveContact(ParseObject newContact) {
        mIContactPresenter.hideRootLayout();
        mIContactPresenter.showLoadingLayout();
        newContact.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    mIContactPresenter.showMainActivity();
                } else {
                    mIContactPresenter.hideLoadingLayout();
                    mIContactPresenter.showRootLayout();
                    mIContactPresenter.showSnackbarMessage("Erro ao salvar contato: " + e.getMessage(), Snackbar.LENGTH_LONG);
                }
            }
        });
    }

    @Override
    public void updateContact(ParseObject contact) {

    }

    @Override
    public void deleteContact(ParseObject parseObject) {

    }

    @Override
    public void getContacts(ParseUser parseUser) {
        Log.d(Constants.DEBUG_KEY, "Entrou de novo");
        mIContactPresenter.showRefresh();
        ParseQuery<ParseObject> parseContacts = ParseQuery.getQuery("Contact");
        parseContacts.whereEqualTo("user", parseUser);
        parseContacts.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Log.d(Constants.DEBUG_KEY, "Chamando objetos -> " + objects.size());
                    mIContactPresenter.showContactsOnUI(objects);
                    mIContactPresenter.hideRefresh();
                } else {
                    mIContactPresenter.hideRefresh();
                    mIContactPresenter.showSnackbarMessage("Algo deu errado: " + e.getMessage(), Snackbar.LENGTH_LONG);
                }
            }
        });
    }
}
