package com.codebreak.codebreakmbaas;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by PedroFelipe on 16/02/2016.
 */
public class CodeBreakMBaaS extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initParse();
    }

    private void initParse() {
        Parse.initialize(getApplicationContext(), getResources().getString(R.string.application_id), getResources().getString(R.string.client_key));
    }

}
