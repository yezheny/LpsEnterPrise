package com.yxcl.lpsenterprise.view.activity.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.utils.Util;
import com.yxcl.lpsenterprise.view.activity.left.AboutMes;
import com.yxcl.lpsenterprise.view.activity.left.ChangePsw;
import com.yxcl.lpsenterprise.view.activity.left.FeedBack;
import com.yxcl.lpsenterprise.view.activity.msg.MessageCenter;
import com.yxcl.lpsenterprise.view.fragment.FgMonitor;
import com.yxcl.lpsenterprise.view.fragment.FgTrajectory;
import com.yxcl.lpsenterprise.view.fragment.FgVideo;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RadioMainActivity extends AppCompatActivity {

    @Bind(R.id.radio_main_toolbar)
    Toolbar main_toolbar;
    @Bind(R.id.radio_main_drawer_layout)
    DrawerLayout main_drawer_layout;
    @Bind(R.id.monitor_radiobtn)
    RadioButton rb1;
    @Bind(R.id.trajectory_radiobtn)
    RadioButton rb2;
    @Bind(R.id.video_radiobtn)
    RadioButton rb3;
    @Bind(R.id.main_fg_radiogroup)
    RadioGroup radioGroup;
    @Bind(R.id.radio_main_content_fly)
    FrameLayout radio_main_content_fly;
    @Bind(R.id.left_changepsw_tv)
    TextView leftChangepswTv;
    @Bind(R.id.left_feedback_tv)
    TextView leftFeedbackTv;
    @Bind(R.id.left_about_mes_tv)
    TextView leftAboutMesTv;
    @Bind(R.id.left_out_login_tv)
    TextView leftOutLoginTv;

    private FgMonitor mFgMonitor;
    private FgTrajectory mFgTrajectory;
    private FgVideo mFgVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myradio_activity_main);
        ButterKnife.bind(this);
        statusBarMethod();
        initFgMent();
    }

    private void initFgMent() {
        radioGroup.check(R.id.monitor_radiobtn);
        select(1);
    }

    //沉浸式
    private void statusBarMethod() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
        initToolBarDrawable();
    }

    //toolbar配置
    private void initToolBarDrawable() {
        main_toolbar.setTitle("");
        setSupportActionBar(main_toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, main_drawer_layout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        main_drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * hidtFragment
     * 隐藏fragmnet
     * fragmentTransaction
     */
    private void hidtFragment(FragmentTransaction fragmentTransaction) {
        if (mFgMonitor != null) {
            fragmentTransaction.hide(mFgMonitor);
        }
        if (mFgTrajectory != null) {
            fragmentTransaction.hide(mFgTrajectory);
        }
        if (mFgVideo != null) {
            fragmentTransaction.hide(mFgVideo);
        }
    }

    /**
     * 选择fragment
     */
    private void select(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        hidtFragment(ft);
        switch (i) {
            case 1:
                if (mFgMonitor == null) {
                    mFgMonitor = new FgMonitor();
                    ft.add(R.id.radio_main_content_fly, mFgMonitor);
                } else {
                    ft.show(mFgMonitor);
                }
                break;
            case 2:
                if (mFgTrajectory == null) {
                    mFgTrajectory = new FgTrajectory();
                    ft.add(R.id.radio_main_content_fly, mFgTrajectory);
                } else {
                    ft.show(mFgTrajectory);
                }
                break;
            case 3:
                if (mFgVideo == null) {
                    mFgVideo = new FgVideo();
                    ft.add(R.id.radio_main_content_fly, mFgVideo);
                } else {
                    ft.show(mFgVideo);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    //返回键退出
    @Override
    public void onBackPressed() {
        if (main_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            main_drawer_layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //ToolBar使用
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //ToolBar使用选择
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            //消息
            Logger.e("消息");
            Util.NextActivity(this, MessageCenter.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.monitor_radiobtn, R.id.trajectory_radiobtn, R.id.video_radiobtn
            , R.id.left_changepsw_tv, R.id.left_feedback_tv
            , R.id.left_about_mes_tv, R.id.left_out_login_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.monitor_radiobtn:
                select(1);
                break;
            case R.id.trajectory_radiobtn:
                select(2);
                break;
            case R.id.video_radiobtn:
                select(3);
                break;
            case R.id.left_changepsw_tv:
                //改变密码
                Util.NextActivity(this, ChangePsw.class);
                break;
            case R.id.left_feedback_tv:
                //反馈意见
                Util.NextActivity(this, FeedBack.class);
                break;
            case R.id.left_about_mes_tv:
                //关于我们
                Util.NextActivity(this, AboutMes.class);
                break;
            case R.id.left_out_login_tv:
                //退出登录

                break;
        }
    }
}
