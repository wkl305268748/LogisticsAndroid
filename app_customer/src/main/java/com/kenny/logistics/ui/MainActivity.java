package com.kenny.logistics.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allenliu.versionchecklib.core.AllenChecker;
import com.allenliu.versionchecklib.core.VersionParams;
import com.kenny.logistics.R;
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.ui.fragment.CreateOrderFragment;
import com.kenny.logistics.ui.fragment.HomeFragment;
import com.kenny.logistics.ui.fragment.MainFragment;
import com.kenny.logistics.ui.fragment.MessageFragment;
import com.kenny.logistics.ui.fragment.OrderFragment;
import com.kenny.logistics.ui.fragment.UserFragment;
import com.kenny.logistics.ui.service.VersionService;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

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
                .setRequestUrl(ApiRetrofit.BASE_URL + "v1/system/version/newest?type=customer_android")
                .setService(VersionService.class);
        AllenChecker.startVersionCheck(this, builder.build());
    }

}
