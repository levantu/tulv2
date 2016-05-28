package com.viettel.brandname.utilities;

/**
 * Created by tulv2 on 5/19/2016.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        boolean flag = false;
        if (str == null || str.isEmpty()) {
            flag = true;
        }
        return flag;
    }

    public static boolean isEmptyTrim(String str) {
        return isEmpty(str.trim());
    }
}
