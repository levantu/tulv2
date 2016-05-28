package com.viettel.brandname.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.viettel.brandname.utilities.SharedPref;

/**
 * Created by tulv2 on 5/20/2016.
 */
public class BaseFragment extends Fragment {

    protected Context mContext;
    protected SharedPref sharedPref;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        sharedPref = new SharedPref(context);
    }
}
