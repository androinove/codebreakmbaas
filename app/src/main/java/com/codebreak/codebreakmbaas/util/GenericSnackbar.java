package com.codebreak.codebreakmbaas.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by PedroFelipe on 07/11/2015.
 */
public class GenericSnackbar {

    public static void showSnackbar(Context context, View view, String message, int duration) {
        Snackbar snackbar = Snackbar.make(view, message, duration);
        //snackbar.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        //((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        snackbar.show();
    }

    public static void showSnackbar(Context context, View view, int resId, int duration) {
        Snackbar snackbar = Snackbar.make(view, resId, duration);
        //snackbar.getView().setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        //((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        snackbar.show();
    }

}
