package com.yxcl.lpsenterprise.presenter;

import com.yxcl.lpsenterprise.model.MonitorAllCarsBean;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:38
 */

public interface IFgMonitorPresenter {

    void getAllCarsData(List<MonitorAllCarsBean> mList);

    void error();
}
