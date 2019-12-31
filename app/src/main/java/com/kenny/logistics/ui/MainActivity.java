package com.kenny.logistics.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.kenny.logistics.R;
import com.kenny.logistics.service.preferences.Proferences;
import com.kenny.logistics.ui.fragment.driver.DvMainFragment;
import com.kenny.logistics.ui.fragment.login.LoginFragment;
import com.kenny.logistics.ui.fragment.shipper.SpMainFragment;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAllPower();
        if (findFragment(SpMainFragment.class) == null) {
            switch (Proferences.getInstance(this).getAppProferences().getType()){
                case LoginFragment.TYPE_DRIVER:
                    loadRootFragment(R.id.rl_main, DvMainFragment.newInstance());
                    break;
                case LoginFragment.TYPE_SHIPPER:
                    loadRootFragment(R.id.rl_main, SpMainFragment.newInstance());
                    break;
            }
        }
    }


    //权限检查
    public void requestAllPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }
    }
}