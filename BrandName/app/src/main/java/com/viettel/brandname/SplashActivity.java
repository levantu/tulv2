package com.viettel.brandname;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.viettel.brandname.activity.BaseSuperActivity;
import com.viettel.brandname.model.LoginModel;
import com.viettel.brandname.utilities.NetWorkUtils;
import com.viettel.brandname.utilities.ToastUtils;
import com.viettel.brandname.webservice.WSRestAuthen;

/**
 * Created by tulv2 on 5/26/2016.
 */
public class SplashActivity extends BaseSuperActivity {

    private TextView txt1splash, txt2splash, txtDescription;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        txt1splash = (TextView) findViewById(R.id.splash_txt1);
        txt2splash = (TextView) findViewById(R.id.splash_txt2);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        Typeface boldRoboto = Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf");
        Typeface LightRoboto = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        txt1splash.setTypeface(boldRoboto);
        txt2splash.setTypeface(LightRoboto);
        txtDescription.setTypeface(LightRoboto);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                checkLogin();
            }
        }, 5000);
    }

    private void checkLogin() {
        if (NetWorkUtils.hasConnection(this)) {
            if (LoginModel.isLogin(this)) {
                doValidateToken(LoginModel.getSessionToken(this));
            } else {
                goToLogin();
            }
        } else {
            ToastUtils.makeText(this, getResources().getString(R.string.no_connection));
            goToLogin();
        }
    }

    private void doValidateToken(final String token) {
        WSRestAuthen rest = new WSRestAuthen(this);
        rest.doValidateToken(token, listener, errorListener);
    }

    private Response.Listener<LoginModel> listener = new Response.Listener<LoginModel>() {
        @Override
        public void onResponse(LoginModel response) {
            processValidateTokenResponse(response);
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            goToLogin();
        }
    };

    private void processValidateTokenResponse(LoginModel response) {
        try {
            if (response.getErrorCode().equals(Constants.OK)) {
                response.saveData(this);
                gotoHomePage();
            } else {
                goToLogin();
            }
        } catch (Exception e) {
            goToLogin();
        }
    }
}