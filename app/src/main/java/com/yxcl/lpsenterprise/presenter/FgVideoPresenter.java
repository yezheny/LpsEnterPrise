package com.yxcl.lpsenterprise.presenter;

import com.yxcl.lpsenterprise.base.BasePresenter;
import com.yxcl.lpsenterprise.model.VideoBean;

import rx.Subscriber;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:38
 */

public class FgVideoPresenter extends BasePresenter<IFgVideoPresenter> {
    public FgVideoPresenter(IFgVideoPresenter view) {
        super(view);
    }

    public void getVideoData(String uid, String type) {
        addSubscription(mApiService.getMonitorAllCarsData(uid, type), new Subscriber<VideoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(VideoBean videoBean) {

            }
        });
    }
}
