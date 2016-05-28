package com.viettel.brandname.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.viettel.brandname.R;
import com.viettel.brandname.activity.BaseSuperActivity;


public class IntroFragment extends BaseFragment implements View.OnClickListener {
    private View mView;
    private ImageView imgIntro;
    private TextView txtTitle, txtDescription;
    public static final String DRAWABLE_ID = "drawable_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    private Button btnStart;
    private static boolean isLastFrg = false;

    public static IntroFragment newInstance(int drawableId, String title, String description, boolean isLastFrg) {
        IntroFragment fragment = new IntroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DRAWABLE_ID, drawableId);
        bundle.putString(TITLE, title);
        bundle.putString(DESCRIPTION, description);
        fragment.setArguments(bundle);
        IntroFragment.isLastFrg = isLastFrg;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_splash, container, false);
        imgIntro = (ImageView) mView.findViewById(R.id.imgIntro);
        btnStart = (Button) mView.findViewById(R.id.btnStart);
        txtTitle = (TextView) mView.findViewById(R.id.txtTitle);
        txtDescription = (TextView) mView.findViewById(R.id.txtDes);
        btnStart.setOnClickListener(this);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isLastFrg) {
            btnStart.setVisibility(View.VISIBLE);
        } else {
            btnStart.setVisibility(View.GONE);
        }
        initData();
    }

    private void initData() {
        imgIntro.setImageDrawable(ContextCompat.getDrawable(mContext, getArguments().getInt(DRAWABLE_ID)));
        txtTitle.setText(getArguments().getString(TITLE));
        txtDescription.setText(getArguments().getString(DESCRIPTION));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                ((BaseSuperActivity) mContext).gotoHomePage();
                break;
        }
    }

}