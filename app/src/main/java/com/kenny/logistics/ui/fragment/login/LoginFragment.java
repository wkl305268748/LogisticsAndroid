package com.kenny.logistics.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kenny.logistics.R;
import com.kenny.logistics.ui.adapter.TabViewPagerAdapter;
import com.kenny.logistics.ui.fragment.base.BaseFragment;
import com.kenny.logistics.service.preferences.Proferences;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginFragment extends BaseFragment {

    public static final String TYPE_SHIPPER = "shipper";
    public static final String TYPE_DRIVER = "driver";

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;
    Unbinder unbinder;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initTab();
        Proferences.getInstance(getContext()).getAppProferences().setPhone("123123");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initTab(){
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFrag(TabLoginFragment.newInstance(LoginFragment.TYPE_SHIPPER), "货主");
        adapter.addFrag(TabLoginFragment.newInstance(LoginFragment.TYPE_DRIVER), "司机");
        pager.setAdapter(adapter);
        tab.setupWithViewPager(pager);
    }

}
