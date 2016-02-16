package com.codebreak.codebreakmbaas.util;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by PedroFelipe on 06/01/2016.
 */
public class GsonUtil {

    private static GsonUtil mInstance;

    private GsonUtil() {

    }

    public static GsonUtil getInstance() {
        synchronized (GsonUtil.class) {
            if (mInstance == null) {
                mInstance = new GsonUtil();
            }
        }
        return mInstance;
    }

    public Gson buildGson() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

}
