package com.kenny.logistics.ui.fragment;

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
import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class CreateOrderSendFragment extends SupportFragment {
    @BindView(R.id.send_name)
    EditText sendName;
    @BindView(R.id.send_phone)
    EditText sendPhone;
    @BindView(R.id.send_addr)
    TextView sendAddr;
    @BindView(R.id.send_addr_info)
    EditText sendAddrInfo;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    CityPicker cityPicker;

    public static CreateOrderSendFragment newInstance(Bundle args) {
        CreateOrderSendFragment fragment = new CreateOrderSendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order_send, container, false);
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

        Bundle bundle = getArguments();
        if (bundle != null) {
            sendName.setText(bundle.getString("send_name"));
            sendPhone.setText(bundle.getString("send_phone"));
            sendAddr.setText(bundle.getString("send_addr"));
            sendAddrInfo.setText(bundle.getString("send_addr_info"));
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

        if(sendName.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入发货人", Snackbar.LENGTH_LONG).show();return;}

        if(sendPhone.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入发货人手机号", Snackbar.LENGTH_LONG).show();return;}

        if(sendAddr.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入发货人地址", Snackbar.LENGTH_LONG).show();return;}

        if(sendAddrInfo.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入发货人详细地址", Snackbar.LENGTH_LONG).show();return;}

        Bundle bundle = new Bundle();
        bundle.putString("send_name", sendName.getText().toString());
        bundle.putString("send_phone", sendPhone.getText().toString());
        bundle.putString("send_addr", sendAddr.getText().toString());
        bundle.putString("send_addr_info", sendAddrInfo.getText().toString());
        setFragmentResult(RESULT_OK, bundle);
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
                sendAddr.setText(province + "/" + city + "/" + district);
            }

            @Override
            public void onCancel() {
            }
        });

    }

    @OnClick(R.id.send_addr)
    public void onSendAddr() {
        cityPicker.show();
    }
}
