package com.yxcl.lpsenterprise.api;

import com.yxcl.lpsenterprise.model.MonitorAllCarsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zqf on 2017/5/22.
 * 接口
 */

public interface ApiService {

    /**
     * 获取车辆监控所有车
     */
    @GET("app/get/all/car")
    Observable<MonitorAllCarsBean> getMonitorAllCarsData(@Query("uid") String uid, @Query("type") String type);

}
