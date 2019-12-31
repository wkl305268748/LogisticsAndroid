package com.kenny.logistics.ui.fragment.shipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.kenny.logistics.R;
import com.kenny.logistics.model.vo.user.UserVo;
import com.kenny.logistics.service.presenter.SpUserInfoPresenter;
import com.kenny.logistics.service.view.BaseView;
import com.kenny.logistics.ui.LoginActivity;
import com.kenny.logistics.ui.component.CircleImageView;
import com.kenny.logistics.ui.component.DialogLoading;
import com.kenny.logistics.ui.fragment.base.BaseFragment;
import com.kenny.logistics.service.preferences.Proferences;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpUserFragment extends BaseFragment{

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.shipper)
    TextView shipper;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.update)
    LinearLayout update;
    @BindView(R.id.logout)
    LinearLayout logout;
    @BindView(R.id.tv_money)
    TextView tvMoney;

    Unbinder unbinder;
    SpUserInfoPresenter presenter;
    DialogLoading loading;

    public static SpUserFragment newInstance() {
        Bundle args = new Bundle();
        SpUserFragment fragment = new SpUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        presenter = new SpUserInfoPresenter(getContext());
        presenter.onCreate(userInfoView);
        loading = new DialogLoading(getContext());

        //设置refresh
        refresh.setOnRefreshListener(refreshListener);
        loading.show();
        presenter.onGetInfo(Proferences.getInstance(getContext()).getAppProferences().getToken());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    //下拉刷新
    OnRefreshListener refreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            presenter.onGetInfo(Proferences.getInstance(getContext()).getAppProferences().getToken());
        }
    };

    @OnClick({R.id.update, R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user:

                break;
            case R.id.update:
                Beta.checkUpgrade();
                break;
            case R.id.logout:
                new MaterialStyledDialog.Builder(_mActivity)
                        .setTitle("提示!")
                        .setDescription("是否确定退出？")
                        .setPositiveText("确定")
                        .setStyle(Style.HEADER_WITH_TITLE)
                        .onPositive((dialog, which) -> {
                            _mActivity.finish();
                            Proferences.getInstance(getContext()).clearAppProferences();
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        })
                        .show();
                break;
        }
    }


    private BaseView userInfoView = new BaseView<UserVo>() {
        @Override
        public void onSuccess(UserVo userVo) {

            Glide.with(_mActivity).load(userVo.getImg()).into(avatar);
            nickname.setText(userVo.getPhone());
            shipper.setText(userVo.getShipperVo().getName());
            tvMoney.setText(userVo.getShipperVo().getMoney()+" 元");
        }

        @Override
        public void onError(String result) {
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onComplate() {
            refresh.finishRefresh();
            loading.hide();
        }
    };
}


