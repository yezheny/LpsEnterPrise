package com.yxcl.lpsenterprise.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.base.BaseFragment;
import com.yxcl.lpsenterprise.model.VideoBean;
import com.yxcl.lpsenterprise.presenter.FgVideoPresenter;
import com.yxcl.lpsenterprise.presenter.IFgVideoPresenter;

import java.util.List;

/**
 * class from 视频
 * Created by zqf
 * Time 2018/4/13 13:37
 */

public class FgVideo extends BaseFragment<FgVideoPresenter> implements IFgVideoPresenter {


    @Override
    protected FgVideoPresenter createPresenter() {
        return new FgVideoPresenter(this);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fg_video_layout, null);
    }

    @Override
    protected void ChildRequestServiceData() {
        refresh_success_gone();
    }

    @Override
    public void getVideoData(List<VideoBean> mList) {

    }

    @Override
    public void error() {

    }
}
