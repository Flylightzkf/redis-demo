package com.liang.redisdemo.utils;

import java.util.regex.Pattern;

/**
 * @author kfz
 * @create 2023-01-16 14:32
 */
public class NumberPatter {

    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }
}
