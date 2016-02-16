package com.codebreak.codebreakmbaas.util;

/**
 * Created by PedroFelipe on 16/11/2015.
 */
public class DateUtil {

    public static String convertDateTimeToDateString(String dateTimeString) {
        StringBuilder stringBuilder = new StringBuilder();
        // WebserviceDate: 2015-10-28T22:10:15

        String[] dateSplit = dateTimeString.split("T")[0].split("-");
        String[] timeSplit = dateTimeString.split("T")[1].split(":");

        for (int i = (dateSplit.length - 1); i >= 0; i--) {
            stringBuilder.append(dateSplit[i]);
            if (i != 0) {
                stringBuilder.append("/");
            } else {
                stringBuilder.append(" ");
            }
        }

        for (int i = 0; i < timeSplit.length - 1; i++) {
            stringBuilder.append(timeSplit[i]);
            if (i != 1) {
                stringBuilder.append(":");
            }
        }

        return stringBuilder.toString();

    }

}
