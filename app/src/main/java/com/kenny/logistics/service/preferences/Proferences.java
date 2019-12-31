package com.kenny.logistics.service.preferences;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.cocosw.favor.FavorAdapter;

/**
 * Created by Kenny on 2017/12/29.
 */

public class Proferences {

    AppProferences appProferences;
    Context mContext;
    private static Proferences instance = null;

    private Proferences(Context context){
        appProferences = new FavorAdapter.Builder(context).build().create(AppProferences.class);
        mContext = context;
    }

    public static Proferences getInstance(Context context){
        if(instance == null)
            instance = new Proferences(context);
        return instance;
    }

    public AppProferences getAppProferences() {
        return appProferences;
    }

    public void clearAppProferences(){
        appProferences.setToken("");
    }

    /**
     * 获取本地软件版本号名称
     */
    public String getLocalVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = mContext.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(mContext.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }
}
