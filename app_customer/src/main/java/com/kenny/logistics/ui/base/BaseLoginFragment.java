package com.kenny.logistics.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * BaseFragment负责ButterKnife的统一绑定
 */
public abstract class BaseLoginFragment extends Fragment {
    //抽象返回layoutId
    public abstract int getContentViewId();

    protected View mRootView;
    protected Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        ButterKnife.bind(this, mRootView);//绑定framgent
        this.context = getActivity();
        return mRootView;
    }

    public void showSnackar(View view, String string) {
        Snackbar.make(view, string, Snackbar.LENGTH_LONG).show();
    }

}
