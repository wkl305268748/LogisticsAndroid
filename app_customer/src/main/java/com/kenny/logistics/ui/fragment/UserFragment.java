package com.kenny.logistics.ui.fragment;

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
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.user.UserSet;
import com.kenny.logistics.ui.LoginActivity;
import com.kenny.logistics.ui.base.BaseMainFragment;
import com.kenny.logistics.ui.component.CircleImageView;
import com.kenny.logistics.ui.preferences.Cache;

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

public class UserFragment extends BaseMainFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;
    @BindView(R.id.nickname)
    TextView nickname;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.content)
    LinearLayout content;
    @BindView(R.id.user)
    LinearLayout user;
    @BindView(R.id.update)
    LinearLayout update;
    @BindView(R.id.logout)
    LinearLayout logout;
    @BindView(R.id.tv_order_place)
    TextView tvOrderPlace;
    @BindView(R.id.tv_order_taking)
    TextView tvOrderTaking;
    @BindView(R.id.tv_order_sign)
    TextView tvOrderSign;
    @BindView(R.id.tv_order_refuse)
    TextView tvOrderRefuse;

    public static UserFragment newInstance() {
        Bundle args = new Bundle();
        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        unbinder = ButterKnife.bind(this, view);
        refresh.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initInfo();
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
                Toast.makeText(_mActivity, "已经是最新版本", Toast.LENGTH_SHORT).show();
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
                                Cache.account.setToken("");
                                Intent intent = new Intent(getActivity(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
                break;
        }
    }

    @Override
    public void onRefresh() {
        System.out.println("onRefresh");
        initInfo();
    }

    private void initInfo() {
        ApiRetrofit.getInstance().userApi.InfoEx(Cache.account.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<UserSet>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull JsonBean<UserSet> userSetJsonBean) {
                        if(userSetJsonBean.isSuccess()) {
                            Glide.with(_mActivity).load(userSetJsonBean.getData().getUserInfo().getImg()).into(avatar);
                            nickname.setText(userSetJsonBean.getData().getUserInfo().getNickname());
                            phone.setText(userSetJsonBean.getData().getUser().getUsername());
                        }else{
                            Toast.makeText(_mActivity,userSetJsonBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                        //刷新完成
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        //刷新完成
                        refresh.setRefreshing(false);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        ApiRetrofit.getInstance().orderApi.CustomerAll(Cache.account.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<Map>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.annotations.NonNull JsonBean<Map> mapJsonBean) {
                        if(mapJsonBean.isSuccess()) {
                            tvOrderPlace.setText((int) Double.parseDouble(mapJsonBean.getData().get("order_place").toString()) + "");
                            tvOrderTaking.setText((int) Double.parseDouble(mapJsonBean.getData().get("order_taking").toString()) + "");
                            tvOrderSign.setText((int) Double.parseDouble(mapJsonBean.getData().get("order_sign").toString()) + "");
                            tvOrderRefuse.setText((int) Double.parseDouble(mapJsonBean.getData().get("order_refuse").toString()) + "");
                        }
                        //刷新完成
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        //刷新完成
                        refresh.setRefreshing(false);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}


