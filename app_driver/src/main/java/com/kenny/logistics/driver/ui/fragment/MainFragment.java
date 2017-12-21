package com.kenny.logistics.driver.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.ui.base.BaseMainFragment;

import butterknife.BindArray;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends BaseMainFragment {
    @BindView(R.id.bar_order_icon)
    ImageView barOrderIcon;
    @BindView(R.id.bar_order_text)
    TextView barOrderText;
    @BindView(R.id.bar_order)
    RelativeLayout barOrder;
    @BindView(R.id.bar_make)
    ImageView barMake;
    @BindView(R.id.bar_user_icon)
    ImageView barUserIcon;
    @BindView(R.id.bar_user_text)
    TextView barUserText;
    @BindView(R.id.bar_user)
    RelativeLayout barUser;
    @BindView(R.id.frame)
    FrameLayout frameLayout;
    //---color---
    @BindColor(R.color.color_main_bar_select)
    int barSelect;
    @BindColor(R.color.color_main_bar_unselect)
    int barUnSelect;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindArray(R.array.title)
    String[] title;
    Unbinder unbinder;
    //----fragment---
    private SupportFragment[] mFragments = new SupportFragment[2];
    public static final int ORDER = 0;
    public static final int USER = 1;
    int position = ORDER;

    public static MainFragment instance;


    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        initFragment();
        initBar();
        instance = this;
    }

    private void initFragment() {
        SupportFragment ORDERFragment = findFragment(OrderFragment.class);
        if (ORDERFragment == null) {
            mFragments[ORDER] = OrderFragment.newInstance();
            mFragments[USER] = UserFragment.newInstance();

            loadMultipleRootFragment(R.id.frame, ORDER,
                    mFragments[ORDER],
                    mFragments[USER]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.findFragmentByTag()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[ORDER] = ORDERFragment;
            mFragments[USER] = findFragment(UserFragment.class);
        }
    }

    private void initBar() {
        reColorBar();
        barOrderIcon.setColorFilter(barSelect);
        barOrderText.setTextColor(barSelect);
        toolbarTitle.setText(title[ORDER]);
    }

    @OnClick({R.id.bar_order,R.id.bar_user})
    public void onViewClicked(View view) {
        reColorBar();
        switch (view.getId()) {
            case R.id.bar_order:
                barOrderIcon.setColorFilter(barSelect);
                barOrderText.setTextColor(barSelect);
                showHideFragment(mFragments[ORDER], mFragments[position]);
                position = ORDER;
                break;
            case R.id.bar_user:
                barUserIcon.setColorFilter(barSelect);
                barUserText.setTextColor(barSelect);
                showHideFragment(mFragments[USER], mFragments[position]);
                position = USER;
                break;
        }
        toolbarTitle.setText(title[position]);

    }

    @OnClick(R.id.bar_make)
    public void onMakeClicked(View view) {
        start(OrderSignFragment.newInstance());
    }

    private void reColorBar() {
        barOrderIcon.setColorFilter(barUnSelect);
        barUserIcon.setColorFilter(barUnSelect);

        barOrderText.setTextColor(barUnSelect);
        barUserText.setTextColor(barUnSelect);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public void toFragment(int type,Bundle bundle) {
        switch (type) {
            case 1:
                start(OrderInfoFragment.newInstance(bundle));
                break;
            case 2:
                start(OrderSignCameraFragment.newInstance(bundle));
                break;
            case 3:
                start(OrderSignSubmitFragment.newInstance(bundle));
                break;
        }
    }

}
