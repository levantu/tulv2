package com.viettel.brandname.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.viettel.brandname.R;

public class DialogSimple extends Dialog {

    private String des;
    private android.view.View.OnClickListener okLis;
    private Context context;

    public void setClickListener(android.view.View.OnClickListener okLis) {
        this.okLis = okLis;
    }

    public DialogSimple(Context context, String des) {
        super(context);
        this.context = context;
        this.des = des;
    }

    public DialogSimple(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // No Window Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCancelable(false);
        // Set Layout
        this.setContentView(R.layout.dialog_simple);

        TextView title = (TextView) findViewById(R.id.popup_alert_title);
        title.setText(this.context.getResources().getString(R.string.alert));

        TextView text = (TextView) findViewById(R.id.popup_text);
        text.setText(des);

        Button yes = (Button) findViewById(R.id.popup_alert_button);
        yes.setOnClickListener(okLis);
        yes.setText("OK");
    }
}