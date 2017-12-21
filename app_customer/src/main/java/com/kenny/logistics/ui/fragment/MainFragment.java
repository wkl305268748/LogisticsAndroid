package com.kenny.logistics.ui.fragment;

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

import com.kenny.logistics.R;
import com.kenny.logistics.ui.MainActivity;
import com.kenny.logistics.ui.base.BaseMainFragment;

import butterknife.BindArray;
import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;

public class MainFragment extends BaseMainFragment {
    @BindView(R.id.bar_home_icon)
    ImageView barHomeIcon;
    @BindView(R.id.bar_home_text)
    TextView barHomeText;
    @BindView(R.id.bar_home)
    RelativeLayout barHome;
    @BindView(R.id.bar_order_icon)
    ImageView barOrderIcon;
    @BindView(R.id.bar_order_text)
    TextView barOrderText;
    @BindView(R.id.bar_order)
    RelativeLayout barOrder;
    @BindView(R.id.bar_make)
    ImageView barMake;
    @BindView(R.id.bar_message_icon)
    ImageView barMessageIcon;
    @BindView(R.id.bar_message_text)
    TextView barMessageText;
    @BindView(R.id.bar_message)
    RelativeLayout barMessage;
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
    private SupportFragment[] mFragments = new SupportFragment[4];
    public static final int HOME = 0;
    public static final int ORDER = 1;
    public static final int MESSAGE = 2;
    public static final int USER = 3;
    int position = HOME;

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
        SupportFragment HOMEFragment = findFragment(HomeFragment.class);
        if (HOMEFragment == null) {
            mFragments[HOME] = HomeFragment.newInstance();
            mFragments[ORDER] = OrderFragment.newInstance();
            mFragments[MESSAGE] = MessageFragment.newInstance();
            mFragments[USER] = UserFragment.newInstance();

            loadMultipleRootFragment(R.id.frame, HOME,
                    mFragments[HOME],
                    mFragments[ORDER],
                    mFragments[MESSAGE],
                    mFragments[USER]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.findFragmentByTag()自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[HOME] = HOMEFragment;
            mFragments[ORDER] = findFragment(OrderFragment.class);
            mFragments[MESSAGE] = findFragment(MessageFragment.class);
            mFragments[USER] = findFragment(UserFragment.class);
        }
    }

    private void initBar() {
        reColorBar();
        barHomeIcon.setColorFilter(barSelect);
        barHomeText.setTextColor(barSelect);
        toolbarTitle.setText(title[HOME]);
    }

    @OnClick({R.id.bar_home, R.id.bar_order, R.id.bar_message, R.id.bar_user})
    public void onViewClicked(View view) {
        reColorBar();
        switch (view.getId()) {
            case R.id.bar_home:
                barHomeIcon.setColorFilter(barSelect);
                barHomeText.setTextColor(barSelect);
                showHideFragment(mFragments[HOME], mFragments[position]);
                position = HOME;
                break;
            case R.id.bar_order:
                barOrderIcon.setColorFilter(barSelect);
                barOrderText.setTextColor(barSelect);
                showHideFragment(mFragments[ORDER], mFragments[position]);
                position = ORDER;
                break;
            case R.id.bar_message:
                barMessageIcon.setColorFilter(barSelect);
                barMessageText.setTextColor(barSelect);
                showHideFragment(mFragments[MESSAGE], mFragments[position]);
                position = MESSAGE;
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
        start(CreateOrderFragment.newInstance());
    }

    private void reColorBar() {
        barHomeIcon.setColorFilter(barUnSelect);
        barOrderIcon.setColorFilter(barUnSelect);
        barMessageIcon.setColorFilter(barUnSelect);
        barUserIcon.setColorFilter(barUnSelect);

        barHomeText.setTextColor(barUnSelect);
        barOrderText.setTextColor(barUnSelect);
        barMessageText.setTextColor(barUnSelect);
        barUserText.setTextColor(barUnSelect);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void toFragment(int type) {
        switch (type) {
            case 1:
                start(CreateOrderFragment.newInstance());
                break;
            case 2:
                reColorBar();
                barOrderIcon.setColorFilter(barSelect);
                barOrderText.setTextColor(barSelect);
                showHideFragment(mFragments[ORDER], mFragments[HOME]);
                position = ORDER;
                toolbarTitle.setText(title[position]);
                break;
            case 3:
                reColorBar();
                barMessageIcon.setColorFilter(barSelect);
                barMessageText.setTextColor(barSelect);
                showHideFragment(mFragments[MESSAGE], mFragments[HOME]);
                position = MESSAGE;
                toolbarTitle.setText(title[position]);
                break;
            case 4:
                reColorBar();
                barUserIcon.setColorFilter(barSelect);
                barUserText.setTextColor(barSelect);
                showHideFragment(mFragments[USER], mFragments[HOME]);
                position = USER;
                toolbarTitle.setText(title[position]);
                break;
        }
    }

    public void toFragment(int type,Bundle bundle) {
        switch (type) {
            case 5:
                start(OrderInfoFragment.newInstance(bundle));
                break;
        }
    }

}
