package com.kenny.logistics.ui.fragment.shipper;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.kenny.logistics.R;
import com.kenny.logistics.model.po.Order;
import com.kenny.logistics.ui.eventbus.SpCreateOrderReciveEvent;
import com.kenny.logistics.ui.eventbus.SpCreateOrderSendEvent;
import com.kenny.logistics.ui.fragment.base.BaseBackFragment;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Kenny on 2017/8/8.
 */

public class SpCreateOrderAddFragment extends BaseBackFragment {

    public static final String TYPE_SEND = "发货";
    public static final String TYPE_RECIVE = "收货";

    @BindView(R.id.name)
    EditText addName;
    @BindView(R.id.phone)
    EditText addPhone;
    @BindView(R.id.addr)
    TextView addAddr;
    @BindView(R.id.addr_info)
    EditText addAddrInfo;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    CityPicker cityPicker;

    public Order order;
    public String type = TYPE_SEND;

    public static SpCreateOrderAddFragment newInstance(Order order, String type) {
        SpCreateOrderAddFragment fragment = new SpCreateOrderAddFragment();
        fragment.order = order;
        fragment.type = type;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_order_send, container, false);
        switch (type) {
            case TYPE_SEND:
                view = inflater.inflate(R.layout.fragment_create_order_send, container, false);
                break;
            case TYPE_RECIVE:
                view = inflater.inflate(R.layout.fragment_create_order_recive, container, false);
                break;
        }
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolbarNav(toolbar);

        if (order != null) {
            switch (type) {
                case TYPE_SEND:
                    addName.setText(order.getSendName());
                    addPhone.setText(order.getSendPhone());
                    addAddr.setText(order.getSendAddr());
                    addAddrInfo.setText(order.getSendAddrInfo());
                    break;
                case TYPE_RECIVE:
                    addName.setText(order.getReciveName());
                    addPhone.setText(order.getRecivePhone());
                    addAddr.setText(order.getReciveAddr());
                    addAddrInfo.setText(order.getReciveAddrInfo());
                    break;
            }
        }
        InitCityPicker();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_ok)
    public void onViewClicked() {

        if (addName.getText().toString().equals("")) {
            Snackbar.make(getView(), "请输入" + type + "人", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (addPhone.getText().toString().equals("")) {
            Snackbar.make(getView(), "请输入" + type + "人手机号", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (addAddr.getText().toString().equals("")) {
            Snackbar.make(getView(), "请输入" + type + "人地址", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (addAddrInfo.getText().toString().equals("")) {
            Snackbar.make(getView(), "请输入" + type + "人详细地址", Snackbar.LENGTH_LONG).show();
            return;
        }


        switch (type) {
            case TYPE_SEND:
                order.setSendName(addName.getText().toString());
                order.setSendPhone(addPhone.getText().toString());
                order.setSendAddr(addAddr.getText().toString());
                order.setSendAddrInfo(addAddrInfo.getText().toString());
                EventBus.getDefault().post(new SpCreateOrderSendEvent(order));
                break;
            case TYPE_RECIVE:
                order.setReciveName(addName.getText().toString());
                order.setRecivePhone(addPhone.getText().toString());
                order.setReciveAddr(addAddr.getText().toString());
                order.setReciveAddrInfo(addAddrInfo.getText().toString());
                EventBus.getDefault().post(new SpCreateOrderReciveEvent(order));
                break;
        }
        hideSoftInput();
        pop();
    }

    private void InitCityPicker() {
        cityPicker = new CityPicker.Builder(getContext())
                .textSize(20)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("安徽省")
                .city("合肥市")
                .district("包河区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                addAddr.setText(province + "/" + city + "/" + district);
            }

            @Override
            public void onCancel() {
            }
        });

    }

    @OnClick(R.id.addr)
    public void onaddAddr() {
        cityPicker.show();
    }
}
