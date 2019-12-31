package com.kenny.logistics;

import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.Bugly;

import me.yokeyword.fragmentation.Fragmentation;

public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化腾讯Bugly
        Bugly.init(getApplicationContext(), "7a97467859", BuildConfig.DEBUG);

        Fragmentation.builder()
                // 设置 栈视图 模式为 BUBBLE:悬浮球模式   SHAKE: 摇一摇唤出  默认NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(BuildConfig.DEBUG ? Fragmentation.BUBBLE : Fragmentation.NONE)
                // 开发环境：true时，遇到异常："Can not perform this action after onSaveInstanceState!"时，抛出，并Crash;
                // 生产环境：false时，不抛出，不会Crash，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG) // 实际场景建议.debug(BuildConfig.DEBUG)
                .install();
    }
}
