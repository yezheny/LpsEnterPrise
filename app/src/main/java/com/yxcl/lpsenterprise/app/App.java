package com.yxcl.lpsenterprise.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.yxcl.lpsenterprise.BuildConfig;
import com.yxcl.lpsenterprise.api.RetrofitHelper;
import com.yxcl.lpsenterprise.config.Constants;
import com.yxcl.lpsenterprise.db.help.RealmHelp;

import java.util.Locale;

/**
 * Created by zqf on 2017/5/31.
 * pplication类-->设置应用全局的一些属性
 */
public class App extends DefaultApplicationLike {

    private static SPUtils mSPUtils;

    private static App instance;

    private static Context mContext;

    public App(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag
            , long applicationStartElapsedTime, long applicationStartMillisTime
            , Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime
                , applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly初始化基本配置
        initBugly();
        //App的实例
        instance = this;
        //ApplicationContext对象
        mContext = getApplication().getApplicationContext();
        //工具类初始化
        Utils.init(getApplication());
        //SharePreference
        mSPUtils = SPUtils.getInstance("lpsenterprise");
        //初始化日志
        initLogger();
        //初始化Retrofit
        RetrofitHelper.getInstance().init();
        //初始化Realm数据库
        RealmHelp.getInstance().init(mContext);
        //换肤初始化
//        initSkin();
    }

//    private void initSkin() {
//        SkinCompatManager.withoutActivity(getApplication())            // 基础控件换肤初始化
//                .addInflater(new SkinMaterialViewInflater())            // material design 控件换肤初始化[可选]
//                .addInflater(new SkinConstraintViewInflater())          // ConstraintLayout 控件换肤初始化[可选]
//                .addInflater(new SkinCardViewInflater())                // CardView v7 控件换肤初始化[可选]
//                .setSkinStatusBarColorEnable(true)                     // 关闭状态栏换肤，默认打开[可选]
//                .setSkinWindowBackgroundEnable(true)                   // 关闭windowBackground换肤，默认打开[可选]
//                .loadSkin();
//    }

    private void initBugly() {
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = false;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplication(),
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Logger.e("补丁下载成功");
            }

            @Override
            public void onDownloadFailure(String msg) {
                Logger.e("补丁下载失败");
            }

            @Override
            public void onApplySuccess(String msg) {
                Logger.e("补丁应用成功");
            }

            @Override
            public void onApplyFailure(String msg) {
                Logger.e("补丁应用失败");
            }

            @Override
            public void onPatchRollback() {

            }
        };
        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(getApplication(), true);
        // 多渠道需求塞入
        // String channel = WalleChannelReader.getChannel(getApplication());
        // Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), Constants.Bugly_AppID, true);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // TODO: 安装tinker
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(
            Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }


    /**
     * Logger
     * init("mytag")    //LOG TAG默认是PRETTYLOGGER
     * methodCount(3)                 // 决定打印多少行（每一行代表一个方法）默认：2
     * hideThreadInfo()               // 隐藏线程信息 默认：显示
     * logLevel(LogLevel.NONE)        // 是否显示Log 默认：LogLevel.FULL（全部显示）
     * methodOffset(2)                // 默认：0
     * logAdapter(new AndroidLogAdapter()); //可以自己构造适配器默认：AndroidLogAdapter
     */
    private void initLogger() {
        //Logger 2.1.1版本使用方法
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(2)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("LifeHelp")           //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        if (BuildConfig.API_ENV) {
            //日志输出
            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        } else {
            //上线时停止日志
            Logger.addLogAdapter(new AndroidLogAdapter() {
                @Override
                public boolean isLoggable(int priority, String tag) {
                    return BuildConfig.DEBUG;
                }
            });
        }
    }

    //获取Sp实例
    public static SPUtils getSp() {
        return mSPUtils;
    }

    //全局获取App
    public static App getInstance() {
        return instance;
    }

    //获取Context全局实例
    public static Context getCon() {
        return mContext;
    }
}
