package com.yxcl.lpsenterprise.presenter;

import com.yxcl.lpsenterprise.base.BasePresenter;
import com.yxcl.lpsenterprise.model.TrajectoryBean;

import rx.Subscriber;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:38
 */

public class FgTrajectoryPresenter extends BasePresenter<IFgTrajectoryPresenter> {

    public FgTrajectoryPresenter(IFgTrajectoryPresenter view) {
        super(view);
    }

    public void getAllCarsData(String uid, String type) {
        addSubscription(mApiService.getMonitorAllCarsData(uid, type), new Subscriber<TrajectoryBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TrajectoryBean trajectoryBean) {

            }
        });
    }
}
