package com.viettel.brandname.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.viettel.brandname.MainActivity;
import com.viettel.brandname.R;
import com.viettel.brandname.utilities.SharedPref;
import com.viettel.brandname.utilities.StringUtils;

/**
 * Created by tulv2 on 5/18/2016.
 */
public abstract class BaseSuperActivity extends AppCompatActivity {
    protected SharedPref sharedPref;
    private ProgressDialog progressDialog;
    protected ImageView btnBack;
    private TextView title;
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPref = new SharedPref(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    protected void showProgressDialog(String message) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        if (StringUtils.isEmptyTrim(message)) {
            message = getResources().getString(R.string.loading);
        }
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    protected void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    protected void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    protected void setTitleBar(String title) {
        if (StringUtils.isEmpty(title)) {
            title = getResources().getString(R.string.app_name);
        }
        if (this.title != null) {
            this.title.setText(title);
        } else {
            getSupportActionBar().setTitle(title);
        }
    }

    //    private BaseFragment abs = null;
//    private int currentTab;
//    private ArrayList<Integer> tabTrackID = new ArrayList<Integer>();
//    private SparseArray<String> tabTrackTitle = new SparseArray<String>();
//    private SparseArray<BaseFragment> maps = new SparseArray<BaseFragment>();
//
//    public void goNextTab(String message, int tabid, Bundle args) {
//        if ((tabTrackID.size() > 0 && tabid != (tabTrackID.get(tabTrackID.size() - 1))) || tabTrackID.size() <= 0) {
//            if (tabTrackID.get(tabid) != null) {
//                abs = maps.get(tabid);
//            } else {
//                switch (tabid) {
//                    default:
//                        break;
//                }
//            }
//        }
//
//        if ((abs != null && currentTab != tabid)) {
//            try {
//                abs.setArguments(args);
//                replaceFragment(abs, true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            if (tabTrackID.size() > 0) {
//                if (!tabTrackID.get(tabTrackID.size() - 1).equals(tabid)) {
//                    tabTrackID.add(tabid);
//                }
//            } else {
//                tabTrackID.add(tabid);
//            }
//            setupNavication();
//            tabTrackTitle.put(tabid, message);
//        }
//        currentTab = tabid;
//    }
//
//    protected void replaceFragment(BaseFragment fragment, boolean slideToLeft) {
//        try {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.setCustomAnimations(R.anim.anim_in, R.anim.anim_in_back, R.anim.anim_out, R.anim.anim_out_back);
//            transaction.replace(R.id.content_main, fragment);
//            transaction.commit();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    protected void setupNavication() {
//
//        if (toolbar != null) {
//            toolbar.setVisibility(View.VISIBLE);
//            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
//            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    goPrevTab();
//                }
//            });
//
//        }
//    }
//
//    protected void goPrevTab() {
//        if (tabTrackID.size() > 1) {
//            int index = tabTrackID.size() - 1;
//            tabTrackID.remove(index--);
//            abs = maps.get(tabTrackID.get(index));
//            Bundle bundle = abs.getArguments();
//            String title = getString(R.string.app_name);
//            //Integer integ = -1;
//            try {
//                title = tabTrackTitle.get(tabTrackID.get(index));
//                //integ = tabTrackInteger.get(tabTrackID.get(index));
//                //if (integ == null) {
//                //   integ = tabTrackID.get(index);
//                //}
//            } catch (Exception e) {
//                title = "";
//                //integ = 0;
//            }
//            goNextTab(title, tabTrackID.get(index), bundle);
//        } else {
//            exitApp();
//        }
//    }
//
//    private boolean isTouchTwoTimes = false;
//    private static final int COUNT_DONW = 2000;
//
//    private void exitApp() {
//        if (!isTouchTwoTimes) {
//            //ToastUtil.makeText(BaseActivity.this, BaseActivity.this.getString(R.string.exit_ask));
//            isTouchTwoTimes = true;
//            new CountDownTimer(COUNT_DONW, COUNT_DONW) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                }
//
//                @Override
//                public void onFinish() {
//                    isTouchTwoTimes = false;
//                }
//            }.start();
//            return;
//        }
//        try {
//            long triggerSize = 10000000; // starts cleaning when cache size is
//            // larger than 3M
//            long targetSize = 6000000; // remove the least recently used files
//            // until cache size is less than 2M
//            File ext = Environment.getExternalStorageDirectory();
//            //File cacheDir = new File(ext, Constants.ROOT_FOLDER + "/" + Constants.CACHE_FOLDER);
//            // AQUtility.cleanCache(cacheDir, triggerSize, targetSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        finish();
//        // System.runFinalization();
//        System.exit(0);
//    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }

    public void gotoHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }

    public void gotoIntroductionPage() {
        Intent intent = new Intent(this, IntroduceActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToSendSMS() {
        Intent sendSMSIntent = new Intent(this, SendSMSActivity.class);
        startActivity(sendSMSIntent);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }

    public void goToAddCash() {
        Intent sendSMSIntent = new Intent(this, AddCashActivity.class);
        startActivity(sendSMSIntent);
        overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.anim_in_back, R.anim.anim_out_back);
    }
}