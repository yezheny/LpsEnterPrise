package com.yxcl.lpsenterprise.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.logger.Logger;
import com.yxcl.lpsenterprise.app.App;
import com.yxcl.lpsenterprise.utils.appmanager.AppManager;
import com.yxcl.lpsenterprise.utils.customview.LoadingDialog;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by zqf on 2017/3/13.
 * 新基类NewBaseActivity
 */
public abstract class OrdinaryBaseActivity extends Activity {

    private LoadingDialog mNetLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity管理类
        AppManager.getAppManager().addActivity(this);
        setWhiteStatusBar();
        //注册RxBus
//        RxBus.get().register(this);
        Logger.e("BaseActivity-->onCreate()");
    }

    /**
     * [沉浸状态栏]
     */
    private void setWhiteStatusBar() {
        ImmersionBar.with(this).statusBarDarkFont(true, 0.2f).init();
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void BaseStartActivity(Class<?> clz) {
        startActivity(new Intent(OrdinaryBaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void BaseStartActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void BaseStartActivityForResult(Class<?> cls, Bundle bundle,
                                           int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        AppManager.getAppManager().finishActivity(this);
        //解除RxBus注册
//        RxBus.get().unregister(this);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void BaseShowToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setShowLoading(String tip_msg) {
        if (TextUtils.isEmpty(tip_msg)) {
            tip_msg = "加载中...";
        }
        mNetLoadingDialog = LoadingDialog.showLoading(this, tip_msg);
    }

    public void onCancelLoadingDialog() {
        if (mNetLoadingDialog != null) {
            mNetLoadingDialog.dismiss();
            mNetLoadingDialog = null;
        }
    }

    //获取Token
    public String getToken() {
        String toke = App.getSp().getString("token");
        if (!TextUtils.isEmpty(toke)) {
            return toke;
        }
        return "";
    }

    //获取userid
    public String getUserid() {
        String userid = App.getSp().getString("user_id");
        if (!TextUtils.isEmpty(userid)) {
            return userid;
        }
        return "";
    }

    public void CallPhone(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
