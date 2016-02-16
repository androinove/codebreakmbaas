package com.codebreak.codebreakmbaas.model.remote;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Stack;

/**
 * Created by PedroFelipe on 16/02/2016.
 */
public interface IContactRemoteDAO {

    void saveContact(ParseObject newContact);

    void updateContact(ParseObject contact);

    void deleteContact(ParseObject parseObject);

    Stack<ParseObject> getContacts(ParseUser parseUser);

}
