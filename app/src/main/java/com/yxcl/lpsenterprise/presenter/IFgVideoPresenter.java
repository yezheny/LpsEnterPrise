package com.yxcl.lpsenterprise.presenter;

import com.yxcl.lpsenterprise.model.VideoBean;

import java.util.List;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 13:38
 */

public interface IFgVideoPresenter {

    void getVideoData(List<VideoBean> mList);

    void error();
}
