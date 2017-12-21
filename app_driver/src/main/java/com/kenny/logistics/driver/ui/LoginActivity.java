package com.kenny.logistics.driver.ui;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.ui.adapter.TabViewPagerAdapter;
import com.kenny.logistics.driver.ui.fragment.FragmentLogin;
import com.kenny.logistics.driver.ui.fragment.FragmentRegist;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends FragmentActivity {
    @BindView(R.id.login_viewpager)
    ViewPager login_viewpager;
    @BindView(R.id.login_tabs)
    TabLayout login_tabs;

    public static LoginActivity instanse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setupViewPager();
        instanse = this;
    }

    //设置tab下的viewpager
    private void setupViewPager() {
        setupViewPager(login_viewpager);
        login_tabs.setupWithViewPager(login_viewpager);
        login_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                final int f = tab.getPosition();
                login_viewpager.setCurrentItem(f);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FragmentLogin(), "登录");
        //adapter.addFrag(new FragmentRegist(), "注册");
        viewPager.setAdapter(adapter);
    }
}
