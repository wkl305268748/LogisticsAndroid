package com.kenny.logistics.ui.fragment.shipper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kenny.logistics.R;
import com.kenny.logistics.model.po.OrderGoods;
import com.kenny.logistics.ui.eventbus.SpCreateOrderGoodsEvent;
import com.kenny.logistics.ui.eventbus.SpCreateOrderReciveEvent;
import com.kenny.logistics.ui.fragment.base.BaseBackFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpCreateOrderGoodsAEFragment extends BaseBackFragment {

    public static final String TYPE_EDIT = "edit";
    public static final String TYPE_ADD = "add";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.goods_name)
    EditText goodsName;
    @BindView(R.id.goods_weight)
    EditText goodsWeight;
    @BindView(R.id.goods_size)
    EditText goodsSize;
    @BindView(R.id.goods_number)
    EditText goodsNumber;
    @BindView(R.id.goods_remark)
    EditText goodsRemark;
    Unbinder unbinder;

    OrderGoods orderGoods;
    int position;
    String type;

    public static SpCreateOrderGoodsAEFragment newInstance() {
        Bundle args = new Bundle();
        SpCreateOrderGoodsAEFragment fragment = new SpCreateOrderGoodsAEFragment();
        fragment.setArguments(args);
        fragment.type = TYPE_ADD;
        return fragment;
    }

    public static SpCreateOrderGoodsAEFragment newInstance(OrderGoods orderGoods,int position) {
        Bundle args = new Bundle();
        SpCreateOrderGoodsAEFragment fragment = new SpCreateOrderGoodsAEFragment();
        fragment.setArguments(args);
        fragment.orderGoods = orderGoods;
        fragment.position = position;
        fragment.type = TYPE_EDIT;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        initToolbarNav(toolbar);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        switch (type){
            case TYPE_ADD:
                orderGoods = new OrderGoods();
                break;
            case TYPE_EDIT:
                goodsName.setText(orderGoods.getName());
                goodsNumber.setText(orderGoods.getNumber().toString());
                goodsSize.setText(orderGoods.getSize());
                goodsWeight.setText(orderGoods.getWeight().toString());
                goodsRemark.setText(orderGoods.getRemark());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {
        if(goodsName.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入货物名称", Snackbar.LENGTH_LONG).show();return;}

        if(goodsNumber.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入货物数量", Snackbar.LENGTH_LONG).show();return;}

        if(goodsWeight.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入货物重量", Snackbar.LENGTH_LONG).show();return;}

        orderGoods.setName(goodsName.getText().toString());
        orderGoods.setNumber(Double.parseDouble(goodsNumber.getText().toString()));
        orderGoods.setWeight(Double.parseDouble(goodsWeight.getText().toString()));
        orderGoods.setSize(goodsSize.getText().toString());
        orderGoods.setRemark(goodsRemark.getText().toString());

        switch (type){
            case TYPE_ADD:
                EventBus.getDefault().post(new SpCreateOrderGoodsEvent(orderGoods));
                break;
            case TYPE_EDIT:
                EventBus.getDefault().post(new SpCreateOrderGoodsEvent(position,orderGoods));
                break;
        }
        hideSoftInput();
        pop();
    }
}
