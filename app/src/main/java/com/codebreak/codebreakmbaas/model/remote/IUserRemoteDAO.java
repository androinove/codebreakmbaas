package com.codebreak.codebreakmbaas.model.remote;

import com.parse.ParseUser;

/**
 * Created by PedroFelipe on 15/02/2016.
 */
public interface IUserRemoteDAO {

    ParseUser getCurrentUser();

    void signUp(ParseUser parseUser);

    void signIn(String email, String password);

    void signOut();

}
