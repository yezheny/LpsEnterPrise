package com.yxcl.lpsenterprise.view.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.base.BaseActivity;
import com.yxcl.lpsenterprise.presenter.ILoginPresenter;
import com.yxcl.lpsenterprise.presenter.LoginPresenter;
import com.yxcl.lpsenterprise.utils.Util;
import com.yxcl.lpsenterprise.view.activity.main.RadioMainActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 10:13
 */

public class Login extends BaseActivity<LoginPresenter> implements ILoginPresenter {

    @Bind(R.id.login_top_view)
    View view;
    @Bind(R.id.account_image)
    ImageView accountImage;
    @Bind(R.id.account_qchu)
    ImageView accountQchu;
    @Bind(R.id.login_account_ed)
    EditText loginAccountEd;
    @Bind(R.id.pas_image)
    ImageView pasImage;
    @Bind(R.id.password_qchu)
    ImageView passwordQchu;
    @Bind(R.id.login_psw_ed)
    EditText loginPswEd;
    @Bind(R.id.remember_psw_cb)
    CheckBox rememberPswCb;
    @Bind(R.id.login_btn)
    Button loginBtn;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.login_layout;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarView(view).init();
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
    }

    @OnClick({R.id.account_qchu, R.id.password_qchu, R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.account_qchu:

                break;
            case R.id.password_qchu:

                break;
            case R.id.login_btn:
                Util.NextActivity(this, RadioMainActivity.class);
                break;
        }
    }
}
