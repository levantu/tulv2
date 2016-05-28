package com.viettel.brandname.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tulv2 on 5/26/2016.
 */
public abstract class AbsModel implements Serializable {

    @SerializedName("errorCode")
    private String errorCode;

    @SerializedName("message")
    private String message;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
