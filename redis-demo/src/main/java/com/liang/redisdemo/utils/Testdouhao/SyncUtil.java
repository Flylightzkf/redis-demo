package com.liang.redisdemo.utils.Testdouhao;


public class SyncUtil {

    public SyncUtil() {
    }
    public static String trim(String value) {
        return value == null ? null : value.trim();
    }

    public static boolean isEmpty(String value) {
        return value == null || value.trim().length() == 0;
    }

    public static boolean isEquals(String str1, String str2) {
        if (isEmpty(str1)) {
            return isEmpty(str2);
        } else {
            return str1.equals(str2);
        }
    }
}