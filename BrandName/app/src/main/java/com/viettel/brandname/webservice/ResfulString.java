package com.viettel.brandname.webservice;

import android.net.Uri;

/**
 * Created by tulv2 on 5/16/2016.
 */
public class ResfulString {
    private StringBuilder baseUrl;

    public ResfulString(String baseUrl, boolean gen) {
        super();
        if (gen) {
            this.baseUrl = new StringBuilder(baseUrl).append("?");
        } else {
            this.baseUrl = new StringBuilder(baseUrl);
        }
    }

    public void AddParam(String key, Object value) {
        baseUrl.append(key + "=" + Uri.encode(value.toString().trim())).append("&");
    }


    public ResfulString(String baseUrl) {
        if (!baseUrl.endsWith("?")) {
            this.baseUrl = new StringBuilder(baseUrl).append("?");
        }
    }


    @Override
    public String toString() {
        String encode = baseUrl.toString().substring(0, baseUrl.length() - 1);
        return encode;
    }
}
