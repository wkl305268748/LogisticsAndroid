package com.kenny.logistics.ui.fragment.shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kenny.logistics.R;
import com.kenny.logistics.ui.fragment.base.BaseFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpMessageFragment extends BaseFragment {

    public static SpMessageFragment newInstance() {
        Bundle args = new Bundle();
        SpMessageFragment fragment = new SpMessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }
}
