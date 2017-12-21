package com.kenny.logistics.driver.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.cocosw.favor.FavorAdapter;
import com.kenny.logistics.driver.ui.preferences.Account;
import com.kenny.logistics.driver.ui.preferences.Cache;

/**
 * Created by Kenny on 2017/9/15.
 */

public class SplashActivity extends Activity{
    Context context;
    int version;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        beginLoading();
        beginToken();
    }

    private void beginLoading(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                into();
            }
        }, 1500);
    }

    private void into(){
        if (Cache.account.getToken().equals("")) {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        }else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        finish();
    }

    private void beginToken(){
        if (!Cache.account.getToken().equals("")) {
            //校验Token
        }
    }
}
