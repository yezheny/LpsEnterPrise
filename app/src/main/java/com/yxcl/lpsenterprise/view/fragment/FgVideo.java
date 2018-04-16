package com.yxcl.lpsenterprise.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.base.BaseFragment;
import com.yxcl.lpsenterprise.presenter.FgMonitorPresenter;
import com.yxcl.lpsenterprise.presenter.IFgMonitorPresenter;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:37
 */

public class FgVideo extends BaseFragment<FgMonitorPresenter> implements IFgMonitorPresenter {
    @Override
    protected FgMonitorPresenter createPresenter() {
        return new FgMonitorPresenter(this);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_video_layout, null);
    }

    @Override
    protected void ChildRequestServiceData() {

    }
}
