package com.viettel.brandname.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.viettel.brandname.Constants;
import com.viettel.brandname.MainActivity;
import com.viettel.brandname.R;
import com.viettel.brandname.SplashActivity;
import com.viettel.brandname.adapter.SuggestInputAdapter;
import com.viettel.brandname.control.ScrollViewWithMaxHeight;
import com.viettel.brandname.dialog.DialogSimple;
import com.viettel.brandname.dialog.DialogYesNo;
import com.viettel.brandname.model.BrandModel;
import com.viettel.brandname.model.GroupModel;
import com.viettel.brandname.model.MessageModel;
import com.viettel.brandname.model.SimpleModel;
import com.viettel.brandname.model.SuggestInputModel;
import com.viettel.brandname.utilities.NetWorkUtils;
import com.viettel.brandname.utilities.StringUtils;
import com.viettel.brandname.utilities.ToastUtils;
import com.viettel.brandname.webservice.WSRestFull;
import com.viettel.brandname.webservice.paser.BrandPaser;
import com.viettel.brandname.webservice.paser.GroupPaser;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

/**
 * Created by tulv2 on 5/25/2016.
 */
public class SendSMSActivity extends BaseSuperActivity implements View.OnClickListener, View.OnFocusChangeListener, AdapterView.OnItemClickListener {

    private Spinner spinnerBrand;
    private String[] myNames = null;
    private String selectedBrand = null;
    private ArrayList<BrandModel> listBrands;
    private ArrayList<GroupModel> listGroups;
    private MessageModel tmpMessage;

    private ArrayList<SuggestInputModel> mSuggestInputs;
    private ArrayList<SuggestInputModel> mSelectedInputs;
    private SuggestInputAdapter mAdapter;

    private ProgressBar pgLoading;
    private TextView txtError;
    private ScrollView layoutContent;

    private FlowLayout topLayout;
    private AppCompatAutoCompleteTextView txtEnterGroup;
    private int pxAs6dp, pxAs2dp, pxAs100dp;
    private ScrollViewWithMaxHeight scrollTopLayout;
    private EditText edSMS;
    private ImageButton btnSendSMS;
    private ImageView imgAddContact;
    private String messContent;

    private TextView tvChangeNumber;
    private TextWatcher textWatcher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        pgLoading = (ProgressBar) findViewById(R.id.pgLoading);
        txtError = (TextView) findViewById(R.id.txtError);
        txtError.setVisibility(View.GONE);
        layoutContent = (ScrollView) findViewById(R.id.layoutContent);
        txtEnterGroup = (AppCompatAutoCompleteTextView) findViewById(R.id.txtEnterName);
        btnSendSMS = (ImageButton) findViewById(R.id.btnSendSMS);
        topLayout = (FlowLayout) findViewById(R.id.flowLayout);
        topLayout.setGravity(Gravity.LEFT | Gravity.TOP);
        scrollTopLayout = (ScrollViewWithMaxHeight) findViewById(R.id.scrollFlowLayout);
        imgAddContact = (ImageView) findViewById(R.id.imgAddContact);
        tvChangeNumber = (TextView) findViewById(R.id.txt_num_chater);

        edSMS = (EditText) findViewById(R.id.edit_sms);
        txtEnterGroup.setThreshold(1);
        initActionBar();
        setTitle(getResources().getString(R.string.send_sms_title));
        initObject();
        getGroups();
        getBrands();
        imgAddContact.setOnClickListener(this);
        btnSendSMS.setOnClickListener(this);
        edSMS.setOnFocusChangeListener(this);
        txtError.setOnClickListener(this);
        txtEnterGroup.setOnItemClickListener(this);
        edSMS.addTextChangedListener(textWatcher);
    }


    private void initObject() {
        pxAs6dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 6, this.getResources().getDisplayMetrics());
        pxAs2dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, this.getResources().getDisplayMetrics());
        pxAs100dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, this.getResources().getDisplayMetrics());

        scrollTopLayout.setMaxHeight(pxAs100dp);
        listGroups = new ArrayList<>();
        listBrands = new ArrayList<>();
        mSuggestInputs = new ArrayList<SuggestInputModel>();
        mAdapter = new SuggestInputAdapter(this, mSuggestInputs);

        mSelectedInputs = new ArrayList<SuggestInputModel>();
        txtEnterGroup.setAdapter(mAdapter);
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvChangeNumber.setText(s.length() + "/1000");
                messContent = edSMS.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        spinnerBrand = (Spinner) findViewById(R.id.spinner_toolbar);
        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selectedBrand = myNames[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        final DialogYesNo dialogYesNo = new DialogYesNo(this, new DialogYesNo.OnInitDialogYesNo() {
            @Override
            public void onClickYes(View v) {
                ToastUtils.makeText(SendSMSActivity.this, "Yes yes");
                SendSMSActivity.this.overridePendingTransition(R.anim.anim_in_back, R.anim.anim_out_back);
                SendSMSActivity.this.finish();
            }

            @Override
            public void onClickNo(View v) {
                ToastUtils.makeText(SendSMSActivity.this, "No no");
            }

            @Override
            public void onSetBtnYesText(Button button) {
                button.setText("Yes1");
            }

            @Override
            public void onSetBtnNoText(Button button) {
                button.setText("No1");
            }
        });
        dialogYesNo.show();
    }

    private void getGroups() {
        if (NetWorkUtils.hasConnection(this)) {
            WSRestFull rest = new WSRestFull(this);
            rest.getGroups(listener, errorListener);
        } else {
            displayDataError(Constants.NOT_CONNECTED, false);
        }
    }

    private Response.Listener<GroupPaser> listener = new Response.Listener<GroupPaser>() {
        @Override
        public void onResponse(GroupPaser response) {
            if (response.getErrorCode().equals(Constants.OK)) {
                listGroups.addAll(response.getData());
                setDataGroups();
            } else {
                displayDataError(response.getErrorCode(), false);
            }
        }
    };

    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            displayDataError(error.getMessage(), false);
        }
    };


    private void setDataGroups() {
        for (GroupModel tmpGroup : listGroups) {
            int tmpGroupSize = tmpGroup.getPhones().split(",").length;
            mSuggestInputs.add(new SuggestInputModel(tmpGroup.getGroupName(), Integer.toString(tmpGroupSize)));
        }

        mAdapter.updateItem(mSuggestInputs);
        pgLoading.setVisibility(View.GONE);
        layoutContent.setVisibility(View.VISIBLE);
    }

    private void setDataBrands() {
        myNames = new String[listBrands.size()];
        for (int i = 0; i < listBrands.size(); i++) {
            myNames[i] = listBrands.get(i).getBrandName();
        }
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_brand_item, myNames);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_brand_item_dropdown);
        spinnerBrand.setAdapter(spinnerArrayAdapter);

        String selectedBrandId = getIntent().getStringExtra(Constants.BRAND_ID);
        // Lay vi tri thuong hieu duoc lua chon
        if (selectedBrandId != null) {// neu chuyen tu man hinh danh sach thuong hieu
            for (int i = 0; i < listBrands.size(); i++) {
                if (listBrands.get(i).getBrandId().equals(selectedBrandId)) {
                    spinnerBrand.setSelection(i);
                }
            }
        } else {
            if (tmpMessage != null)// neu chuyen tu man hinh sua tin nhan cho gui
            {
                String tmpBrand = tmpMessage.getBrandName();
                for (int i = 0; i < listBrands.size(); i++) {
                    if (listBrands.get(i).getBrandName().equals(tmpBrand)) {
                        spinnerBrand.setSelection(i);
                    }
                }
            }
        }
        spinnerBrand.setVisibility(View.VISIBLE);
    }

    private void addLayoutToTop(final SuggestInputModel selectedItem) {
        final TextView txtGroupName = new TextView(SendSMSActivity.this);
        FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        params.setMargins(pxAs6dp, 0, pxAs6dp, pxAs6dp);
        txtGroupName.setLayoutParams(params);
        txtGroupName.setTextSize(14);
        txtGroupName.setPadding(pxAs6dp, pxAs2dp, pxAs6dp, pxAs2dp);
        txtGroupName.setGravity(Gravity.CENTER_VERTICAL);
        txtGroupName.setCompoundDrawablePadding(pxAs2dp);
        txtGroupName.setText(selectedItem.getName());
        txtGroupName.setTextColor(getResources().getColor(android.R.color.black));
        txtGroupName.setBackgroundResource(R.drawable.bg_suggest_input);
        txtGroupName.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ico_group, 0, R.drawable.ico_delete, 0);
        txtGroupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topLayout.removeView(view);
                removeSuggestedInput(mSelectedInputs, selectedItem);
            }
        });
        topLayout.addView(txtGroupName);
        txtEnterGroup.setText("");
    }

    // Lay xoa dia chi gui trong danh sach dia chi
    private void removeSuggestedInput(ArrayList<SuggestInputModel> selectedInputs, SuggestInputModel item) {
        for (int i = 0; i < selectedInputs.size(); i++) {
            SuggestInputModel tmpItem = selectedInputs.get(i);
            if (tmpItem.getName().equals(item.getName())) {
                selectedInputs.remove(i);
            }
        }
    }

    private void getBrands() {
        if (NetWorkUtils.hasConnection(this)) {
            WSRestFull rest = new WSRestFull(this);
            rest.getBrands(listenerBrands, errorListenerBrands);
        } else {
            displayDataError(Constants.NOT_CONNECTED, true);
        }
    }

    private Response.Listener<BrandPaser> listenerBrands = new Response.Listener<BrandPaser>() {
        @Override
        public void onResponse(BrandPaser response) {
            if (response.getErrorCode().equals(Constants.OK)) {
                listBrands.addAll(response.getData());
                setDataBrands();
            } else {
                displayDataError(response.getErrorCode(), true);
            }
        }
    };

    private Response.ErrorListener errorListenerBrands = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            displayDataError(error.getMessage(), true);
        }
    };

    private void displayDataError(String type, boolean isBrandLoad) {
        if (Constants.NOT_CONNECTED.equalsIgnoreCase(type)) {
            txtError.setText(getResources().getString(R.string.no_connection_has_retry));
        } else if (Constants.NO_DATA.equalsIgnoreCase(type)) {
            if (isBrandLoad) {
                txtError.setText(getResources().getString(R.string.send_sms_no_brands));
            } else {
                txtError.setText(getResources().getString(R.string.send_sms_pending_brands));
            }
        } else if (Constants.UNAUTHENTICATED.equalsIgnoreCase(type)) {
            txtError.setText(getResources().getString(R.string.un_authenticated_has_retry));
        } else {
            txtError.setText(getResources().getString(R.string.error_has_retry));
        }
        txtError.setVisibility(View.VISIBLE);
        pgLoading.setVisibility(View.GONE);
        layoutContent.setVisibility(View.GONE);
        spinnerBrand.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgAddContact:
                addContact();
                break;
            case R.id.btnSendSMS:
                sendSms();
                break;
            case R.id.txtError:

                break;
        }
    }

    private void addContact() {
        String txtInput = txtEnterGroup.getText().toString().trim();
        if (!StringUtils.isEmpty(txtInput)) {
            SuggestInputModel selectedItem = new SuggestInputModel(txtInput, txtInput);
            if (!mSelectedInputs.contains(selectedItem)) {
                addLayoutToTop(selectedItem);
                mSelectedInputs.add(selectedItem);
            } else {
                txtEnterGroup.setText("");
                ToastUtils.makeText(SendSMSActivity.this, SendSMSActivity.this.getResources().getString(R.string.send_sms_receiver_duplicated));
            }
        }
    }

    private void sendSms() {
        if (!StringUtils.isEmptyTrim(selectedBrand)) {
            String groups = getGroupsFromSuggestInput(mSelectedInputs);
            if (StringUtils.isEmptyTrim(groups)) {
                ToastUtils.makeText(SendSMSActivity.this, SendSMSActivity.this.getResources().getString(R.string.send_sms_error_receiver));
            } else {
                if (messContent == null || messContent.equals("")) {
                    ToastUtils.makeText(SendSMSActivity.this, SendSMSActivity.this.getResources().getString(R.string.send_sms_error_content));
                } else {
                    if (NetWorkUtils.hasConnection(SendSMSActivity.this)) {
                        doSendMessage(groups, selectedBrand, messContent);
                    } else {
                        ToastUtils.makeText(SendSMSActivity.this, SendSMSActivity.this.getResources().getString(R.string.no_connection));
                    }
                }
            }
        } else {
            ToastUtils.makeText(SendSMSActivity.this, SendSMSActivity.this.getResources().getString(R.string.send_sms_error_my_name));
        }
    }

    //// TODO: Do send message
    private void doSendMessage(String groups, String selectedBrand, String messContent) {
        if (NetWorkUtils.hasConnection(this)) {
            WSRestFull rest = new WSRestFull(this);
            rest.sendSms(groups, selectedBrand, messContent, listenerSendMessage, errorListenerSendMessage);
            showProgressDialog("");
        } else {
            ToastUtils.makeText(this, getResources().getString(R.string.no_connection));
        }
    }

    private Response.Listener<SimpleModel> listenerSendMessage = new Response.Listener<SimpleModel>() {
        @Override
        public void onResponse(SimpleModel response) {
            processSendMessageResponse(response);
        }
    };

    private Response.ErrorListener errorListenerSendMessage = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            processSendMessageResponse(null);
        }
    };

    private void processSendMessageResponse(SimpleModel response) {
        hideProgressDialog();
        try {
            if (response != null) {
                if (response.getErrorCode().equalsIgnoreCase(Constants.OK)) {
                    ToastUtils.makeText(this, response.getMessage());
                    finish();
                    this.overridePendingTransition(R.anim.anim_in_back, R.anim.anim_out_back);
                } else if (response.getErrorCode().equals(Constants.UNAUTHENTICATED)) {
                    showDialogUnauthenticated(this, this.getResources().getString(R.string.un_authenticated));
                } else {
                    ToastUtils.makeText(this, response.getMessage());
                }
            } else {
                ToastUtils.makeText(this, this.getResources().getString(R.string.error));
            }
        } catch (Exception e) {
            ToastUtils.makeText(this, this.getResources().getString(R.string.error));
        }
    }

    public void showDialogUnauthenticated(final Context mContext, String message) {
        final DialogSimple alert = new DialogSimple(mContext, message);
        alert.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
                Activity activity = (Activity) mContext;
                if (activity instanceof MainActivity) {
                    Intent intent = new Intent(mContext, SplashActivity.class);
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                } else {
                    Intent i = new Intent(activity, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.putExtra("EXIT", true);
                    mContext.startActivity(i);
                }
            }
        });
        alert.show();
    }

    private String getGroupsFromSuggestInput(ArrayList<SuggestInputModel> selectedInput) {
        String result = "";
        ArrayList<SuggestInputModel> selectedGroups = new ArrayList<SuggestInputModel>();
        for (SuggestInputModel tmpSuggestInput : selectedInput) {
            selectedGroups.add(tmpSuggestInput);
        }
        result = convertArrayToString(selectedGroups);
        return result;
    }

    private String convertArrayToString(ArrayList<SuggestInputModel> suggestInputs) {
        String result = "";
        for (int i = 0; i < suggestInputs.size(); i++) {
            if (i == suggestInputs.size() - 1) {
                result += suggestInputs.get(i).getName();
            } else {
                result += suggestInputs.get(i).getName() + ",";
            }
        }
        return result;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            tvChangeNumber.setText(edSMS.getText().length() + "/1000");
            messContent = edSMS.getText().toString();
        } else {
            if (edSMS.getText().toString().equals("")) {
                tvChangeNumber.setText("");
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SuggestInputModel selectedItem = mSuggestInputs.get(position);
        if (!mSelectedInputs.contains(selectedItem)) {
            addLayoutToTop(selectedItem);
            mSelectedInputs.add(selectedItem);
        } else {
            txtEnterGroup.setText("");
            ToastUtils.makeText(SendSMSActivity.this, SendSMSActivity.this.getResources().getString(R.string.send_sms_receiver_duplicated));
        }
    }
}
