package com.viettel.brandname;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;
import com.viettel.brandname.webservice.AbsRestful;

/**
 * Created by tulv2 on 5/16/2016.
 */
public class App extends Application {

    private static RequestQueue requestQueue;
    public static final String TAG = "VolleyPatterns";
    private static RetryPolicy policy = new DefaultRetryPolicy(AbsRestful.TIME_OUT, AbsRestful.MAX_NUMBER_TO_RETRY, 0.5f);

    private static App sInstance;

    public static synchronized App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            VolleyLog.DEBUG = true;
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        req.setRetryPolicy(policy);
        req.setShouldCache(true);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        addToRequestQueue(req, TAG);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
