package com.viettel.brandname.webservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.viettel.brandname.Constants;
import com.viettel.brandname.model.LoginModel;
import com.viettel.brandname.model.SimpleModel;
import com.viettel.brandname.webservice.paser.BrandPaser;
import com.viettel.brandname.webservice.paser.GroupPaser;

/**
 * Created by tulv2 on 5/26/2016.
 */
public class WSRestFull extends AbsRestful {

    private Context context;

    public WSRestFull(Context context) {
        this.context = context;
    }

    public void getBrands(Response.Listener<BrandPaser> listener, Response.ErrorListener errorListener) {
        ResfulString params = new ResfulString(GET_BRAND);
        GsonRequest<BrandPaser> req = new GsonRequest<BrandPaser>(Request.Method.POST, params.toString(), BrandPaser.class, null, listener, errorListener);
        req.setParams("token", LoginModel.getSessionToken(context));
        addReq(req);
    }

    public void getGroups(Response.Listener<GroupPaser> listener, Response.ErrorListener errorListener) {
        ResfulString params = new ResfulString(GET_GROUP);
        GsonRequest<GroupPaser> req = new GsonRequest<GroupPaser>(Request.Method.POST, params.toString(), GroupPaser.class, null, listener, errorListener);
        req.setParams("token", LoginModel.getSessionToken(context));
        addReq(req);
    }

    public void sendSms(String groups, String brandName, String message, Response.Listener<SimpleModel> listener, Response.ErrorListener errorListener) {
        ResfulString params = new ResfulString(GET_SEND_MESSAGE);
        GsonRequest<SimpleModel> req = new GsonRequest<>(Request.Method.POST, params.toString(), SimpleModel.class, null, listener, errorListener);
        req.setParams(Constants.TOKEN, LoginModel.getSessionToken(context));
        req.setParams(Constants.GROUPS, groups);
        req.setParams(Constants.BRAND_NAME, brandName);
        req.setParams(Constants.MESSAGE, message);
        req.setParams(Constants.PHONE, "");
        req.setParams(Constants.SET_TIME, 0);
        req.setParams(Constants.TIME, "");
        addReq(req);
    }
}
