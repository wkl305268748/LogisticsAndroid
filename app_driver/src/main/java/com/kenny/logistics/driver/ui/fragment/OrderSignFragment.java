package com.kenny.logistics.driver.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.kenny.logistics.driver.R;
import com.kenny.logistics.driver.api.ApiRetrofit;
import com.kenny.logistics.driver.model.JsonBean;
import com.kenny.logistics.driver.model.PageResponse;
import com.kenny.logistics.driver.model.order.OrderCustomer;
import com.kenny.logistics.driver.model.order.OrderSet;
import com.kenny.logistics.driver.ui.adapter.ItemOrderAdapter;
import com.kenny.logistics.driver.ui.adapter.ItemOrderSignAdapter;
import com.kenny.logistics.driver.ui.preferences.Cache;
import com.kenny.logistics.driver.util.MapNaviUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class OrderSignFragment extends SupportFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    Unbinder unbinder;
    ItemOrderSignAdapter itemOrderAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static OrderSignFragment newInstance() {
        Bundle args = new Bundle();
        OrderSignFragment fragment = new OrderSignFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_sign, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        initView();
    }

    //初始化
    private void initView() {
        refresh.setOnRefreshListener(this);
        itemOrderAdapter = new ItemOrderSignAdapter(_mActivity);
        recycler.setLayoutManager(new LinearLayoutManager(_mActivity));
        recycler.setAdapter(itemOrderAdapter);
        itemOrderAdapter.setOnItemClickListener(new ItemOrderSignAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                Bundle bundle = new Bundle();
                bundle.putInt("order_id", itemOrderAdapter.getItem(position).getOrder().getId());
                bundle.putString("order_number", itemOrderAdapter.getItem(position).getOrder().getOrder_number());
                startWithPop(OrderSignCameraFragment.newInstance(bundle));
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
        ApiRetrofit.getInstance().orderApi.GetOrderPageTakingByDriverToken(0, 50, Cache.account.getToken())
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
