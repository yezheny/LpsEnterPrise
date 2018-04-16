package com.yxcl.lpsenterprise.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.base.BaseFragment;
import com.yxcl.lpsenterprise.presenter.FgMonitorPresenter;
import com.yxcl.lpsenterprise.presenter.IFgMonitorPresenter;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:37
 */

public class FgTrajectory extends BaseFragment<FgMonitorPresenter> implements IFgMonitorPresenter {
    View FgTrajectoryView;
    private MapView mMapView;

    @Override
    protected FgMonitorPresenter createPresenter() {
        return new FgMonitorPresenter(this);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        FgTrajectoryView = inflater.inflate(R.layout.fg_trajectory_layout, null);
        initViewFgMonitor();
        return FgTrajectoryView;
    }

    private void initViewFgMonitor() {
        mMapView = (MapView) FgTrajectoryView.findViewById(R.id.fg_trajectory_mapview);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    protected void ChildRequestServiceData() {
        refresh_success_gone();
    }
}
