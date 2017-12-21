package com.kenny.logistics.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.PageResponse;
import com.kenny.logistics.model.order.OrderSet;
import com.kenny.logistics.ui.adapter.ItemOrderAdapter;
import com.kenny.logistics.ui.base.BaseMainFragment;
import com.kenny.logistics.ui.listener.OnItemClickListener;
import com.kenny.logistics.ui.preferences.Cache;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Kenny on 2017/8/8.
 */

public class OrderFragment extends BaseMainFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Unbinder unbinder;
    ItemOrderAdapter itemOrderAdapter;

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        initView();
    }

    //初始化
    private void initView() {
        refresh.setOnRefreshListener(this);
        itemOrderAdapter = new ItemOrderAdapter(_mActivity);
        recycler.setLayoutManager(new LinearLayoutManager(_mActivity));
        recycler.setAdapter(itemOrderAdapter);
        itemOrderAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Bundle bundle = new Bundle();
                bundle.putInt("order_id",itemOrderAdapter.getItem(position).getOrder().getId());
                MainFragment.instance.toFragment(5,bundle);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        initData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        initData();
    }

    private void initData() {
        ApiRetrofit.getInstance().orderApi.GetOrderPageByCustomerToken(0, 50, Cache.account.getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<PageResponse<OrderSet>>>() {

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonBean<PageResponse<OrderSet>> pageResponseJsonBean) {

                        if (pageResponseJsonBean.isSuccess()) {
                            Toast.makeText(_mActivity, "加载成功", Toast.LENGTH_SHORT).show();
                            itemOrderAdapter.setDatas(pageResponseJsonBean.getData().getItem());
                            itemOrderAdapter.notifyDataSetChanged();

                        } else {
                            Toast.makeText(_mActivity, "加载失败 " + pageResponseJsonBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        //刷新完成
                        refresh.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        Toast.makeText(_mActivity, "加载失败", Toast.LENGTH_SHORT).show();
                        //刷新完成
                        refresh.setRefreshing(false);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
