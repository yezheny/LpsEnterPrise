package com.yxcl.lpsenterprise.view.activity.login;

import android.view.View;
import android.view.WindowManager;
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
    @Bind(R.id.new_account_qchu)
    ImageView newAccountQchu;
    @Bind(R.id.new_login_account_ed)
    EditText newLoginAccountEd;
    @Bind(R.id.new_password_qchu)
    ImageView newPasswordQchu;
    @Bind(R.id.new_login_psw_ed)
    EditText newLoginPswEd;
    @Bind(R.id.new_code_img)
    ImageView newCodeImg;
    @Bind(R.id.new_code_qchu)
    ImageView newCodeQchu;
    @Bind(R.id.new_login_code_ed)
    EditText newLoginCodeEd;
    @Bind(R.id.new_remember_psw_cb)
    CheckBox newRememberPswCb;
    @Bind(R.id.new_login_btn)
    Button newLoginBtn;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.new_login_layout;
    }

    @Override
    public void initView() {
        super.initView();
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//让布局向上移来显示软键盘
    }

    @OnClick({R.id.new_account_qchu, R.id.new_password_qchu
            , R.id.new_code_img, R.id.new_code_qchu, R.id.new_login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_account_qchu:
                //账号清除
                break;
            case R.id.new_password_qchu:
                //密码
                break;
            case R.id.new_code_img:
                //点击获取验证码
                break;
            case R.id.new_code_qchu:
                //密码清除
                break;
            case R.id.new_login_btn:
                //登录
                Util.NextActivity(this, RadioMainActivity.class);
                break;
        }
    }
}
