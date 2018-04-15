package com.yxcl.lpsenterprise.view.activity.advsplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.app.App;
import com.yxcl.lpsenterprise.view.activity.login.Login;
import com.yxcl.lpsenterprise.view.activity.main.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * class from
 * Created by zqf
 * Time 2018/4/13 10:10
 */

public class Splash extends AppCompatActivity {


    @Bind(R.id.splash_logo_iv)
    ImageView splashLogoIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        ButterKnife.bind(this);
        /**
         渐变动画
         */
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1f);
        animation.setDuration(2000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (App.getSp().getBoolean("splash_permiss", false)) {
                    MainIntent();
                } else {
                    //检测权限
                    HiPermission.create(Splash.this)
                            .animStyle(R.style.PermissionAnimFade)
                            .checkMutiPermission(MyHiPermission);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashLogoIv.startAnimation(animation);
    }

    /**
     * 权限检测回调结果
     */
    private PermissionCallback MyHiPermission = new PermissionCallback() {
        @Override
        public void onClose() {
            //用户关闭权限操作--退出App
            Logger.i("-PermissionCallback--onClose");
            App.getSp().put("splash_permiss", false);
            finish();
        }

        @Override
        public void onFinish() {
            //所有权限申请完成
            Logger.i("-PermissionCallback--onFinish");
            App.getSp().put("splash_permiss", true);
            MainIntent();
        }

        @Override
        public void onDeny(String permission, int position) {
            Logger.i("-PermissionCallback--onDeny");
        }

        @Override
        public void onGuarantee(String permission, int position) {
            Logger.i("-PermissionCallback--onGuarantee");
        }
    };

    /**
     * 跳转主页面或Tag页面判断
     */
    private void MainIntent() {
        if (App.getSp().getBoolean("isRememberPsw", false)) {
            startActivity(new Intent(Splash.this, MainActivity.class));
        } else {
            startActivity(new Intent(Splash.this, Login.class));
        }
        finish();
    }
}
