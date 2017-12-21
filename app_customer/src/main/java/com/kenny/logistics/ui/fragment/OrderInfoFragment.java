package com.kenny.logistics.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kenny.logistics.R;
import com.kenny.logistics.api.ApiRetrofit;
import com.kenny.logistics.model.JsonBean;
import com.kenny.logistics.model.order.OrderSet;
import com.kenny.logistics.ui.adapter.ItemOrderGoodsAdapter;
import com.kenny.logistics.ui.base.BaseMainFragment;

import java.text.SimpleDateFormat;

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

public class OrderInfoFragment extends SupportFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_send_name)
    TextView tvSendName;
    @BindView(R.id.tv_send_addr)
    TextView tvSendAddr;
    @BindView(R.id.tv_send_addr_info)
    TextView tvSendAddrInfo;
    @BindView(R.id.tv_recive_name)
    TextView tvReciveName;
    @BindView(R.id.tv_recive_addr)
    TextView tvReciveAddr;
    @BindView(R.id.tv_recive_addr_info)
    TextView tvReciveAddrInfo;
    @BindView(R.id.tv_dispatching)
    TextView tvDispatching;
    @BindView(R.id.tv_send_time)
    TextView tvSendTime;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.tv_recive_time)
    TextView tvReciveTime;
    @BindView(R.id.tv_want_company)
    TextView tvWantCompany;
    @BindView(R.id.recycler_goods)
    RecyclerView recyclerGoods;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    Unbinder unbinder;
    int order_id;

    ItemOrderGoodsAdapter itemOrderGoodsAdapter;

    private void InitView(JsonBean<OrderSet> orderSetJsonBean){
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyy年MM月dd日");

        tvOrderNumber.setText("订单号："+orderSetJsonBean.getData().getOrder().getOrder_number());
        tvOrderTime.setText("下单时间："+myFmt.format(orderSetJsonBean.getData().getOrder().getTime()));
        switch (orderSetJsonBean.getData().getOrder().getStatus()){
            case "ORDER_PLACE":
                tvOrderStatus.setText("已下单");
                tvOrderStatus.setTextColor(ContextCompat.getColor(_mActivity, R.color.color_order_pending));
                break;
            case "ORDER_TAKING":
                tvOrderStatus.setText("已处理");
                tvOrderStatus.setTextColor(ContextCompat.getColor(_mActivity, R.color.color_order_taking));
                break;
            case "ORDER_SIGN":
                tvOrderStatus.setText("已签收");
                tvOrderStatus.setTextColor(ContextCompat.getColor(_mActivity, R.color.color_order_sign));
                break;
            case "ORDER_REFUSE":
                tvOrderStatus.setText("已拒绝");
                tvOrderStatus.setTextColor(ContextCompat.getColor(_mActivity, R.color.color_order_warning));
                break;
        }
        tvSendName.setText(orderSetJsonBean.getData().getOrderCustomer().getSend_name()+"("+orderSetJsonBean.getData().getOrderCustomer().getSend_phone()+")");
        tvReciveName.setText(orderSetJsonBean.getData().getOrderCustomer().getRecive_name()+"("+orderSetJsonBean.getData().getOrderCustomer().getRecive_phone()+")");
        tvSendAddr.setText(orderSetJsonBean.getData().getOrderCustomer().getSend_addr());
        tvSendAddrInfo.setText(orderSetJsonBean.getData().getOrderCustomer().getSend_addr_info());
        tvReciveAddr.setText(orderSetJsonBean.getData().getOrderCustomer().getRecive_addr());
        tvReciveAddrInfo.setText(orderSetJsonBean.getData().getOrderCustomer().getRecive_addr_info());
        tvSendTime.setText(myFmt2.format(orderSetJsonBean.getData().getOrderCustomer().getSend_time()));
        tvReciveTime.setText(myFmt2.format(orderSetJsonBean.getData().getOrderCustomer().getRecive_time()));
        tvDispatching.setText(orderSetJsonBean.getData().getOrderCustomer().getDispatching_type());
        if(!orderSetJsonBean.getData().getOrder().getIs_company())
            tvWantCompany.setText("未指定物流公司");
        else
            tvWantCompany.setText(orderSetJsonBean.getData().getWantCompany().getUserInfo().getCompany());
        itemOrderGoodsAdapter.setDatas(orderSetJsonBean.getData().getOrderGoods(),false);
        itemOrderGoodsAdapter.notifyDataSetChanged();
    }

    public static OrderInfoFragment newInstance(Bundle args) {
        OrderInfoFragment fragment = new OrderInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_info, container, false);
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
        itemOrderGoodsAdapter = new ItemOrderGoodsAdapter(_mActivity);
        recyclerGoods.setLayoutManager(new LinearLayoutManager(_mActivity));
        recyclerGoods.setAdapter(itemOrderGoodsAdapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            order_id = bundle.getInt("order_id");
            InitData(order_id);
        }
    }

    private void InitData(int id){
        progressBar.setVisibility(View.VISIBLE);
        ApiRetrofit.getInstance().orderApi.GetOrderById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonBean<OrderSet>>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull JsonBean<OrderSet> orderSetJsonBean) {
                        if(orderSetJsonBean.isSuccess()){
                            InitView(orderSetJsonBean);
                        }else{
                            Toast.makeText(_mActivity,orderSetJsonBean.getMsg(),Toast.LENGTH_SHORT);
                        }

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(_mActivity,"网络错误",Toast.LENGTH_SHORT);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
