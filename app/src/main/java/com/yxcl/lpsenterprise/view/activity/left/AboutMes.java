package com.yxcl.lpsenterprise.view.activity.left;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.base.OrdinaryBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * class from
 * Created by zqf
 * Time 2018/4/17 14:02
 */

public class AboutMes extends OrdinaryBaseActivity {

    @Bind(R.id.title_left_iv)
    ImageView titleLeftIv;
    @Bind(R.id.title_name_tv)
    TextView titleNameTv;
    @Bind(R.id.title_right_tv)
    TextView titleRightTv;
    @Bind(R.id.title_relative)
    RelativeLayout titleRelative;
    @Bind(R.id.version_tv)
    TextView versionTv;
    @Bind(R.id.go_score_relative)
    RelativeLayout goScoreRelative;
    @Bind(R.id.update_function_relative)
    RelativeLayout updateFunctionRelative;
    @Bind(R.id.complaint_relative)
    RelativeLayout complaintRelative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.title_left_iv, R.id.version_tv, R.id.go_score_relative
            , R.id.update_function_relative, R.id.complaint_relative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_left_iv:
                break;
            case R.id.version_tv:
                break;
            case R.id.go_score_relative:
                break;
            case R.id.update_function_relative:
                break;
            case R.id.complaint_relative:
                break;
        }
    }
}
