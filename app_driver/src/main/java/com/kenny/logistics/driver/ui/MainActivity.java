package com.kenny.logistics.driver.ui;

import android.os.Bundle;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.api.ApiRetrofit;
import com.kenny.logistics.driver.ui.fragment.MainFragment;
import com.kenny.logistics.driver.ui.service.VersionService;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.rl_main, MainFragment.newInstance());
        }



        //版本检测
        VersionParams.Builder builder = new VersionParams.Builder()
                .setRequestUrl(ApiRetrofit.BASE_URL + "v1/system/version/newest?type=driver_android")
                .setService(VersionService.class);
        AllenChecker.startVersionCheck(this, builder.build());
    }

}
