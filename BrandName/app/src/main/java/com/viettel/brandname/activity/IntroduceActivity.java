package com.viettel.brandname.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viettel.brandname.R;
import com.viettel.brandname.adapter.IntroduceAdapter;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by tulv2 on 5/20/2016.
 */
public class IntroduceActivity extends BaseSuperActivity {
    private ViewPager mViewPager;
    private CirclePageIndicator mIndicator;
    private IntroduceAdapter mAdapter;
    private int[] drawableIds;
    private String[] titles;
    private String[] descriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        drawableIds = new int[]{R.drawable.img_intro1, R.drawable.img_intro2, R.drawable.img_intro3, R.drawable.img_intro4};

        titles = getResources().getStringArray(R.array.intro_titles);
        descriptions = getResources().getStringArray(R.array.intro_des);
        mAdapter = new IntroduceAdapter(getSupportFragmentManager(), drawableIds, titles, descriptions);
        mViewPager = (ViewPager) findViewById(R.id.pagerIntroduce);
        mViewPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.anim_in_back, R.anim.anim_out_back);
    }
}