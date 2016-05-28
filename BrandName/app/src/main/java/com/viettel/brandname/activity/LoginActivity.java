package com.viettel.brandname.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.viettel.brandname.Constants;
import com.viettel.brandname.R;
import com.viettel.brandname.model.LoginModel;
import com.viettel.brandname.utilities.NetWorkUtils;
import com.viettel.brandname.utilities.StringUtils;
import com.viettel.brandname.utilities.ToastUtils;
import com.viettel.brandname.webservice.WSRestAuthen;

/**
 * Created by tulv2 on 5/19/2016.
 */
public class LoginActivity extends BaseSuperActivity implements View.OnClickListener {

    private EditText editUserName;
    private EditText editPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUserName = (EditText) findViewById(R.id.edit_user_name);
        editPassword = (EditText) findViewById(R.id.edit_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                validateLogin(editUserName.getText().toString(), editPassword.getText().toString());
                break;
        }
    }

    private void validateLogin(String phoneNumber, String password) {
        if (!StringUtils.isEmptyTrim(phoneNumber) && !StringUtils.isEmptyTrim(password)) {
            if (NetWorkUtils.hasConnection(this)) {
                doLogin(phoneNumber.trim(), password.trim());
            } else {
                ToastUtils.makeText(this, getResources().getString(R.string.no_connection));
            }
        } else {
            ToastUtils.makeText(this, getResources().getString(R.string.login_empty_error));
        }
    }

    private void doLogin(String userName, String passWord) {
        showProgressDialog(getResources().getString(R.string.please_wait));
        WSRestAuthen rest = new WSRestAuthen(this);
        rest.doLogin(userName, passWord, listener, errorListener);
    }

    private Response.Listener<LoginModel> listener = new Response.Listener<LoginModel>() {
        @Override
        public void onResponse(LoginModel response) {
            hideProgressDialog();
            if (response.getErrorCode().equalsIgnoreCase(Constants.OK)) {
                response.saveData(LoginActivity.this);
                ToastUtils.makeText(LoginActivity.this, response.getMessage());
                progressLoginBusiness();
            } else {
                ToastUtils.makeText(LoginActivity.this, getResources().getString(R.string.error));
            }
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            hideProgressDialog();
            ToastUtils.makeText(LoginActivity.this, getResources().getString(R.string.error));
        }
    };

    private void progressLoginBusiness() {
        if (!LoginModel.isShowIntro(this)) {
            gotoIntroductionPage();
        } else {
            gotoHomePage();
        }
    }
}