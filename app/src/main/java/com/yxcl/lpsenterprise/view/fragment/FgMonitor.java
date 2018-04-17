package com.yxcl.lpsenterprise.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.baidu.mapapi.map.MapView;
import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.app.App;
import com.yxcl.lpsenterprise.base.BaseFragment;
import com.yxcl.lpsenterprise.model.MonitorAllCarsBean;
import com.yxcl.lpsenterprise.presenter.FgMonitorPresenter;
import com.yxcl.lpsenterprise.presenter.IFgMonitorPresenter;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:37
 */

public class FgMonitor extends BaseFragment<FgMonitorPresenter> implements IFgMonitorPresenter {
    View FgView;
    private MapView mMapView;
    private String CarType = "";//默认所有

    @Override
    protected FgMonitorPresenter createPresenter() {
        return new FgMonitorPresenter(this);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        FgView = inflater.inflate(R.layout.fg_monitor_layout, null);
        initViewBaiduFgMonitor();
        return FgView;
    }

    private void initViewBaiduFgMonitor() {
        mMapView = (MapView) FgView.findViewById(R.id.fg_monitor_mapview);
        mMapView.showZoomControls(false);//是否显示缩放控件
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
        mPresenter.getAllCarsData(App.getSp().getString("user_id"), CarType);
    }

    @Override
    public void getAllCarsData(List<MonitorAllCarsBean> mList) {
        refresh_success_gone();
    }

    @Override
    public void error() {

    }
}
