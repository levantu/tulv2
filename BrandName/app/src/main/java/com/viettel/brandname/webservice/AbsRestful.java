package com.viettel.brandname.webservice;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.viettel.brandname.App;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by tulv2 on 5/16/2016.
 */
public class AbsRestful {

    public static final int TIME_OUT = 20 * 1000;
    public static final int MAX_NUMBER_TO_RETRY = 2;

//    public static String ROOT_URL = "http://10.30.173.24:8080/smsbrandname_restful/SmsBrandNameAPI/";
public static String ROOT_URL = "http://203.190.170.41:9669/";

    // phan xac thuc
    public static final String VALIDATE_TOKEN = ROOT_URL + "validateToken";
    public static final String GET_SIGNIN = ROOT_URL + "getSignin";

    public static final String GET_BRAND = ROOT_URL + "getBrands";
    public static final String GET_GROUP = ROOT_URL + "getGroups";
    public static final String GET_SEND_MESSAGE = ROOT_URL + "sendMessage";


    public <T> void addReq(Request<T> req) {
        App.getInstance().addToRequestQueue(req);
        try {
            Log.i("AbsRestful.addReq()", "request: --> url: " + req.getUrl());
            HashMap<String, String> map = (HashMap<String, String>) req.getHeaders();
            Set<String> keys = map.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String keyMap = iterator.next();
                Log.i("AbsRestful.addReq()", "header: " + keyMap + "->" + map.get(keyMap));
            }
        } catch (AuthFailureError e) {
            e.printStackTrace();
        }
    }


    public <T> void addReq(Request<T> req, String TAG) {
        App.getInstance().addToRequestQueue(req, TAG);
    }
}
