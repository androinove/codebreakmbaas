package com.codebreak.codebreakmbaas.util;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by PedroFelipe on 14/11/2015.
 */
public class SystemServices {

    public static void changeToolbarTitle(Context context, String newTitle) {
        ((AppCompatActivity) context).getSupportActionBar().setTitle(newTitle);
    }

    public static void closeKeyboard(Context context, Activity activity) {
        InputMethodManager inputManager = (InputMethodManager)
                context.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
