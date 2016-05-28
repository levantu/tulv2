package com.viettel.brandname.utilities;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by tulv2 on 5/27/2016.
 */
public class KeyboardUtilities {

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
