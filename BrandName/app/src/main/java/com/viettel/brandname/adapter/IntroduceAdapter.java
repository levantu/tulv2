package com.viettel.brandname.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.viettel.brandname.fragment.IntroFragment;

public class IntroduceAdapter extends FragmentStatePagerAdapter {
    private int[] drawableIds;
    private String[] titles;
    private String[] descriptions;

    public IntroduceAdapter(FragmentManager fm, int[] drawableIds, String[] titles, String[] description) {
        super(fm);
        this.drawableIds = drawableIds;
        this.titles = titles;
        this.descriptions = description;
    }

    @Override
    public Fragment getItem(int position) {
        IntroFragment fragment = null;
        if (position == 0) {
            fragment = IntroFragment.newInstance(drawableIds[0], titles[0], descriptions[0], false);
        } else if (position == 1) {
            fragment = IntroFragment.newInstance(drawableIds[1], titles[1], descriptions[1], false);
        } else if (position == 2) {
            fragment = IntroFragment.newInstance(drawableIds[2], titles[2], descriptions[2], false);
        } else {
            fragment = IntroFragment.newInstance(drawableIds[3], titles[3], descriptions[3], true);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
