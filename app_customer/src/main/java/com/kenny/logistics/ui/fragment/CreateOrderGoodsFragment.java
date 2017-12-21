package com.kenny.logistics.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.kenny.logistics.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Kenny on 2017/8/8.
 */

public class CreateOrderGoodsFragment extends SupportFragment {

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

    public static CreateOrderGoodsFragment newInstance() {
        Bundle args = new Bundle();
        CreateOrderGoodsFragment fragment = new CreateOrderGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_order_goods, container, false);
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

        Bundle bundle = new Bundle();
        bundle.putString("goods_name", goodsName.getText().toString());
        bundle.putInt("goods_number", Integer.parseInt(goodsNumber.getText().toString()));
        bundle.putFloat("goods_weight", Float.parseFloat(goodsWeight.getText().toString()));
        bundle.putString("goods_size", goodsSize.getText().toString());
        bundle.putString("goods_remark", goodsRemark.getText().toString());
        setFragmentResult(RESULT_OK, bundle);
        hideSoftInput();
        pop();
    }
}
