package com.yxcl.lpsenterprise.view.activity.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
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

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.toolbar)
    Toolbar main_toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout main_drawer_layout;
    @Bind(R.id.nav_view)
    NavigationView main_nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity_main);
        ButterKnife.bind(this);
        statusBarMethod();
        initToolBarDrawable();
    }

    //沉浸式
    private void statusBarMethod() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
    }

    private void initToolBarDrawable() {
        setSupportActionBar(main_toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, main_drawer_layout, main_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        main_drawer_layout.addDrawerListener(toggle);
        toggle.syncState();
        main_nav_view.setNavigationItemSelectedListener(this);
        main_nav_view.setItemIconTintList(null);
    }

    //返回键退出
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_change_psw:

                break;
            case R.id.nav_feedback:

                break;
            case R.id.nav_about:

                break;
            case R.id.nav_out:

                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
