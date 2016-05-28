package com.viettel.brandname.model;

import android.content.Context;

import com.google.gson.annotations.SerializedName;
import com.viettel.brandname.Constants;
import com.viettel.brandname.utilities.SharedPref;

/**
 * Created by tulv2 on 5/20/2016.
 */
public class LoginModel extends AbsModel {

    @SerializedName("token")
    private String token;

    @SerializedName("is_first_login")
    private int isFirstLogin;

    @SerializedName("userName")
    private String userName;

    //@SerializedName("number_of_names")
    private int numberOfNames;

    //@SerializedName("reg_name_fee")
    private String regNameFee;

    private String avatarUrl;

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsFirstLogin(int isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public void setNumberOfNames(int numberOfNames) {
        this.numberOfNames = numberOfNames;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRegNameFee(String regNameFee) {
        this.regNameFee = regNameFee;
    }


    public int getIsFirstLogin() {
        return isFirstLogin;
    }

    public int getNumberOfNames() {
        return numberOfNames;
    }

    public String getUserName() {
        return userName;
    }

    public String getRegNameFee() {
        return regNameFee;
    }

    public String getToken() {
        return token;
    }

    public void saveData(Context context) {
        SharedPref config = new SharedPref(context);
        config.putString(Constants.TOKEN, token);
        config.putInt(Constants.IS_FIRST_LOGIN, isFirstLogin);
        config.putString(Constants.PHONE_NUMBER, token);
        config.putInt(Constants.NUMBER_OF_NAMES, numberOfNames);
        config.putString(Constants.REG_NAME_FEE, regNameFee);
        config.putString(Constants.USERNAME, userName);
        config.putString(Constants.AVATAR, avatarUrl);
    }

    public void logout(Context context) {
        SharedPref config = new SharedPref(context);
        config.putString(Constants.TOKEN, "");
        config.putInt(Constants.IS_FIRST_LOGIN, 0);
        config.putString(Constants.PHONE_NUMBER, "");
        config.putInt(Constants.NUMBER_OF_NAMES, 0);
        config.putString(Constants.REG_NAME_FEE, "");
        config.putString(Constants.USERNAME, "");
        config.putString(Constants.AVATAR, "");
    }

    public static String getSessionToken(Context context) {
        String session = "";
        SharedPref config = new SharedPref(context);
        session = config.getString(Constants.TOKEN, "");
        return session;
    }

    public static boolean isLogin(Context context) {
        boolean flag = false;
        String session = getSessionToken(context);
        if (!"".equalsIgnoreCase(session)) {
            flag = true;
        }
        return flag;
    }

    public static boolean isShowIntro(Context context) {
        boolean flag = false;
        SharedPref config = new SharedPref(context);
        int isShow = config.getInt(Constants.IS_SHOW_INTRO, 0);
        if (isShow == 1) {
            flag = true;
        } else {
            updateShowIntro(context);
        }
        return flag;
    }

    private static void updateShowIntro(Context context) {
        SharedPref config = new SharedPref(context);
        config.putInt(Constants.IS_SHOW_INTRO, 1);
    }

    public static boolean isFirstLogin(Context context) {
        boolean flag = false;
        SharedPref config = new SharedPref(context);
        int firstLogin = config.getInt(Constants.IS_FIRST_LOGIN, 0);
        if (firstLogin == 0) {
            flag = true;
        }
        return flag;
    }

    public static String getUserName(Context context) {
        String name = "";
        SharedPref config = new SharedPref(context);
        name = config.getString(Constants.USERNAME, "");
        return name;
    }
}
