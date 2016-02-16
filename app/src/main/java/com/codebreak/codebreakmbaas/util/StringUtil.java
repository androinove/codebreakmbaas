package com.codebreak.codebreakmbaas.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by PedroFelipe on 14/11/2015.
 */
public class StringUtil {

    /**
     * Este método gera uma String randomica
     * Utilize em todas as requisições para webservices para evitar receber dados desatualizados
     * devido a armazenamento em memória cache
     *
     * @return String randomica
     */
    public static String geraRandom() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        return myRandom.substring(0, 20);
    }

    /**
     * Este método verifica se uma string é um email
     * @param s string que será validada
     * @return se verdadeiro, email é válido
     */
    public static boolean validEmail(String s) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        return Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE).matcher(s).matches();
    }

    public static boolean isAValidJSONString(String s) {
        try {
            // Try to convert the String to a JSONObject
            new JSONObject(s);
        } catch (JSONException jsonObjectException) {
            // If it is not a JSONObject...
            try {
                // Try to convert the string to a JSONArray
                new JSONArray(s);
            } catch (JSONException jsonArrayException) {
                // If it is not a JSONArray return false
                return false;
            }
        }

        return true;
    }

    public static String capitalize(String str, char[] delimiters) {
        int delimLen = (delimiters == null ? -1 : delimiters.length);
        if (str == null || str.length() == 0 || delimLen == 0) {
            return str;
        }
        int strLen = str.length();
        StringBuffer buffer = new StringBuffer(strLen);
        boolean capitalizeNext = true;
        for (int i = 0; i < strLen; i++) {
            char ch = str.charAt(i);

            if (isDelimiter(ch, delimiters)) {
                buffer.append(ch);
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer.append(Character.toTitleCase(ch));
                capitalizeNext = false;
            } else {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (int i = 0, isize = delimiters.length; i < isize; i++) {
            if (ch == delimiters[i]) {
                return true;
            }
        }
        return false;
    }

}
