package com.viettel.brandname.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.viettel.brandname.R;

/**
 * Created by tulv2 on 5/24/2016.
 */
public class AddCashActivity extends BaseSuperActivity implements View.OnClickListener {

    private EditText edtCardPassword, edtCardNumber;
    private Button btnOK;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        edtCardPassword = (EditText) findViewById(R.id.edit_card_password);
        edtCardNumber = (EditText) findViewById(R.id.edit_card_number);
        btnOK = (Button) findViewById(R.id.btn_ok);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnOK.setOnClickListener(this);
        initActionBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.anim_in_back, R.anim.anim_out_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                    // Xu ly nap the
                break;
            default:
                break;
        }
    }
}