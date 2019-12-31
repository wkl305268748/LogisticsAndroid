package com.kenny.logistics.ui.fragment.driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.kenny.logistics.R;
import com.kenny.logistics.model.vo.user.UserVo;
import com.kenny.logistics.service.preferences.Proferences;
import com.kenny.logistics.service.presenter.DvUserInfoPresenter;
import com.kenny.logistics.service.presenter.SpUserInfoPresenter;
import com.kenny.logistics.service.view.BaseView;
import com.kenny.logistics.ui.LoginActivity;
import com.kenny.logistics.ui.component.CircleImageView;
import com.kenny.logistics.ui.component.DialogLoading;
import com.kenny.logistics.ui.fragment.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.bugly.beta.Beta;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kenny on 2017/8/8.
 */

public class DvUserFragment extends BaseFragment{

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.phone)
    TextView phone;

    Unbinder unbinder;
    DvUserInfoPresenter presenter;
    DialogLoading loading;

    public static DvUserFragment newInstance() {
        Bundle args = new Bundle();
        DvUserFragment fragment = new DvUserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dv_fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        presenter = new DvUserInfoPresenter(getContext());
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

    @OnClick({R.id.user, R.id.update, R.id.logout})
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
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                _mActivity.finish();
                                Proferences.getInstance(getContext()).clearAppProferences();
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
                break;
        }
    }


    //下拉刷新
    OnRefreshListener refreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            presenter.onGetInfo(Proferences.getInstance(getContext()).getAppProferences().getToken());
        }
    };

    private BaseView userInfoView = new BaseView<UserVo>() {
        @Override
        public void onSuccess(UserVo userVo) {

            Glide.with(_mActivity).load(userVo.getImg()).into(avatar);
            nickname.setText(userVo.getPhone());
            phone.setText(userVo.getDriverVo().getName());
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


