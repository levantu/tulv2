package com.viettel.brandname.webservice;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tulv2 on 5/16/2016.
 */
public class GsonRequest<T> extends Request<T> {

    private Response.Listener<T> listener;
    private Class<T> cls;
    private String requestBody;
    private Gson mGson;

    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> params = new HashMap<String, String>();

    public GsonRequest(int method, String url, Class<T> cls, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        this.requestBody = requestBody;
        this.cls = cls;
        this.mGson = new Gson();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, Charset.forName("UTF-8"));
            T parsedGSON = mGson.fromJson(jsonString, cls);
            return Response.success(parsedGSON, HttpHeaderParser.parseCacheHeaders(response));
        } catch (JsonSyntaxException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null && response != null) {
            listener.onResponse(response);
        } else {
            Response.error(new VolleyError("NULL RESPONSE"));
        }
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        try {
            return requestBody == null ? super.getBody() : requestBody.getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, getParamsEncoding());
            Log.i("GsonRequest.getBody()", " get byte null");
            return null;
        } catch (AuthFailureError e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    public void setHeader(String title, Object content) {
        if (content == null) {
            params.put(title, "");
            return;
        }
        headers.put(title, content.toString());
    }

    public void setParams(String title, Object content) {
        if (content == null) {
            params.put(title, "");
            return;
        }
        params.put(title, content.toString());
        Log.i("GsonRequest.setParams()", "Key " + title + " = " + content);
    }
}
