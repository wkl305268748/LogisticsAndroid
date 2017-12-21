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

public class CreateOrderReciveFragment extends SupportFragment {
    @BindView(R.id.recive_name)
    EditText reciveName;
    @BindView(R.id.recive_phone)
    EditText recivePhone;
    @BindView(R.id.recive_addr)
    TextView reciveAddr;
    @BindView(R.id.recive_addr_info)
    EditText reciveAddrInfo;
    Unbinder unbinder;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    CityPicker cityPicker;

    public static CreateOrderReciveFragment newInstance(Bundle args) {
        CreateOrderReciveFragment fragment = new CreateOrderReciveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order_recive, container, false);
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
            reciveName.setText(bundle.getString("recive_name"));
            recivePhone.setText(bundle.getString("recive_phone"));
            reciveAddr.setText(bundle.getString("recive_addr"));
            reciveAddrInfo.setText(bundle.getString("recive_addr_info"));
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

        if(reciveName.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入收货人", Snackbar.LENGTH_LONG).show();return;}

        if(recivePhone.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入收货人手机号", Snackbar.LENGTH_LONG).show();return;}

        if(reciveAddr.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入收货人地址", Snackbar.LENGTH_LONG).show();return;}

        if(reciveAddrInfo.getText().toString().equals(""))
        {Snackbar.make(getView(), "请输入收货人详细地址", Snackbar.LENGTH_LONG).show();return;}

        Bundle bundle = new Bundle();
        bundle.putString("recive_name", reciveName.getText().toString());
        bundle.putString("recive_phone", recivePhone.getText().toString());
        bundle.putString("recive_addr", reciveAddr.getText().toString());
        bundle.putString("recive_addr_info", reciveAddrInfo.getText().toString());
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
                reciveAddr.setText(province + "/" + city + "/" + district);
            }

            @Override
            public void onCancel() {
            }
        });

    }

    @OnClick(R.id.recive_addr)
    public void onreciveAddr() {
        cityPicker.show();
    }
}
