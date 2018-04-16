package com.yxcl.lpsenterprise.view.activity.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yxcl.lpsenterprise.R;
import com.yxcl.lpsenterprise.utils.customview.NoScrollViewPager;
import com.yxcl.lpsenterprise.view.adapter.NewMainFgAdapter;
import com.yxcl.lpsenterprise.view.fragment.FgMonitor;
import com.yxcl.lpsenterprise.view.fragment.FgTrajectory;
import com.yxcl.lpsenterprise.view.fragment.FgVideo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;

public class NewMainActivity extends AppCompatActivity {

    @Bind(R.id.new_main_toolbar)
    Toolbar main_toolbar;
    @Bind(R.id.new_main_drawer_layout)
    DrawerLayout main_drawer_layout;
    @Bind(R.id.new_main_tablayout)
    TabLayout new_main_tablayout;
    @Bind(R.id.new_main_viewpager)
    NoScrollViewPager new_main_viewpager;

    private List<Fragment> fg_list = new ArrayList<>();
    private FgMonitor mFgMonitor;
    private FgTrajectory mFgTrajectory;
    private FgVideo mFgVideo;
    private NewMainFgAdapter mAdapter;
    private String[] mTitleName = {"监控", "轨迹", "视频"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mynew_activity_main);
        ButterKnife.bind(this);
        statusBarMethod();
        initFgMent(savedInstanceState);
        initFgData();
    }

    private void initFgMent(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            mFgMonitor = (FgMonitor) getSupportFragmentManager().getFragment(savedInstanceState, "FgMonitor");
            mFgTrajectory = (FgTrajectory) getSupportFragmentManager().getFragment(savedInstanceState, "FgTrajectory");
            mFgVideo = (FgVideo) getSupportFragmentManager().getFragment(savedInstanceState, "FgVideo");
        } else {
            mFgMonitor = new FgMonitor();
            mFgTrajectory = new FgTrajectory();
            mFgVideo = new FgVideo();
        }
        fg_list.add(mFgMonitor);
        fg_list.add(mFgTrajectory);
        fg_list.add(mFgVideo);
        transaction.commitAllowingStateLoss();
    }

    private void initFgData() {
        new_main_viewpager.setOffscreenPageLimit(2);
        new_main_tablayout.setTabMode(TabLayout.MODE_FIXED);
        mAdapter = new NewMainFgAdapter(getSupportFragmentManager(), fg_list, Arrays.asList(mTitleName));
        new_main_viewpager.setAdapter(mAdapter);
        new_main_tablayout.setupWithViewPager(new_main_viewpager);
        new_main_tablayout.setTabTextColors(ContextCompat.getColor(this, R.color.app_all_tv_black_color)
                , ContextCompat.getColor(this, R.color.login_btn_nopress_color));
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getSupportFragmentManager();
        if (mFgMonitor.isAdded()) {
            manager.putFragment(outState, "NewsFragment", mFgMonitor);
        }
        if (mFgTrajectory.isAdded()) {
            manager.putFragment(outState, "FgTrajectory", mFgTrajectory);
        }
        if (mFgVideo.isAdded()) {
            manager.putFragment(outState, "FgVideo", mFgVideo);
        }
    }

    //返回键退出
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.new_main_drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
