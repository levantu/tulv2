package com.viettel.brandname.webservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.viettel.brandname.model.LoginModel;

/**
 * Created by tulv2 on 5/16/2016.
 */
public class WSRestAuthen extends AbsRestful {

    private Context context;

    public WSRestAuthen(Context context) {
        this.context = context;
    }

    public void doLogin(String userName, String password, Response.Listener<LoginModel> listener, Response.ErrorListener errorListener) {
        ResfulString params = new ResfulString(GET_SIGNIN);
        GsonRequest<LoginModel> req = new GsonRequest<>(Request.Method.POST, params.toString(), LoginModel.class, null, listener, errorListener);
        req.setParams("user_name", userName);
        req.setParams("password", password);
        addReq(req);
    }

    public void doValidateToken(String token, Response.Listener<LoginModel> listener, Response.ErrorListener errorListener) {
        ResfulString params = new ResfulString(VALIDATE_TOKEN);
        GsonRequest<LoginModel> req = new GsonRequest<>(Request.Method.POST, params.toString(), LoginModel.class, null, listener, errorListener);
        req.setParams("token", LoginModel.getSessionToken(context));
        addReq(req);
    }
}
