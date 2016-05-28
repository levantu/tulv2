package com.viettel.brandname.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;

import com.viettel.brandname.R;

/**
 * Created by tulv2 on 5/27/2016.
 */
public class DialogYesNo extends Dialog implements View.OnClickListener {

    private Button btnYes, btnNo;

    public DialogYesNo(@NonNull Context context, @StyleRes int themeResId, OnInitDialogYesNo onClickYesNo) {
        super(context, themeResId);
        init(onClickYesNo);
    }


    public DialogYesNo(Context context, OnInitDialogYesNo onClickYesNo) {
        super(context);
        init(onClickYesNo);
    }

    private void init(OnInitDialogYesNo onClickYesNo) {
        this.onClickYesNo = onClickYesNo;
        setContentView(R.layout.dialog_yesno);
        btnYes = (Button) findViewById(R.id.btn_yes);
        btnNo = (Button) findViewById(R.id.btn_no);
        btnNo.setOnClickListener(this);
        btnYes.setOnClickListener(this);
        onClickYesNo.onSetBtnNoText(btnNo);
        onClickYesNo.onSetBtnYesText(btnYes);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_no:
                if (onClickYesNo != null) {
                    onClickYesNo.onClickNo(v);
                    dismiss();
                }
                break;
            case R.id.btn_yes:
                if (onClickYesNo != null) {
                    onClickYesNo.onClickYes(v);
                    dismiss();
                }
                break;

        }
    }

    private OnInitDialogYesNo onClickYesNo;

    public interface OnInitDialogYesNo {
        public void onClickYes(View v);

        public void onClickNo(View v);

        public void onSetBtnYesText(Button button);

        public void onSetBtnNoText(Button button);
    }
}
