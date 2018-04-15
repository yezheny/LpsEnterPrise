package com.yxcl.lpsenterprise.api;

/**
 * Created by zqf on 2017/5/23.
 * 数据管理类--->方便调用RetrofitService 中定义的方法
 */
public class ApiManager {
    private ApiService mRetrofitService;

    public ApiManager() {
        mRetrofitService = RetrofitHelper.getInstance().getServer();
    }


}
