package com.kenny.logistics.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import me.yokeyword.fragmentation.SupportFragment;


public abstract class BaseBackFragment extends SupportFragment {
    public static final int RESULT_OK = 1;
    public static final int TYPE_NOMAL = 1;
    public static final int TYPE_SELECT = 2;
    public static final int TYPE_EDIT = 3;
    public static final int TYPE_DELETE = 4;
    public int TYPE = TYPE_NOMAL;
    public static final String TAG = "BaseBackFragment";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy-->"+this.getClass().getCanonicalName());
    }

    protected void initToolbarNav(Toolbar toolbar) {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftInput();
                _mActivity.onBackPressed();
            }
        });
    }

    //被打开时调用
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume-->"+this.getClass().getCanonicalName());
        //极光统计
        //JAnalyticsInterface.onPageStart(getContext(),this.getClass().getCanonicalName());
    }

    //被关闭时、锁屏时、切换其他应用时调用
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"onPause-->"+this.getClass().getCanonicalName());
        //极光统计
        //JAnalyticsInterface.onPageEnd(getContext(),this.getClass().getCanonicalName());
    }

    //被新View盖住时调用
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.d(TAG,"onHiddenChanged-->"+hidden+"--"+this.getClass().getCanonicalName());
        //极光统计
        //JAnalyticsInterface.onPageEnd(getContext(),this.getClass().getCanonicalName());
    }
}
