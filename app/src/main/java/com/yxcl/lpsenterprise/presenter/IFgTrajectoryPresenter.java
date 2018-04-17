package com.yxcl.lpsenterprise.presenter;

import com.yxcl.lpsenterprise.model.TrajectoryBean;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:38
 */

public interface IFgTrajectoryPresenter {

    void getData(List<TrajectoryBean> mList);

    void error();
}
