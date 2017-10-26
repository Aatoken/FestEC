package com.mk.latte.util.str;

/**
 * @author lenovo
 * @data 2017/10/26
 */

public class StrUtils {

    public static boolean isEmpty(String str) {
        if (str == null || str.equals("")) {
            return true;
        }
        return false;
    }

}
