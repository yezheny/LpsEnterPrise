package com.yxcl.lpsenterprise.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/4/16 14:10
 */

public class NewMainFgAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> mTitles;

    public NewMainFgAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public NewMainFgAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> mTitles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.size() > 0 ? mTitles.get(position) : "";
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
