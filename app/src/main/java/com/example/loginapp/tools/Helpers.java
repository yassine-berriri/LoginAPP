package com.example.loginapp.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helpers {

    public static boolean isValidEmail(String source) {
        boolean isValid = false;
        if (source!= null && !source.equals("")) {
            String emailRegularExpression  = "^([a-zA-Z_.0-9\\-]+)@([\\w\\-]+\\.)([\\w\\-]+)(\\.[\\w\\-]+)*$";
            CharSequence inputToCheckCharSequence = source.trim();
            Pattern regularExpressionPatter = Pattern.compile(emailRegularExpression);
            Matcher inputToCheckMatcher = regularExpressionPatter.matcher(inputToCheckCharSequence);
            if (inputToCheckMatcher.matches()) {
                isValid = true;
            }
        }
        return isValid;
    }

    //showSeconds
    public static String showSeconds(int seconds){
        int minute;
        int second;
        minute= seconds / 60;
        second = seconds%60;
            return (minute+":"+second);

    }
}
